package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logic.MoveGenerator;
import model.Board;
import model.Game;
import model.Human_Player;
import model.Remote_Player;
import model.Player_Model;
import network.Command;
import network.Session;

public class CheckerBoard extends JButton {

	private static final int PADDING = 16;

	private Game game;

	private CheckersFrame window;

	private Player_Model player1;

	private Player_Model player2;

	private Point selected;

	private boolean selectionValid;

	private Color lightTile;

	private Color darkTile;

	private boolean isGameOver;

	public CheckerBoard(CheckersFrame window) {
		this(window, new Game(), null, null);
	}

	public CheckerBoard(CheckersFrame window, Game game, Player_Model player1, Player_Model player2) {
		super.setBorderPainted(false);
		super.setFocusPainted(false);
		super.setContentAreaFilled(false);
		super.setBackground(Color.RED);

		this.addActionListener(new ClickListener());

		this.game = (game == null) ? new Game() : game;

		this.lightTile = Color.RED;
		this.darkTile = Color.BLACK;
		this.window = window;

		setPlayer1(player1);
		setPlayer2(player2);
	}

	public void update() {
		runPlayer();
		this.isGameOver = game.isGameOver();
		repaint();
	}

	private void runPlayer() {
		Player_Model player = getCurrentPlayer();

		if (player == null || player.isHuman() || player instanceof Remote_Player) {
			return;
		}
	}

	public void updateNetwork() {
		List<Session> sessions = new ArrayList<>();

		if (player1 instanceof Remote_Player) {
			sessions.add(window.getSession1());
		}

		if (player2 instanceof Remote_Player) {
			sessions.add(window.getSession2());
		}

		for (Session s : sessions) {
			sendGameState(s);
		}
	}

	public synchronized boolean setGameState(boolean testValue, String newState, String expected) {
		if (testValue && !game.getGameState().equals(expected)) {
			return false;
		}

		this.game.setGameState(newState);
		repaint();

		return true;
	}

	public void sendGameState(Session s) {
		if (s == null) {
			return;
		}

		Command update = new Command(Command.COMMAND_UPDATE, s.getSid(), game.getGameState());
		String host = s.getDestinationHost();
		int port = s.getDestinationPort();
		update.send(host, port);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Game game = this.game.copy();

		final int PADDING = 4;
		final int WIDTH = getWidth();
		final int HEIGHT = getHeight();
		final int DIMENSION = WIDTH < HEIGHT ? WIDTH : HEIGHT, SIZE = (DIMENSION - 2 * PADDING) / 8;
		final int X_OFFSET = (WIDTH - SIZE * 8) / 2;
		final int Y_OFFSET = (HEIGHT - SIZE * 8) / 2;
		final int CHECKER_SIZE = Math.max(0, SIZE - 2 * PADDING);

		g.setColor(Color.BLACK);
		g.drawRect(X_OFFSET - 1, Y_OFFSET - 1, SIZE * 8 + 1, SIZE * 8 + 1);
		g.setColor(lightTile);
		g.fillRect(X_OFFSET, Y_OFFSET, SIZE * 8, SIZE * 8);
		g.setColor(darkTile);

		for (int y = 0; y < 8; y++) {
			for (int x = (y + 1) % 2; x < 8; x += 2) {
				g.fillRect(X_OFFSET + x * SIZE, Y_OFFSET + y * SIZE, SIZE, SIZE);
			}
		}

		if (Board.isValidPoint(selected)) {
			g.setColor(selectionValid ? Color.GREEN : Color.RED);
			g.fillRect(X_OFFSET + selected.x * SIZE, Y_OFFSET + selected.y * SIZE, SIZE, SIZE);
		}

		Board b = game.getBoard();
		for (int y = 0; y < 8; y++) {
			int cy = Y_OFFSET + y * SIZE + PADDING;
			for (int x = (y + 1) % 2; x < 8; x += 2) {
				int id = b.get(x, y);

				if (id == Board.EMPTY) {
					continue;
				}

				int cx = X_OFFSET + x * SIZE + PADDING;

				if (id == Board.BLACK_CHECKER) {
					g.setColor(Color.DARK_GRAY);
					g.fillOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.LIGHT_GRAY);
					g.drawOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.BLACK);
					g.fillOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.LIGHT_GRAY);
					g.drawOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
				}

				else if (id == Board.BLACK_KING) {
					g.setColor(Color.DARK_GRAY);
					g.fillOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.LIGHT_GRAY);
					g.drawOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.DARK_GRAY);
					g.fillOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.LIGHT_GRAY);
					g.drawOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.BLACK);
					g.fillOval(cx - 1, cy - 2, CHECKER_SIZE, CHECKER_SIZE);
				}

				else if (id == Board.RED_CHECKER) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.DARK_GRAY);
					g.drawOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.RED);
					g.fillOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.DARK_GRAY);
					g.drawOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
				}

				else if (id == Board.RED_KING) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.DARK_GRAY);
					g.drawOval(cx + 1, cy + 2, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.DARK_GRAY);
					g.drawOval(cx, cy, CHECKER_SIZE, CHECKER_SIZE);
					g.setColor(Color.RED);
					g.fillOval(cx - 1, cy - 2, CHECKER_SIZE, CHECKER_SIZE);
				}

				if (id == Board.BLACK_KING || id == Board.RED_KING) {
					g.setColor(new Color(255, 255, 255));
					g.drawOval(cx - 1, cy - 2, CHECKER_SIZE, CHECKER_SIZE);
					g.drawOval(cx + 1, cy, CHECKER_SIZE - 4, CHECKER_SIZE - 4);
				}
			}
		}

		if (isGameOver) {
			JDialog dialog = new JDialog();
			dialog.setTitle("Winner");
			JLabel winner;

			if (game.isP1Turn()) {
				winner = new JLabel("Player 2 (red) wins!", SwingConstants.CENTER);
				winner.setForeground(Color.RED);
			} else {
				winner = new JLabel("Player 1 (black) wins!", SwingConstants.CENTER);
				winner.setForeground(Color.BLACK);
			}
			winner.setFont(new Font("Arial", Font.BOLD, 20));
			dialog.add(winner);

			JButton button = new JButton("Exit Game");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					System.exit(0);
				}
			});
			dialog.add(button, BorderLayout.SOUTH);

			dialog.setSize(300, 300);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = (game == null) ? new Game() : game;
	}

	public CheckersFrame getWindow() {
		return window;
	}

	public void setWindow(CheckersFrame window) {
		this.window = window;
	}

	public Player_Model getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player_Model player1) {
		this.player1 = (player1 == null) ? new Human_Player() : player1;
		if (game.isP1Turn() && !this.player1.isHuman()) {
			this.selected = null;
		}
	}

	public Player_Model getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player_Model player2) {
		this.player2 = (player2 == null) ? new Human_Player() : player2;
		if (!game.isP1Turn() && !this.player2.isHuman()) {
			this.selected = null;
		}
	}

	public Player_Model getCurrentPlayer() {
		return game.isP1Turn() ? player1 : player2;
	}

	public Color getLightTile() {
		return lightTile;
	}

	public void setLightTile(Color lightTile) {
		this.lightTile = (lightTile == null) ? Color.RED : lightTile;
	}

	public Color getDarkTile() {
		return darkTile;
	}

	public void setDarkTile(Color darkTile) {
		this.darkTile = (darkTile == null) ? Color.BLACK : darkTile;
	}

	private void handleClick(int x, int y) {
		if (isGameOver || !getCurrentPlayer().isHuman()) {
			return;
		}

		Game copy = game.copy();

		final int W = getWidth(), H = getHeight();
		final int DIMENSION = W < H ? W : H;
		final int SIZE = (DIMENSION - 2 * PADDING) / 8;
		final int X_OFFSET = (W - SIZE * 8) / 2;
		final int Y_OFFSET = (H - SIZE * 8) / 2;
		x = (x - X_OFFSET) / SIZE;
		y = (y - Y_OFFSET) / SIZE;
		Point sel = new Point(x, y);

		if (Board.isValidPoint(sel) && Board.isValidPoint(selected)) {
			boolean change = copy.isP1Turn();
			String expected = copy.getGameState();
			boolean move = copy.move(selected, sel);
			boolean updated = (move ? setGameState(true, copy.getGameState(), expected) : false);

			if (updated) {
				updateNetwork();
			}

			change = (copy.isP1Turn() != change);
			this.selected = change ? null : sel;
		} else {
			this.selected = sel;
		}

		this.selectionValid = isValidSelection(copy.getBoard(), copy.isP1Turn(), selected);

		update();
	}

	private boolean isValidSelection(Board b, boolean isP1Turn, Point selected) {
		int i = Board.toIndex(selected);
		int id = b.get(i);

		if (id == Board.EMPTY || id == Board.INVALID) {
			return false;
		} else if (isP1Turn ^ (id == Board.BLACK_CHECKER || id == Board.BLACK_KING)) {
			return false;
		} else if (!MoveGenerator.getSkips(b, i).isEmpty()) {
			return true;
		} else if (MoveGenerator.getMoves(b, i).isEmpty()) {
			return false;
		}

		List<Point> points = b.find(isP1Turn ? Board.BLACK_CHECKER : Board.RED_CHECKER);
		points.addAll(b.find(isP1Turn ? Board.BLACK_KING : Board.RED_KING));

		for (Point p : points) {
			int checker = Board.toIndex(p);

			if (checker == i) {
				continue;
			}

			if (!MoveGenerator.getSkips(b, checker).isEmpty()) {
				return false;
			}
		}

		return true;
	}

	private class ClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Point mouse = CheckerBoard.this.getMousePosition();
			if (mouse != null) {
				handleClick(mouse.x, mouse.y);
			}
		}
	}
}

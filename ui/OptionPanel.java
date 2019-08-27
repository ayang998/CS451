package ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Human_Player;
import model.Remote_Player;
import model.Player_Model;
import network.CheckersNetworkHandler;
import network.Command;
import network.ConnectionListener;
import network.Session;

public class OptionPanel extends JPanel {

	private CheckersFrame window;

	private JButton restartButton;

	private JComboBox<String> player1Options;

	private NetworkWindow player1NetworkOptions;

	private JButton player1Button;

	private JComboBox<String> player2Options;

	private NetworkWindow player2NetworkOptions;

	private JButton player2Button;

	public OptionPanel(CheckersFrame window) {
		super(new GridLayout(0, 1));

		this.window = window;

		OptionListener ol = new OptionListener();

		final String[] playerTypeOpts = { "Human", "Network" };

		this.restartButton = new JButton("Restart");
		this.restartButton.addActionListener(ol);

		this.player1Options = new JComboBox<>(playerTypeOpts);
		this.player1Options.addActionListener(ol);

		this.player2Options = new JComboBox<>(playerTypeOpts);
		this.player2Options.addActionListener(ol);

		this.player1NetworkOptions = new NetworkWindow(ol);
		this.player1NetworkOptions.setTitle("Player 1 - Configure Network");

		this.player2NetworkOptions = new NetworkWindow(ol);
		this.player2NetworkOptions.setTitle("Player 2 - Configure Network");

		this.player1Button = new JButton("Set Connection");
		this.player1Button.addActionListener(ol);
		this.player1Button.setVisible(false);

		this.player2Button = new JButton("Set Connection");
		this.player2Button.addActionListener(ol);
		this.player2Button.setVisible(false);

		JPanel restart = new JPanel(new FlowLayout(FlowLayout.CENTER));
		restart.add(restartButton);
		this.add(restart);

		JPanel players = new JPanel(new FlowLayout(FlowLayout.CENTER));
		players.add(new JLabel("Player 1 (black): "));
		players.add(player1Options);
		players.add(player1Button);
		players.add(new JLabel("Player 2 (red): "));
		players.add(player2Options);
		players.add(player2Button);
		this.add(players);
	}

	public CheckersFrame getWindow() {
		return window;
	}

	public void setWindow(CheckersFrame window) {
		this.window = window;
	}

	public void setNetworkWindowMessage(boolean player1, String msg) {
		if (player1) {
			this.player1NetworkOptions.setMessage(msg);
		} else {
			this.player2NetworkOptions.setMessage(msg);
		}
	}

	public NetworkWindow getNetworkWindow1() {
		return player1NetworkOptions;
	}

	public NetworkWindow getNetworkWindow2() {
		return player2NetworkOptions;
	}

	private void handleNetworkUpdate(NetworkWindow networkWindow, ActionEvent e) {
		if (networkWindow == null || window == null || e == null) {
			return;
		}

		int sourcePort = networkWindow.getSourcePort(), destinationPort = networkWindow.getDestinationPort();
		String destinationHost = networkWindow.getDestinationHost();
		boolean isPlayer1 = (networkWindow == player1NetworkOptions);
		Session session = (isPlayer1 ? window.getSession1() : window.getSession2());

		if (e.getID() == NetworkWindow.LISTEN_BUTTON) {
			if (sourcePort < 1025 || sourcePort > 65535) {
				networkWindow.setMessage("Error: source port must be between 1025 and 65535. ");
				return;
			}
			if (!ConnectionListener.available(sourcePort)) {
				networkWindow.setMessage("Error: source port " + sourcePort + " is not available.");
				return;
			}

			if (session.getListener().getPort() != sourcePort) {
				session.getListener().stopListening();
			}

			session.getListener().setPort(sourcePort);
			session.getListener().listen();

			networkWindow.setMessage("  This client is listening on port " + sourcePort);
			networkWindow.setCanUpdateListen(false);
			networkWindow.setCanUpdateConnect(true);
		} else if (e.getID() == NetworkWindow.CONNECT_BUTTON) {
			if (destinationPort < 1025 || destinationPort > 65535) {
				networkWindow.setMessage("  Error: destination port must be between 1025 and 65535. ");
				return;
			}
			if (destinationHost == null || destinationHost.isEmpty()) {
				destinationHost = "127.0.0.1";
			}

			Command connect = new Command(Command.COMMAND_CONNECT, networkWindow.getSourcePort() + "",
					isPlayer1 ? "1" : "0");
			String response = connect.send(destinationHost, destinationPort);

			if (response.isEmpty()) {
				networkWindow
						.setMessage("  Error: could not connect to " + destinationHost + ":" + destinationPort + ".");
			} else if (response.startsWith(CheckersNetworkHandler.RESPONSE_DENIED)) {
				String[] lines = response.split("\n");
				String error = lines.length > 1 ? lines[1] : "";

				if (error.isEmpty()) {
					networkWindow.setMessage("  Error: the other client refused to connect.");
				} else {
					networkWindow.setMessage("  " + error);
				}
			} else if (response.startsWith(CheckersNetworkHandler.RESPONSE_ACCEPTED)) {
				session.setDestinationHost(destinationHost);
				session.setDestinationPort(destinationPort);

				networkWindow.setMessage(
						"  Successfully started a session with " + destinationHost + ":" + destinationPort + ".");
				networkWindow.setCanUpdateConnect(false);

				String[] lines = response.split("\n");
				String sid = lines.length > 1 ? lines[1] : "";
				session.setSid(sid);

				Command get = new Command(Command.COMMAND_GET, sid, null);
				response = get.send(destinationHost, destinationPort);
				lines = response.split("\n");
				String state = lines.length > 1 ? lines[1] : "";
				window.setGameState(state);
			} else {
				networkWindow.setMessage(
						"  Error: you tried to connect to a host and port that isn't running a checkers client.");
			}
		}
	}

	private static Player_Model getPlayer(JComboBox<String> playerOptions) {
		Player_Model player = new Human_Player();

		if (playerOptions == null) {
			return player;
		}

		String type = "" + playerOptions.getSelectedItem();
		if (type.equals("Network")) {
			player = new Remote_Player();
		}

		return player;
	}

	private class OptionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (window == null) {
				return;
			}

			Object source = e.getSource();

			JButton button = null;

			boolean isNetwork = false, isP1 = true;
			Session s = null;

			if (source == restartButton) {
				window.restart();
				window.getBoard().updateNetwork();
			} else if (source == player1Options) {
				Player_Model player = getPlayer(player1Options);
				window.setPlayer1(player);

				isNetwork = (player instanceof Remote_Player);
				button = player1Button;
				s = window.getSession1();
			} else if (source == player2Options) {
				Player_Model player = getPlayer(player2Options);
				window.setPlayer2(player);

				isNetwork = (player instanceof Remote_Player);
				button = player2Button;
				s = window.getSession2();
				isP1 = false;
			} else if (source == player1Button) {
				player1NetworkOptions.setVisible(true);
			} else if (source == player2Button) {
				player2NetworkOptions.setVisible(true);
			} else if (source == player1NetworkOptions || source == player2NetworkOptions) {
				handleNetworkUpdate((NetworkWindow) source, e);
			}

			if (button != null) {
				String sid = s.getSid();

				if (!isNetwork && button.isVisible() && sid != null && !sid.isEmpty()) {
					Command disconnect = new Command(Command.COMMAND_DISCONNECT, sid);
					disconnect.send(s.getDestinationHost(), s.getDestinationPort());

					s.setSid(null);
					NetworkWindow networkWindow = isP1 ? player1NetworkOptions : player2NetworkOptions;
					networkWindow.setCanUpdateConnect(true);
				}

				button.setVisible(isNetwork);
				button.repaint();
			}
		}
	}
}

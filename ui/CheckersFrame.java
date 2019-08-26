package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Player;
import network.CheckersNetworkHandler;
import network.ConnectionListener;
import network.Session;


public class CheckersFrame extends JFrame {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 650;
	
	public static final String TITLE = "Checkers";
	
	private CheckerBoard board;
	
	private OptionPanel opts;
	
	private Session session1;
	
	private Session session2;
	
	public CheckersFrame() {
		this(WIDTH, HEIGHT, TITLE);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(CheckersFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit application?", null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {  
					System.exit(0);
				}  
			}
		});
		menu.add(exit);
		
		menu = new JMenu("Help");
		menuBar.add(menu);
		
		JMenuItem rules = new JMenuItem("Rules");
		rules.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
				String rules = "https://www.itsyourturn.com/t_helptopic2030.html";
				
				try {
					Desktop desktop = Desktop.getDesktop();
					desktop.browse(java.net.URI.create(rules));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		menu.add(rules);
		
		JMenuItem aboutUs = new JMenuItem("About Us");
		aboutUs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
				JDialog dialog = new JDialog();
				dialog.setLayout(new BorderLayout());
				dialog.getContentPane().setBackground(Color.WHITE);
				dialog.setPreferredSize(new Dimension(300, 300));

				dialog.setTitle("About Us");
				
				JLabel group = new JLabel("<html> CS451-001 Group 7 <br>"
						+ "<br> Zachary Jung "
						+ "<br> Anna Nguyen "
						+ "<br> Mosfiqur Rahman "
						+ "<br> Andy Yang </html>", SwingConstants.CENTER);
				group.setFont(new Font("Arial", Font.PLAIN, 20));
				dialog.add(group);
				
				JButton button = new JButton("Close");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						dialog.dispose();
					}
				});
				dialog.add(button, BorderLayout.SOUTH);

				dialog.pack();
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.setResizable(false);
			}
		});
		menu.add(aboutUs);

		setJMenuBar(menuBar);
		
		setVisible(true);
	}
	
	public CheckersFrame(Player player1, Player player2) {
		this();
		setPlayer1(player1);
		setPlayer2(player2);
	}
	
	public CheckersFrame(int width, int height, String title) {
		super(title);
		super.setSize(width, height);
		super.setLocationByPlatform(true);
		
		JPanel layout = new JPanel(new BorderLayout());
		this.board = new CheckerBoard(this);
		this.opts = new OptionPanel(this);
		layout.add(board, BorderLayout.CENTER);
		layout.add(opts, BorderLayout.SOUTH);
		this.add(layout);
		
		CheckersNetworkHandler session1Handler, session2Handler;
		session1Handler = new CheckersNetworkHandler(true, this, board, opts);
		session2Handler = new CheckersNetworkHandler(false, this, board, opts);
		this.session1 = new Session(new ConnectionListener(0, session1Handler), null, null, -1);
		this.session2 = new Session(new ConnectionListener(0, session2Handler), null, null, -1);
	}
	
	public CheckerBoard getBoard() {
		return board;
	}

	public void setPlayer1(Player player1) {
		this.board.setPlayer1(player1);
		this.board.update();
	}
	
	public void setPlayer2(Player player2) {
		this.board.setPlayer2(player2);
		this.board.update();
	}
	
	public void restart() {
		this.board.getGame().restart();
		this.board.update();
	}
	
	public void setGameState(String state) {
		this.board.getGame().setGameState(state);
	}
	
	public Session getSession1() {
		return session1;
	}

	public Session getSession2() {
		return session2;
	}
}

package ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NetworkWindow extends JFrame {
	public static final int WIDTH = 500;

	public static final int HEIGHT = 140;

	public static final String TITLE = "Configure Network";

	public static final int CONNECT_BUTTON = 0;

	public static final int LISTEN_BUTTON = 1;

	private JTextField sourcePort;

	private JTextField destinationHost;

	private JTextField destinationPort;

	private JButton listen;

	private JButton connect;

	private JPanel source;

	private JPanel destination;

	private JLabel message;

	private ActionListener actionListener;

	public NetworkWindow() {
		super(TITLE);
		super.setSize(WIDTH, HEIGHT);
		super.setLocationByPlatform(true);
		setLocationRelativeTo(null);
		setResizable(false);
		init();
	}

	public NetworkWindow(ActionListener actionListener) {
		this();
		this.actionListener = actionListener;
	}

	public NetworkWindow(ActionListener actionListener, int sourcePort, String destinationHost, int destinationPort) {
		this();
		this.actionListener = actionListener;
		setSourcePort(sourcePort);
		setDestinationHost(destinationHost);
		setDestinationPort(destinationPort);
	}

	private void init() {
		this.getContentPane().setLayout(new GridLayout(3, 1));

		this.sourcePort = new JTextField(4);

		this.destinationHost = new JTextField(11);
		this.destinationHost.setText("127.0.0.1");
		this.destinationPort = new JTextField(4);

		this.listen = new JButton("Listen");
		this.listen.addActionListener(new ButtonListener());

		this.connect = new JButton("Connect");
		this.connect.addActionListener(new ButtonListener());

		this.source = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.source.add(new JLabel("Source port:"));
		this.source.add(sourcePort);
		this.source.add(listen);

		this.destination = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.destination.add(new JLabel("Destination host/port:"));
		this.destination.add(destinationHost);
		this.destination.add(destinationPort);
		this.destination.add(connect);

		this.message = new JLabel();

		setCanUpdateConnect(false);

		this.sourcePort.setToolTipText("Source port to listen for updates (1025 - 65535)");
		this.destinationPort.setToolTipText("Destination port to listen for updates (1025 - 65535)");
		this.destinationHost.setToolTipText("The destination host to send updates to (ipconfig)");

		createLayout(null);
	}

	private void createLayout(String message) {
		this.getContentPane().removeAll();

		this.getContentPane().add(source);
		this.getContentPane().add(destination);

		this.message.setText(message);
		this.getContentPane().add(this.message);

		this.message.setVisible(false);
		this.message.setVisible(true);
	}

	public void setCanUpdateListen(boolean canUpdate) {
		this.sourcePort.setEnabled(canUpdate);
		this.listen.setEnabled(canUpdate);
	}

	public void setCanUpdateConnect(boolean canUpdate) {
		this.destinationHost.setEnabled(canUpdate);
		this.destinationPort.setEnabled(canUpdate);
		this.connect.setEnabled(canUpdate);
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	public int getSourcePort() {
		return parseField(sourcePort);
	}

	public void setSourcePort(int port) {
		this.sourcePort.setText("" + port);
	}

	public String getDestinationHost() {
		return destinationHost.getText();
	}

	public void setDestinationHost(String host) {
		this.destinationHost.setText(host);
	}

	public int getDestinationPort() {
		return parseField(destinationPort);
	}

	public void setDestinationPort(int port) {
		this.destinationPort.setText("" + port);
	}

	public String getMessage() {
		return message.getText();
	}

	public void setMessage(String message) {
		createLayout(message);
	}

	private static int parseField(JTextField textField) {
		if (textField == null) {
			return 0;
		}

		int portNumber = 0;
		try {
			portNumber = Integer.parseInt(textField.getText());
		} catch (NumberFormatException e) {

		}

		return portNumber;
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (actionListener != null) {
				JButton source = (JButton) e.getSource();
				ActionEvent event = null;
				
				if (source == listen) {
					event = new ActionEvent(NetworkWindow.this, LISTEN_BUTTON, null);
				} else {
					event = new ActionEvent(NetworkWindow.this, CONNECT_BUTTON, null);
				}
				
				actionListener.actionPerformed(event);
			}
		}
	}
}

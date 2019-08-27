
package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import model.Remote_Player;
import ui.CheckerBoard;
import ui.CheckersFrame;
import ui.NetworkWindow;
import ui.OptionPanel;

public class CheckersNetworkHandler implements ActionListener {

	private static final int MIN_SID_LENGTH = 16;

	private static final int MAX_SID_LENGTH = 64;

	public static final String RESPONSE_ACCEPTED = "ACCEPTED";

	public static final String RESPONSE_DENIED = "DENIED";

	private boolean isPlayer1;

	private CheckersFrame window;

	private CheckerBoard board;

	/** The option panel in the checkers window. */
	private OptionPanel opts;

	public CheckersNetworkHandler(boolean isPlayer1, CheckersFrame window, CheckerBoard board, OptionPanel opts) {
		this.isPlayer1 = isPlayer1;
		this.window = window;
		this.board = board;
		this.opts = opts;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e == null || !(e.getSource() instanceof ConnectionHandler)) {
			return;
		}

		ConnectionHandler handler = (ConnectionHandler) e.getSource();
		String data = ConnectionListener.read(handler.getSocket());
		data = data.replace("\r\n", "\n");

		if (window == null || board == null || opts == null) {
			sendResponse(handler, "Client error: invalid network handler.");
			return;
		}

		Session s1 = window.getSession1(), s2 = window.getSession2();

		String[] lines = data.split("\n");
		String cmd = lines[0].split(" ")[0].toUpperCase();
		String sid = lines.length > 1 ? lines[1] : "";
		String response = "";
		boolean match = false;
		if (isPlayer1) {
			match = sid.equals(s1.getSid());
		} else {
			match = sid.equals(s2.getSid());
		}

		if (cmd.equals(Command.COMMAND_UPDATE)) {
			String newState = (match && lines.length > 2 ? lines[2] : "");
			response = handleUpdate(newState);
		}

		else if (cmd.equals(Command.COMMAND_CONNECT)) {

			int port = -1;
			try {
				port = Integer.parseInt(sid);
			} catch (NumberFormatException err) {
			}

			String isP1 = (lines.length > 2 ? lines[2] : "");
			boolean remotePlayer1 = isP1.startsWith("1");

			response = handleConnect(handler.getSocket(), port, remotePlayer1);
		}

		else if (cmd.equals(Command.COMMAND_GET)) {

			if (match) {
				response = RESPONSE_ACCEPTED + "\n" + board.getGame().getGameState();
			} else {
				response = RESPONSE_DENIED;
			}
		}

		else if (cmd.equals(Command.COMMAND_DISCONNECT)) {

			if (match) {
				response = RESPONSE_ACCEPTED + "\nClient has been disconnected.";
				if (isPlayer1) {
					s1.setSid(null);
					this.opts.getNetworkWindow1().setCanUpdateConnect(true);
				} else {
					s2.setSid(null);
					this.opts.getNetworkWindow2().setCanUpdateConnect(true);
				}
			} else {
				response = RESPONSE_DENIED + "\nError: cannot disconnect if not connected.";
			}
		}

		else {
			response = RESPONSE_DENIED + "\nJava Checkers - unknown " + "command '" + cmd + "'";
		}

		sendResponse(handler, response);
	}

	private String handleUpdate(String newState) {

		// New state is invalid
		if (newState.isEmpty()) {
			return RESPONSE_DENIED;
		}

		this.board.setGameState(false, newState, null);
		if (!board.getCurrentPlayer().isHuman()) {
			board.update();
		}

		if (isPlayer1 && board.getPlayer2() instanceof Remote_Player) {
			board.sendGameState(window.getSession2());
		} else if (!isPlayer1 && board.getPlayer1() instanceof Remote_Player) {
			board.sendGameState(window.getSession1());
		}

		return RESPONSE_ACCEPTED;
	}

	private String handleConnect(Socket s, int port, boolean remotePlayer1) {

		Session s1 = window.getSession1(), s2 = window.getSession2();
		String sid1 = s1.getSid();
		String sid2 = s2.getSid();
		if ((isPlayer1 && sid1 != null && !sid1.isEmpty()) || (!isPlayer1 && sid2 != null && !sid2.isEmpty())) {
			return RESPONSE_DENIED + "\nError: user already connected.";
		}

		if (!(isPlayer1 ^ remotePlayer1)) {
			return RESPONSE_DENIED + "\nError: the other client is already " + "player "
					+ (remotePlayer1 ? "1." : "2.");
		}
		String host = s.getInetAddress().getHostAddress();
		if (host.equals("127.0.0.1")) {
			if ((isPlayer1 && port == s2.getSourcePort()) || (!isPlayer1 && port == s1.getSourcePort())) {
				return RESPONSE_DENIED + "\nError: the client cannot connect " + "to itself.";
			}
		}

		String sid = generateSessionID();
		Session session = isPlayer1 ? s1 : s2;
		NetworkWindow win = (isPlayer1 ? opts.getNetworkWindow1() : opts.getNetworkWindow2());
		session.setSid(sid);
		session.setDestinationHost(host);
		session.setDestinationPort(port);

		win.setDestinationHost(host);
		win.setDestinationPort(port);
		win.setCanUpdateConnect(false);
		win.setMessage("  Connected to " + host + ":" + port + ".");

		return RESPONSE_ACCEPTED + "\n" + sid + "\nSuccessfully connected.";
	}

	private static void sendResponse(ConnectionHandler handler, String response) {

		if (handler == null) {
			return;
		}
		Socket s = handler.getSocket();
		if (s == null || s.isClosed()) {
			return;
		}
		if (response == null) {
			response = "";
		}

		try (OutputStream os = s.getOutputStream()) {
			os.write(response.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			// Close the socket
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String generateSessionID() {

		String sid = "";
		int chars = (int) ((MAX_SID_LENGTH - MIN_SID_LENGTH) * Math.random()) + MIN_SID_LENGTH;
		for (int i = 0; i < chars; i++) {

			int t = (int) (4 * Math.random());
			int min = 32, max = 48;
			if (t == 1) {
				min = 48;
				max = 65;
			} else if (t == 2) {
				min = 65;
				max = 97;
			} else if (t == 3) {
				min = 97;
				max = 125;
			}
			char randChar = (char) ((Math.random() * (max - min)) + min);
			sid += randChar;
		}

		return sid;
	}
}

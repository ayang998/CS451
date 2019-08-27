
package network;

import java.awt.event.ActionEvent;
import java.net.Socket;

public class ConnectionHandler extends Thread {

	private ConnectionListener listener;

	private Socket socket;

	public ConnectionHandler(ConnectionListener listener, Socket socket) {
		this.listener = listener;
		this.socket = socket;
	}

	@Override
	public void run() {

		if (listener == null) {
			return;
		}

		ActionEvent e = new ActionEvent(this, 0, "CONNECTION ACCEPT");
		if (listener.getConnectionHandler() != null) {
			this.listener.getConnectionHandler().actionPerformed(e);
		}
	}

	public ConnectionListener getListener() {
		return listener;
	}

	public Socket getSocket() {
		return socket;
	}
}

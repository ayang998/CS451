package ui;

import javax.swing.*;


public class SplashScreen extends JFrame{

	public SplashScreen() {
		ImageIcon splashScreen = new ImageIcon("resources/splash-screen.png");
		JLabel label = new JLabel(splashScreen);
		add(label);
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		dispose();
	}
}
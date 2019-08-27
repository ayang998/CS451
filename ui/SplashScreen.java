package ui;

import java.net.URL;

import javax.swing.*;


public class SplashScreen extends JFrame{

	public SplashScreen() {
		URL image = SplashScreen.class.getResource("/resources/splash-screen.png");
		ImageIcon splashScreen = new ImageIcon(image);
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
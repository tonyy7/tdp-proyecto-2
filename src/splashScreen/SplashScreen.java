package splashScreen;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow {
	protected int tiempo;
	
	/*
	 * Crear una SplashScreen, con tiempo en segundos
	 */
	public SplashScreen(int tiempo) {
		this.tiempo = tiempo;
		JPanel panel = (JPanel) getContentPane();
		ImageIcon img 	= new ImageIcon("assets/SplashScreen.png");
		
		panel.add(new JLabel(img), BorderLayout.CENTER);
		setSize(img.getIconWidth(), img.getIconHeight());
		setLocationRelativeTo(null);
		setVisible(true);
		
		try {
			Thread.sleep(tiempo*1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		setVisible(false);
		
	}
}

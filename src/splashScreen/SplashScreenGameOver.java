package splashScreen;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.JTextPane;
import java.awt.Font;

public class SplashScreenGameOver extends JWindow {
	protected int tiempo;
	protected JTextPane textPane;
	
	/*
	 * Crear una SplashScreen, con tiempo en segundos
	 */
	public SplashScreenGameOver(int tiempo, String url) {
		this.tiempo = tiempo;
		JPanel panel = (JPanel) getContentPane();
		ImageIcon img 	= new ImageIcon(url);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel(img);
		label.setBounds(0, 0, 500, 500);
		panel.add(label);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Arial", Font.BOLD, 14));
		textPane.setBounds(138, 221, 220, 228);
		getContentPane().add(textPane);
		setSize(500, 500);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		try {
			Thread.sleep(tiempo*1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		setVisible(false);
		
	}
	
	public void setTop(String top) {
		textPane.setText(top);
	}
}

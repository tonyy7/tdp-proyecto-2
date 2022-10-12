package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class GUI extends JFrame {
	public GUI() {
		getContentPane().setLayout(null);
		
		JPanel panelGrilla = new JPanel();
		panelGrilla.setBounds(10, 11, 280, 239);
		getContentPane().add(panelGrilla);
		
		JLabel lblPuntaje = new JLabel("puntaje");
		lblPuntaje.setBounds(300, 11, 124, 55);
		getContentPane().add(lblPuntaje);
		
		JLabel lblNewLabel = new JLabel("tiempo");
		lblNewLabel.setBounds(300, 72, 124, 55);
		getContentPane().add(lblNewLabel);
		
		JButton btnmenu = new JButton("menu");
		btnmenu.setBounds(300, 138, 124, 46);
		getContentPane().add(btnmenu);
	}
}

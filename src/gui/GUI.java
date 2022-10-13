package gui;

import javax.swing.*;

import juego.Juego;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame {
	protected Juego juego;
	protected ControlTeclado teclado;
	
	public GUI(Juego juego) {
		setResizable(false);
		this.juego = juego;
		teclado = new ControlTeclado(juego);
		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(600, 800));
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		JPanel panelGrilla = new JPanel();
		panelGrilla.setBounds(10, 11, 600, 600);
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
		
		
		initKeyBindings();
		setVisible(true);
	}
	
	private void initKeyBindings() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
					{
						teclado.generadorPosition("izquierda");
						break;
					}
					case KeyEvent.VK_RIGHT:
					{
						teclado.generadorPosition("derecha");
						break;
					}
					case KeyEvent.VK_UP:
					{
						teclado.generadorPosition("arriba");
						break;
					}
					case KeyEvent.VK_DOWN:
					{
						teclado.generadorPosition("abajo");
						break;
					}
					case KeyEvent.VK_ESCAPE:
					{
						teclado.generadorPosition("salir");
						break;
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
}

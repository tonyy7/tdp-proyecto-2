package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import juego.Juego;

import javax.swing.JLabel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class GUI extends JFrame {
	protected Juego juego;
	protected ControlTeclado teclado;
	
	public GUI(Juego juego) {
		this.juego = juego;
		teclado = new ControlTeclado(juego);
		
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
		
		initKeyBindings();
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

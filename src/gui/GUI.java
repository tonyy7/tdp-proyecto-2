package gui;

import javax.swing.*;

import criatura.Cuerpo;
import ente.Ente;
import grilla.Grilla;
import juego.Juego;
import reloj.Reloj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;

public class GUI extends JFrame {
	protected final static int celdaSize = 30; //Tama�o de las celdas 30x30
	protected Juego juego;
	protected ControlTeclado teclado;
	protected JLabel lblPuntaje;
	protected JLabel lblNewLabel;
	protected Reloj timer;
	protected LinkedList<JLabel> pared;
	private LinkedList<JLabel> snake;
	protected JPanel panelGrilla;
	private Color colorTexto;
	private Font fuenteTexto;
	private JTextPane textPane;
	
	
	
	public GUI(Juego juego, Reloj timer) {
		pared = new LinkedList<JLabel>();
		snake = new LinkedList<JLabel>();
		this.juego = juego;
		this.timer = timer;
		teclado = new ControlTeclado(juego);
		colorTexto = new Color(233, 246, 255);
		fuenteTexto = new Font("Arial", Font.BOLD, 14);
		getContentPane().setBackground(new Color(6, 40, 61));
		setResizable(false);
		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(797, 783));
		setResizable(false);
		setLocationRelativeTo(null);		
		
		panelGrilla = new JPanel();
		panelGrilla.setBackground(new Color(233, 246, 255));
		panelGrilla.setBounds(10, 10, 600, 600);
		
		JLabel lblPuntaje = new JLabel("PUNTAJE:");
		lblPuntaje.setFont(fuenteTexto);
		lblPuntaje.setForeground(new Color(255, 255, 0));
		lblPuntaje.setBounds(638, 88, 130, 40);
		lblPuntaje.setBackground(colorTexto);
		
		JButton btnmenu = new JButton("menu");
		btnmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnmenu.setBackground(new Color(71, 181, 255));
		btnmenu.setForeground(new Color(0, 0, 0));
		btnmenu.setBounds(639, 320, 130, 46);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(new Color(6, 40, 61));
		textPane.setForeground(new Color(255, 255, 0));
		textPane.setText("TIEMPO: 00:00");
		textPane.setFont(new Font("Arial", Font.BOLD, 14));
		textPane.setBounds(638, 47, 130, 40);
		
		
		setVisible(true);
		initKeyBindings();
		
		getContentPane().add(panelGrilla);
		panelGrilla.setLayout(null);
		getContentPane().add(lblPuntaje);
		getContentPane().add(btnmenu);
		getContentPane().add(textPane);

	}
	
	public void actualizarVentana(LinkedList<Cuerpo> pocisionSnake) {   
		
        for(int i=0; i<pocisionSnake.size(); i++) {
        	
        		JLabel cuerpo=new JLabel(new ImageIcon(pocisionSnake.get(i).getSkin()));
        		snake.addLast(cuerpo);
        		panelGrilla.add(cuerpo);
        		cuerpo.setBounds(pocisionSnake.get(i).getPosition().getX()*30, pocisionSnake.get(i).getPosition().getY()*30,30,30);
        	
        }
    }

	
	public void generarGrilla() {
		LinkedList<Ente>[][] grillaLogica = juego.getGrilla();
		JLabel insert;
		for(int i=0; i<20; i++) {
			for(int j=0; j<20; j++) {
				if(grillaLogica[i][j].getFirst() != null) {
					insert = new JLabel(new ImageIcon(grillaLogica[i][j].getFirst().getSkin()));
					pared.addLast(insert);
					panelGrilla.add(insert);
					insert.setBounds((grillaLogica[i][j].getFirst().getPosition().getX())*30,(grillaLogica[i][j].getFirst().getPosition().getY())*30, 30, 30);
				}
			}
		}
	}
	
	public void setTiempo() {
		textPane.setText("TIEMPO: "+timer.getTime());
	}
	
	public void setPuntaje() {
		lblPuntaje.setText("PUNTAJE: "+juego.getPuntajeActaul());
	}
	
	private void initKeyBindings() {
		addKeyListener(new KeyListener() {
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

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}

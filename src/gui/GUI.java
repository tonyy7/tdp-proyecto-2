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
import java.awt.Point;
import java.awt.event.KeyAdapter;

public class GUI extends JFrame {
	protected final static int celdaSize = 30; //Tama�o de las celdas 30x30
	protected Juego juego;
	protected ControlTeclado teclado;
	protected JLabel lblPuntaje;
	protected JLabel lblNewLabel;
	protected JLabel ente;
	protected Reloj timer;
	protected LinkedList<JLabel> pared;
	protected LinkedList<JLabel> snake;
	protected LinkedList<JLabel> consumibles;
	protected JPanel panelGrilla;
	private Color colorTexto;
	private Font fuenteTexto;
	private JTextPane textPaneTiempo;
	
	protected int frameX;
	protected int frameY;
	private JTextPane textPanePuntos;
	
	
	
	public GUI(Juego juego, Reloj timer) {
		frameX=1;
		frameY=1;
		pared = new LinkedList<JLabel>();
		snake = new LinkedList<JLabel>();
		consumibles = new LinkedList<JLabel>();
		this.juego = juego;
		this.timer = timer;
		teclado = new ControlTeclado(juego);
		colorTexto = new Color(233, 246, 255);
		fuenteTexto = new Font("Arial", Font.BOLD, 14);
		getContentPane().setBackground(new Color(6, 40, 61));
		setResizable(false);
		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(797, 658));
		setResizable(false);
		setLocationRelativeTo(null);		
		
		panelGrilla = new JPanel();
		panelGrilla.setBackground(new Color(233, 246, 255));
		panelGrilla.setBounds(10, 10, 600, 600);
		
		textPanePuntos = new JTextPane();
		textPanePuntos.setText("PUNTAJE: 0");
		textPanePuntos.setForeground(Color.YELLOW);
		textPanePuntos.setFont(new Font("Arial", Font.BOLD, 14));
		textPanePuntos.setEditable(false);
		textPanePuntos.setBackground(new Color(6, 40, 61));
		textPanePuntos.setBounds(638, 98, 130, 40);
		
		textPaneTiempo = new JTextPane();
		textPaneTiempo.setEditable(false);
		textPaneTiempo.setBackground(new Color(6, 40, 61));
		textPaneTiempo.setForeground(new Color(255, 255, 0));
		textPaneTiempo.setText("TIEMPO: 00:00");
		textPaneTiempo.setFont(new Font("Arial", Font.BOLD, 14));
		textPaneTiempo.setBounds(638, 47, 130, 40);
		
		
		setVisible(true);
		initKeyBindings();
		
		getContentPane().add(panelGrilla);
		panelGrilla.setLayout(null);
		getContentPane().add(textPaneTiempo);
		
		getContentPane().add(textPanePuntos);

	}
	
	public void actualizarVentana(LinkedList<Cuerpo> posicionSnake) {  
		int posX = 0;
		int posY = 0;
		Point aux = new Point(0,0);
		if(snake.isEmpty()) {
			for(int i=0; i<posicionSnake.size(); i++) {      
				
        		JLabel cuerpo=new JLabel(new ImageIcon(posicionSnake.get(i).getSkin()));
        		snake.addLast(cuerpo);
        		panelGrilla.add(cuerpo);
        		cuerpo.setBounds(posicionSnake.get(i).getPosition().getX(), posicionSnake.get(i).getPosition().getY(),30,30);        		
			}
		}
		else {
			for(int i=0; i<snake.size(); i++) {			
				aux.setLocation(posicionSnake.get(i).getPosition().getX()*30, posicionSnake.get(i).getPosition().getY()*30);
				snake.get(i).setLocation(aux);
			}
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
	
	public void setEnte(Ente e) {
		ente = new JLabel(new ImageIcon(e.getSkin()));
		panelGrilla.add(ente);
		ente.setBounds(e.getPosition().getX()*30, e.getPosition().getY()*30, 30, 30);
		consumibles.addLast(ente);
	}
	
	public void removerEnte(Ente e) {
		consumibles.getFirst().setIcon(null);	
	}
	
	public void setTiempo() {
		textPaneTiempo.setText("TIEMPO: "+timer.getTime());
	}
	
	public void setPuntaje(int puntaje) {
		lblPuntaje.setText("PUNTAJE: "+puntaje);
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

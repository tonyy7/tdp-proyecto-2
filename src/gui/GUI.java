package gui;

import javax.swing.*;

import criatura.Cuerpo;
import ente.Ente;
import ente.EnteGrafico;
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
		panelGrilla.setBounds(10, 10, 600, 600);;
		
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
	
	public void generarGrilla() {
		LinkedList<Ente>[][] grillaLogica = juego.getGrilla();
		JLabel insert;
		for(int i=0; i<60; i++) {
			for(int j=0; j<60; j++) {
				if(grillaLogica[i][j].getFirst() != null) {
					insert = new JLabel(new ImageIcon(grillaLogica[i][j].getFirst().getSkin()));
					pared.addLast(insert);
					panelGrilla.add(insert);
					insert.setBounds((grillaLogica[i][j].getFirst().getPosition().getX())*10,(grillaLogica[i][j].getFirst().getPosition().getY())*10, 10, 10);
				}
			}
		}
	}
	
	public void actualizarCriatura(LinkedList<EnteGrafico> cuerpoSnake) {
		Point aux = new Point(0,0);
		if(snake.isEmpty()) {
			for(EnteGrafico e : cuerpoSnake) {
				JLabel cuerpo = new JLabel(new ImageIcon(e.getSkin()));
				snake.addLast(cuerpo);
				cuerpo.setBounds(e.getPosicion().getX(), e.getPosicion().getY(), 10, 10);
				panelGrilla.add(cuerpo);
			}
		}
		if(cuerpoSnake.size() > snake.size()) {
			EnteGrafico e = cuerpoSnake.getLast();
			JLabel cuerpo = new JLabel(new ImageIcon(e.getSkin()));
			snake.addLast(cuerpo);
			cuerpo.setBounds(e.getPosicion().getX(), e.getPosicion().getY(), 10, 10);
			panelGrilla.add(cuerpo);
		}
		for(int i=0; i<cuerpoSnake.size(); i++) {
			aux.setLocation(cuerpoSnake.get(i).getPosicion().getX(), cuerpoSnake.get(i).getPosicion().getY());
			snake.get(i).setLocation(aux);			
		}
	}
	
	public void setConsumible(EnteGrafico e) {
		JLabel cons = new JLabel(new ImageIcon(e.getSkin()));
		cons.setBounds(e.getPosicion().getX()*10, e.getPosicion().getY()*10, 10, 10);
		panelGrilla.add(cons);
		consumibles.addFirst(cons);
		panelGrilla.repaint();
	}
	
	public void eliminarConsumible() {
		if(!consumibles.isEmpty()) {
			panelGrilla.remove(consumibles.removeFirst());
			panelGrilla.repaint();
		}
	}

	
	public void setTiempo() {
		textPaneTiempo.setText("TIEMPO: "+timer.getTime());
	}
	
	public void setPuntaje(int p) {
		textPanePuntos.setText("PUNTAJE: "+p);
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
					case KeyEvent.VK_SPACE:
					{
						teclado.generadorPosition("espacio");
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

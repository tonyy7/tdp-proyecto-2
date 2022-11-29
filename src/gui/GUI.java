package gui;

import javax.swing.*;
import ente.EnteGrafico;
import juego.Juego;
import reloj.Reloj;
import splashScreen.SplashScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	protected Juego juego;
	protected ControlTeclado teclado;
	protected Reloj timer;
	protected JLabel lblPuntaje;
	protected JLabel ente;
	protected JLabel lblControles;
	protected JLabel lblBackground;
	protected JTextPane textPaneTiempo;
	protected JTextPane textPanePuntos;
	protected JTextPane textPaneNivel;
	protected JPanel panelGrilla;
	protected JPanel exit;
	protected Color colorTexto;
	protected Color colorFondo;
	protected Font fuenteTexto;
	protected LinkedList<JLabel> pared;
	protected LinkedList<JLabel> snake;
	protected LinkedList<JLabel> consumibles;
	
	
	public GUI(Juego juego, Reloj timer) {		
		pared = new LinkedList<JLabel>();
		snake = new LinkedList<JLabel>();
		consumibles = new LinkedList<JLabel>();
		this.juego = juego;
		this.timer = timer;
		teclado = new ControlTeclado(juego);
		colorTexto = new Color(0, 0, 0);
		colorFondo=new Color(169,169,169);
		fuenteTexto = new Font("Arial", Font.BOLD, 14);
		
		init();
	}
	
	private void init() {
		getContentPane().setBackground(colorFondo);
		setResizable(false);
		setTitle("Snake TDP 2022");
		ImageIcon icon = new ImageIcon("assets/SplashScreen/Icon.png");
		setIconImage(icon.getImage());		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(790, 620));
		setResizable(false);
		setLocationRelativeTo(null);		
		setUndecorated(true);
		
		panelGrilla = new JPanel();				
		panelGrilla.setBounds(10, 10, 600, 600);
		panelGrilla.setOpaque(false);
		panelGrilla.setLayout(null);
		
		textPanePuntos = new JTextPane();
		textPanePuntos.setText("PUNTAJE: 0");
		textPanePuntos.setForeground(colorTexto);
		textPanePuntos.setFont(fuenteTexto);
		textPanePuntos.setEditable(false);
		textPanePuntos.setBackground(colorFondo);
		textPanePuntos.setBounds(638, 98, 130, 40);
		
		textPaneTiempo = new JTextPane();
		textPaneTiempo.setEditable(false);
		textPaneTiempo.setBackground(colorFondo);
		textPaneTiempo.setForeground(colorTexto);
		textPaneTiempo.setText("TIEMPO: 00:00");
		textPaneTiempo.setFont(fuenteTexto);
		textPaneTiempo.setBounds(638, 47, 130, 40);
		
		textPaneNivel = new JTextPane();
		textPaneNivel.setText("NIVEL:");
		textPaneNivel.setForeground(colorTexto);
		textPaneNivel.setFont(fuenteTexto);
		textPaneNivel.setEditable(false);
		textPaneNivel.setBackground(colorFondo);
		textPaneNivel.setBounds(638, 149, 130, 40);
		
		lblControles = new JLabel(new ImageIcon("assets/splashScreen/controles.png"));
		lblControles.setBounds(620, 350, 150, 220);
		
		exit = new JPanel();
		exit.setOpaque(false);
		exit.setBounds(760, 5, 25, 25);
		exit.setLayout(null);
		JLabel boton=new JLabel(new ImageIcon("assets/gui/exit.png"));
		boton.setSize(25,25);
		exit.add(boton);		
		exit.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		setVisible(true);
		setFocusable(true);
		initKeyBindings();
		
		getContentPane().add(lblControles);
		getContentPane().add(panelGrilla);		
		getContentPane().add(textPaneTiempo);		
		getContentPane().add(textPanePuntos);		
		getContentPane().add(textPaneNivel);
		getContentPane().add(exit);
		
		lblBackground = new JLabel(new ImageIcon("assets/grilla/grilla.png"));
		lblBackground.setBounds(10, 10, 600, 600);		
		this.getContentPane().add(lblBackground);
	}
	
	public void generarGrilla() {
		EnteGrafico grillaGrafica[][] = juego.getGrilla();
		JLabel insert;
		for(int i=0; i<grillaGrafica.length; i++) {
			for(int j=0; j<grillaGrafica[0].length; j++) {
				if(grillaGrafica[i][j] != null) {
					insert = new JLabel(new ImageIcon(grillaGrafica[i][j].getSkin()));
					pared.addLast(insert);  
					panelGrilla.add(insert);
					insert.setBounds((grillaGrafica[i][j].getPosicion().getX())*10,(grillaGrafica[i][j].getPosicion().getY())*10, 10, 10);
				}
			}
		}
		panelGrilla.repaint();
	}
	
	public void cambiarNivel() {
		new SplashScreen(2, "assets/splashScreen/nextLevel.png");
		pared.clear();
		snake.clear();
		consumibles.clear();
		panelGrilla.removeAll();
		generarGrilla();	
		panelGrilla.repaint();
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
			ImageIcon skin = new ImageIcon(cuerpoSnake.get(i).getSkin());
			snake.get(i).setIcon(skin);
		}
	}
	
	public void setConsumible(EnteGrafico e) {			
		JLabel cons = new JLabel(new ImageIcon(e.getSkin()));
		cons.setBounds(e.getPosicion().getX()*10, e.getPosicion().getY()*10, 10, 10);
		consumibles.addFirst(cons);
		panelGrilla.add(cons);
		panelGrilla.repaint();		
	}
	
	public void eliminarConsumible() {		
		panelGrilla.remove(consumibles.removeFirst());
		panelGrilla.repaint();
	}

	public boolean consumiblesEmpty() {
		return consumibles.isEmpty();
	}
	
	public void setTiempo() {
		textPaneTiempo.setText("TIEMPO: "+timer.getTime());
	}
	
	public void setPuntaje(int p) {
		textPanePuntos.setText("PUNTAJE: "+p);
	}
	
	public void setNivel(int n) {
		textPaneNivel.setText("NIVEL: "+n);
	}
	
	public void close() {
		setVisible(false);
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
					case KeyEvent.VK_P:
					{
						teclado.generadorPosition("pausa");
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

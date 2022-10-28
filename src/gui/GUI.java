package gui;

import javax.swing.*;
import ente.EnteGrafico;
import juego.Juego;
import reloj.Reloj;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.awt.Font;
import java.awt.Point;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	protected final static int celdaSize = 30;
	protected Juego juego;
	protected ControlTeclado teclado;
	protected JLabel lblPuntaje;
	protected JLabel ente;
	protected Reloj timer;
	protected LinkedList<JLabel> pared;
	protected LinkedList<JLabel> snake;
	protected LinkedList<JLabel> consumibles;
	protected JPanel panelGrilla;
	private Color colorTexto;
	private Color colorFondo;
	private Font fuenteTexto;
	private JTextPane textPaneTiempo;
	private JTextPane textPanePuntos;
	private JTextPane textPaneNivel;
	private JLabel lblControles;
	private JLabel lblBackground;
	
	
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
		setSize(new Dimension(797, 658));
		setResizable(false);
		setLocationRelativeTo(null);		
		
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
		
		lblControles = new JLabel(new ImageIcon("assets/SplashScreen/controles.png"));
		lblControles.setBounds(620, 350, 150, 220);
		
		setVisible(true);
		initKeyBindings();
		
		getContentPane().add(lblControles);
		getContentPane().add(panelGrilla);		
		getContentPane().add(textPaneTiempo);		
		getContentPane().add(textPanePuntos);		
		getContentPane().add(textPaneNivel);
		
		lblBackground = new JLabel(new ImageIcon("assets/grilla/grillaoscura.png"));
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
		pared.clear();
		panelGrilla.removeAll();
		panelGrilla.repaint();
		snake.clear();
		generarGrilla();		
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

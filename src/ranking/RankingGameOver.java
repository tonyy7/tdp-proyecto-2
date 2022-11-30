package ranking;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import TDAColaCP.*;
import juego.Juego;

import javax.swing.JTextPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class RankingGameOver extends JFrame {
	private Ranking puntaje;
	private Juego juego;
	private static int CANT_PUNTAJES = 5;
	private JLabel lblTitulo;
	private JLabel lblGameEnd;
	private JLabel lblIntegrantes;
	private JLabel lblTituloRanking;
	private JButton btnIngresarDatos;
	private JTextPane textRanking;
	private JTextField textField;
	private Color colorTexto;
	private Color colorFondo;
	private Font fuenteTexto;
	private boolean control;
	
	public RankingGameOver(Juego juego, Ranking puntaje, boolean estado) {
		this.juego = juego;
		this.puntaje = puntaje;
		control = true;
		colorTexto = new Color(0, 0, 0);
		colorFondo =new Color(169,169,169);
		fuenteTexto = new Font("Arial", Font.BOLD, 14);
		
		init(estado);
		setVisible(true);
	}
	
	private void init(boolean estado) {
		getContentPane().setBackground(colorFondo);
		setResizable(false);
		setTitle("Snake TDP 2022 - Ranking");
		ImageIcon icon = new ImageIcon("assets/SplashScreen/Icon.png");
		setIconImage(icon.getImage());		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(500, 274));
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(36, 115, 196, 20);
		textField.setColumns(10);
		
		lblTitulo = new JLabel("Ingrese su nombre:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(36, 90, 196, 14);
		lblTitulo.setForeground(colorTexto);
		lblTitulo.setFont(fuenteTexto);
		
		btnIngresarDatos = new JButton("Ingresar datos");		
		btnIngresarDatos.setBounds(36, 146, 196, 23);
		btnIngresarDatos.setForeground(colorTexto);
		btnIngresarDatos.setFont(fuenteTexto);
		btnIngresarDatos.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(control) {
						ingresarDatos();
						control = false;
					}
				}
			}
		});
		btnIngresarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(control) {
					ingresarDatos();
					control = false;
				}
			}
		});
		
		lblTituloRanking = new JLabel("Ranking");
		lblTituloRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloRanking.setBounds(264, 23, 196, 14);
		lblTituloRanking.setForeground(colorTexto);
		lblTituloRanking.setFont(fuenteTexto);
		
		textRanking = new JTextPane();
		textRanking.setEditable(false);
		textRanking.setBounds(264, 48, 196, 137);
		if(!puntaje.cola().isEmpty())
			textRanking.setText(listaPuntaje());
		
		lblGameEnd = new JLabel("");
		lblGameEnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameEnd.setBounds(36, 48, 196, 31);
		lblGameEnd.setFont(new Font("Arial", Font.BOLD, 24));
		if(estado) {
			lblGameEnd.setForeground(new Color(0, 255, 0));
			lblGameEnd.setText("¡FELICIDADES!");
		}
		else {
			lblGameEnd.setForeground(new Color(255, 0, 0));
			lblGameEnd.setText("GAME OVER");

		}
			
		lblIntegrantes = new JLabel("Franco Leon - Benjamín Saez - Agustin Oyarzún - Antonio Sevenants");
		lblIntegrantes.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntegrantes.setBounds(36, 205, 424, 14);
		lblIntegrantes.setForeground(colorTexto);
		lblIntegrantes.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		
		getContentPane().add(lblIntegrantes);
		getContentPane().add(lblGameEnd);
		getContentPane().add(lblTitulo);
		getContentPane().add(textField);
		getContentPane().add(btnIngresarDatos);
		getContentPane().add(textRanking);
		getContentPane().add(lblTituloRanking);
		
		
	}
	
	private void ingresarDatos() {
		try {
			puntaje.insert(juego.getPuntajeActaul(), textField.getText());
			textRanking.setText(listaPuntaje());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}
	
	private String listaPuntaje() {
		String toReturn = "";
		ColaCP<Integer, String> aux = new ColaCP<Integer,String>(10, new Comparador<Integer>());
		int clave;
		String valor;
		
		try {
			for(int i=0; i<CANT_PUNTAJES; i++) {
				if(!puntaje.cola().isEmpty()) {
					clave = puntaje.cola().min().getKey();
					valor = puntaje.cola().min().getValue();
					toReturn += valor +" = "+clave+"\n";
					aux.insert(clave, valor);
					puntaje.cola().removeMin();
				}
			}
			while(!aux.isEmpty()) {
				puntaje.insert(aux.min().getKey(), aux.min().getValue());
				aux.removeMin();
			}			
			guardarPuntaje(puntaje);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return toReturn;
	}
	
	private void guardarPuntaje(Ranking r) throws Exception {
		FileOutputStream file = new FileOutputStream("ranking.r");
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(r);
	    out.close();
	    file.close();
	}
}

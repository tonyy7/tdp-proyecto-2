package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel{

	ImageIcon image;
	String nombre;
	
	public Panel (String nombre) {
		this.nombre = nombre;
	}
	
	public void paint (Graphics g) {
		Dimension tamanio = getSize();
		image = new ImageIcon(getClass().getResource(nombre));
		g.drawImage(image.getImage(), 600, 600, tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paint(g);
	}

}
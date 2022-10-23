package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel{
	ImageIcon image;
	String nombre;
	
	public Panel (String nombre) {
		this.nombre = nombre;
	}
	
	public void paint (Graphics g) {
		Dimension tamanio = getSize();
		image = new ImageIcon(getClass().getResource(nombre));
		g.drawImage(image.getImage(), 0, 0, tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paint(g);
	}

}

package factory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;

import Position.Position;
import ente.Ente;
import ente.alimento.*;
import ente.pared.Pared;
import ente.powerUp.*;
import grilla.nivel.Nivel;

public class GeneradorNivel implements FactoryLevel {
	LinkedList<Ente>[][] miNivel;
	LinkedList<Ente> consumibles;

	/*
	 * Inicializa una lista de consumibles y una grilla con sus extremos
	 * como pared. 
	 */
	public GeneradorNivel() {
		iniciarNivel();
	}
	
	@SuppressWarnings("unchecked")
	public void iniciarNivel() {
		miNivel = (LinkedList<Ente>[][]) new LinkedList[60][60];
		consumibles = new LinkedList<Ente>();
		
		for(int i = 0; i < 60; i++) { //Inicia la grilla
			for (int j = 0; j < 60; j++) {
				miNivel[i][j] = new LinkedList<Ente>();
				miNivel[i][j].addFirst(null);
			}
		}
		
		//Generan las paredes en los bordes de la grilla
		for(int i = 0; i < 60; i++) {
			miNivel[0][i].addFirst(new Pared(new Position(i,0)));
		}
		for (int i = 0; i < 60; i++) {
			miNivel[59][i].addFirst(new Pared(new Position(i,59)));
		}
		
		for(int i = 1; i < 59; i++) {
			miNivel[i][59].addFirst(new Pared(new Position(59,i)));
		}
		
		for(int i = 1; i < 59; i++) {
			miNivel[i][0].addFirst(new Pared(new Position(0,i)));
		}
		
		consumibles = new LinkedList<Ente>();
	}
	
	/*
	 * Generador de Nivel, recibe un string correspondiente a un archivo txt
	 * 1,2,3,4,5 corresponden a Alimento1, Alimento3, etc
	 * 6,7,8 corresponden a PowerUp1, PowerUp2, etc.
	 * P corresponde a Pared
	 * @return Nivel
	 */
	public Nivel generarNivel(String url) {
		Path filePath
		= Paths.get(url);
		String level;
		try {
			level = Files.readString(filePath);
			level = level.replace(" ","");
			level = level.replace("\n", "");
			String x;
			String y;
			
			for(int i = 0; i < level.length(); i++) {
				
				switch(level.charAt(i)){
				
				case 'P':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Pared p = new Pared(new Position(Integer.parseInt(x),Integer.parseInt(y)));
					miNivel[p.getPosition().getX()][p.getPosition().getY()].addFirst(p);
					i=i+6;
				}break;
			
				case '1':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Alimento1 a = new Alimento1(new Position(Integer.parseInt(x),Integer.parseInt(y)));
					consumibles.add(a);
					i=i+6;
				}break;
			
				case'2':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Alimento2 a = new Alimento2(new Position(Integer.parseInt(x),Integer.parseInt(y)));
					consumibles.add(a);
					i=i+6;
				}break;
			
				case'3':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));					
					Alimento3 a = new Alimento3(new Position(Integer.parseInt(x),Integer.parseInt(y)));
					consumibles.add(a);
					i=i+6;
				}break;
			
				case'4':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Alimento4 a = new Alimento4(new Position(Integer.parseInt(x),Integer.parseInt(y)));
					consumibles.add(a);
					i=i+6;
				}break;
			
				case'5':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Alimento5 a = new Alimento5(new Position(Integer.parseInt(x),Integer.parseInt(y)));
					consumibles.add(a);
					i=i+6;
				}break;
			
				case'6':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					PowerUp1 pu = new PowerUp1(new Position(Integer.parseInt(x),Integer.parseInt(y)));
					consumibles.add(pu);
					i=i+6;
				}break;
			
				case'7':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					PowerUp2 pu = new PowerUp2(new Position(Integer.parseInt(x),Integer.parseInt(y)));
					consumibles.add(pu);
					i=i+6;
				}break;
			
				case'8':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					PowerUp3 pu = new PowerUp3(new Position(Integer.parseInt(x),Integer.parseInt(y)));
					consumibles.add(pu);
					i=i+6;
				}break;
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Collections.shuffle(consumibles);		
		
		return new Nivel(miNivel, consumibles);
	}
	

}

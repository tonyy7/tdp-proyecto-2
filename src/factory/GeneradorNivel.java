package factory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;

import Position.Position;
import ente.Ente;
import ente.alimento.Alimento1;
import ente.alimento.Alimento2;
import ente.alimento.Alimento3;
import ente.alimento.Alimento4;
import ente.alimento.Alimento5;
import ente.pared.Pared;
import ente.powerUp.PowerUp1;
import ente.powerUp.PowerUp2;
import ente.powerUp.PowerUp3;
import grilla.nivel.Nivel;

public class GeneradorNivel implements FactoryLevel {
	LinkedList<Ente>[][] miNivel;
	LinkedList<Ente> consumibles;

	public Nivel generarNivel(String url) {
		Path filePath
		= Paths.get(url);
		String level;
		iniciar();
		try {
			level = Files.readString(filePath);
			level = level.replace("\n", "");
			String x;
			String y;
			for(int i = 0; i < level.length(); i++) {
				
				switch(level.charAt(i)){
				
					case 'p':{
						x = Integer.toString(level.charAt(i+2)) + level.charAt(i+3);
						y = Integer.toString(level.charAt(i+5)) + level.charAt(i+6);
						Pared p = new Pared(new Position(Integer.parseInt(x),Integer.parseInt(y)));
						miNivel[p.getPosition().getX()][p.getPosition().getY()].addFirst(p);
						i=i+6;
					}break;
				
					case '1':{
						x = Integer.toString(level.charAt(i+2)) + level.charAt(i+3);
						y = Integer.toString(level.charAt(i+5)) + level.charAt(i+6);
						Alimento1 a = new Alimento1(new Position(Integer.parseInt(x),Integer.parseInt(y)));
						consumibles.add(a);
						i=i+6;
					}break;
				
					case'2':{
						x = Integer.toString(level.charAt(i+2)) + level.charAt(i+3);
						y = Integer.toString(level.charAt(i+5)) + level.charAt(i+6);
						Alimento2 a = new Alimento2(new Position(Integer.parseInt(x),Integer.parseInt(y)));
						consumibles.add(a);
						i=i+6;
					}break;
				
					case'3':{
						x = Integer.toString(level.charAt(i+2)) + level.charAt(i+3);
						y = Integer.toString(level.charAt(i+5)) + level.charAt(i+6);
						Alimento3 a = new Alimento3(new Position(Integer.parseInt(x),Integer.parseInt(y)));
						consumibles.add(a);
						i=i+6;
					}break;
				
					case'4':{
						x = Integer.toString(level.charAt(i+2)) + level.charAt(i+3);
						y = Integer.toString(level.charAt(i+5)) + level.charAt(i+6);
						Alimento4 a = new Alimento4(new Position(Integer.parseInt(x),Integer.parseInt(y)));
						consumibles.add(a);
						i=i+6;
					}break;
				
					case'5':{
						x = Integer.toString(level.charAt(i+2)) + level.charAt(i+3);
						y = Integer.toString(level.charAt(i+5)) + level.charAt(i+6);
						Alimento5 a = new Alimento5(new Position(Integer.parseInt(x),Integer.parseInt(y)));
						consumibles.add(a);
						i=i+6;
					}break;
				
					case'6':{
						x = Integer.toString(level.charAt(i+2)) + level.charAt(i+3);
						y = Integer.toString(level.charAt(i+5)) + level.charAt(i+6);
						PowerUp1 pu = new PowerUp1(new Position(Integer.parseInt(x),Integer.parseInt(y)));
						consumibles.add(pu);
						i=i+6;
					}break;
				
					case'7':{
						x = Integer.toString(level.charAt(i+2)) + level.charAt(i+3);
						y = Integer.toString(level.charAt(i+5)) + level.charAt(i+6);
						PowerUp2 pu = new PowerUp2(new Position(Integer.parseInt(x),Integer.parseInt(y)));
						consumibles.add(pu);
						i=i+6;
					}break;
				
					case'8':{
						x = Integer.toString(level.charAt(i+2)) + level.charAt(i+3);
						y = Integer.toString(level.charAt(i+5)) + level.charAt(i+6);
						PowerUp3 pu = new PowerUp3(new Position(Integer.parseInt(x),Integer.parseInt(y)));
						consumibles.add(pu);
						i=i+6;
					}break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.shuffle(consumibles);
		
		return new Nivel(miNivel, consumibles);
	}
	
	
	private void iniciar() {
		for(int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				miNivel[i][j] = new LinkedList<Ente>();
				miNivel[i][j].addFirst(null);
			}
		}
		consumibles = new LinkedList<Ente>();
	}

}

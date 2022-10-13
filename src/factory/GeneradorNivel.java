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
			int x;
			int y;
			for(int i = 0; i < level.length(); i++) {
				
				switch(level.charAt(i)){
				
					case 'p':{
						x = level.charAt(i+2);
						y = level.charAt(i+2);
						Pared p = new Pared(new Position(x,y));
						miNivel[x][y].addFirst(p);
						i=i+5;
					}break;
				
					case '1':{
						x = level.charAt(i+2);
						y = level.charAt(i+2);
						Alimento1 a = new Alimento1(new Position(x,y));
						consumibles.add(a);
						i=i+5;
					}break;
				
					case'2':{
						x = level.charAt(i+2);
						y = level.charAt(i+2);
						Alimento2 a = new Alimento2(new Position(x,y));
						consumibles.add(a);
						i=i+5;
					}break;
				
					case'3':{
						x = level.charAt(i+2);
						y = level.charAt(i+2);
						Alimento3 a = new Alimento3(new Position(x,y));
						consumibles.add(a);
						i=i+5;
					}break;
				
					case'4':{
						x = level.charAt(i+2);
						y = level.charAt(i+2);
						Alimento4 a = new Alimento4(new Position(x,y));
						consumibles.add(a);
						i=i+5;
					}break;
				
					case'5':{
						x = level.charAt(i+2);
						y = level.charAt(i+2);
						Alimento5 a = new Alimento5(new Position(x,y));
						consumibles.add(a);
						i=i+5;
					}break;
				
					case'6':{
						x = level.charAt(i+2);
						y = level.charAt(i+2);
						PowerUp1 pu = new PowerUp1(new Position(x,y));
						consumibles.add(pu);
						i=i+5;
					}break;
				
					case'7':{
						x = level.charAt(i+2);
						y = level.charAt(i+2);
						PowerUp2 pu = new PowerUp2(new Position(x,y));
						consumibles.add(pu);
						i=i+5;
					}break;
				
					case'8':{
						x = level.charAt(i+2);
						y = level.charAt(i+2);
						PowerUp3 pu = new PowerUp3(new Position(x,y));
						consumibles.add(pu);
						i=i+5;
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

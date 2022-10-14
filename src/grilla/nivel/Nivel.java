package grilla.nivel;

import java.util.LinkedList;

import ente.Ente;

public class Nivel {
	protected LinkedList<Ente>[][] grilla;
	protected LinkedList<Ente> consumibles;
	
	public Nivel(LinkedList<Ente>[][] g, LinkedList<Ente> c) {
		grilla = g;
		consumibles = c;
	}
	
	public LinkedList<Ente>[][] getGrilla(){
		return grilla;
	}
	
	public Ente getconsumible(){
		Ente e = null;
		if(!consumibles.isEmpty())
			e = consumibles.removeFirst();
		return e;
	}
	
}

package factory;

import java.util.LinkedList;

import ente.Ente;

public interface FactoryLevel {
	public LinkedList<Ente>[][] generarNivel(String url);
}

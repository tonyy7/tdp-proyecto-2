package factory;

import java.util.LinkedList;

import ente.Ente;
import grilla.nivel.Nivel;

public interface FactoryLevel {
	public Nivel generarNivel(String url);
}

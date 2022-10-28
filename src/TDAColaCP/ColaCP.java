package TDAColaCP;

import java.io.Serializable;
import java.util.Comparator;

public class ColaCP<K,V> implements PriorityQueue<K,V>, Serializable {
    protected Entrada<K,V> [] elems;
    protected Comparator<K> comp;
    protected int size;

    /**
     * Crea un arreglo entradas.
     * @param maxElems tamanio del arreglo.
     * @param comp comparador de claves.
     */
    @SuppressWarnings("unchecked")
	public ColaCP(int maxElems, Comparator<K> comp) {
    	elems= (Entrada<K,V> []) new Entrada[maxElems];
    	this.comp= comp; 
    	size= 0;
    }
    
    public int size() {
    	return size;
    }
    
    public boolean isEmpty() {
    	return size==0;
    }
    
    public Entry<K,V> min() throws EmptyPriorityQueueException{
    	if(isEmpty())
    		throw new EmptyPriorityQueueException("La cola esta vacia");
    	return elems[1];
    }
    
    public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
    	checkKey(key);
    	if(size() == elems.length-1)
    		aumentarCapacidad();
    	Entrada<K,V> entrada= new Entrada<K,V>(key, value);
    	elems[++size]= entrada;
    	int i= size;
    	boolean seguir= true;
    	while(i>1 && seguir) {
    		Entrada<K,V> elemActual= elems[i];
    		Entrada<K,V> elemPadre= elems[i/2];
    		if(comp.compare(elemActual.getKey(), elemPadre.getKey())< 0) {
    			Entrada<K,V> aux= elems[i];
    			elems[i]= elems[i/2];
    			elems[i/2]= aux;
    			i/= 2;
    		}
    		else
    			seguir= false;
    	}
    	return entrada;
    }
    
    public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException("La cola esta vacia");
		Entry<K,V> entrada= min();
		if(size==1) {
			elems[1]= null;
			size= 0;
			return entrada;
		}
		else {
			elems[1]= elems[size]; 
			elems[size]= null; 
			size--;			
			int i= 1;
			boolean seguir= true;
			while(seguir) {				
				int hi= i*2;
				int hd= (i*2)+1;
				boolean tieneHijoIzq= hi <= size();
				boolean tieneHijoDer= hd <= size();
				if(!tieneHijoIzq)
					seguir= false;
				else {
					int m;
					if(tieneHijoDer) {
						if(comp.compare(elems[hi].getKey(),elems[hd].getKey())< 0)
							m= hi;
						else
							m= hd;
					}
					else
						m= hi;
				if(comp.compare(elems[i].getKey(), elems[m].getKey())> 0) {
					Entrada<K,V> aux= elems[i]; 
					elems[i]= elems[m];
					elems[m]= aux;
					i=m;
				}
				else
					seguir= false;
			    }
		    }
		}
		return entrada;
	}
    
    /**
     * Analiza una clave de tipo K.
     * @param c clave a analizar.
     * @return clave valida.
     * @throws InvalidKeyException si la clave no es comparable retorna "clave invalida".
     */
    protected K checkKey(K c) throws InvalidKeyException{
    	try {
    	    comp.compare(c, c);
    	}catch(Exception e) {
    		throw new InvalidKeyException("Clave invalida");
    	}
        return c;
    }
    
    /**
     * Aumenta la capacidad del arreglo elems, de esta forma el arreglo es no acotado.
     */     
    @SuppressWarnings("unchecked")
	private void aumentarCapacidad() {    	    	
		Entrada<K,V> [] aux = elems;
		elems = (Entrada<K,V> []) new Entrada[aux.length*5];
    	for(int i=0; i<aux.length; i++) {
    		Entrada<K,V> e = aux[i];
    		if(e != null)
    			elems[i] = e;
    	}    
    }       
}

package lib;

import java.util.ArrayList;

/*
 * Classe di supporto per la generazione degli oggetti durante la fase
 * di code generation
 * 
 * **/
public class ClassDescriptor {

	private String className;
	// Se la classe non usa il comando implements il valore di parent e' NULL
	private String parent;
	// Contiene la dimensione degli oggetti della classe
	// viene usata in caso di ereditarieta'
	private int objectSize;
	// Da implementare la lista degli attributi come lista di coppie 
	// < nome_attributo , offset >
	
	public ClassDescriptor(String n,String p) {
		className = n;
		parent = p;
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getParent() {
		return parent;
	}
	
	public int getSize() {
		return objectSize;
	}
	
}

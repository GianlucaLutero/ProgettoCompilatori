package lib;

import java.util.ArrayList;
import java.util.HashMap;

import ast.STentry;

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
	private ArrayList<HashMap<String, Integer>> attrList;
	
	public ClassDescriptor(String n,String p) {
		className = n;
		parent = p;
		attrList = new ArrayList<HashMap<String, Integer>>();
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

package lib;

import java.util.ArrayList;

/*
 * Classe di supporto per la generazione degli oggetti durante la fase
 * di code generation
 * 
 * **/
public class ClassDescriptor {

	private String className;
	private String parent;
	
	// Da implementare la lista degli attributi come lista di coppie 
	// nome attributo offset
	
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
	
}

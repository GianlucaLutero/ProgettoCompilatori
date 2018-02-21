package ast;

import java.util.ArrayList;

import lib.ClassDescriptor;
/*
 * Gestisce il sistema object oriented del linguaggio FOOL 
 * durante la fase di generazione del codice  
 * 
 */
import util.SemanticError;
public class ObjectHandler {
	
	public static boolean active = false;
	
	public static ArrayList<ClassDescriptor> classList = new ArrayList<ClassDescriptor>(); 
	
	/*
	 * Aggiunge una nuova classe alla classList
	 * */
	public static void addClass(String name,String parent) {
		ClassDescriptor cd = new ClassDescriptor(name, parent);
		
		classList.add(cd);
	}
	
	/*
	 * Resetta lo stato dell'object handler
	 * */
	public static void resetHandler() {
		classList = new ArrayList<ClassDescriptor>();
	}
	
	public static ArrayList<SemanticError> addAttribute(String name) {
		ArrayList<SemanticError> se = new ArrayList<SemanticError>();
		// Se la classe e' derivata aggiungo prima gli attributi della classe genitore 
		// e poi quelli della classe derivata
		
		
		return se;
	}

}

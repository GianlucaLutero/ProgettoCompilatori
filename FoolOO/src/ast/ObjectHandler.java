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
	 * Viene controllato se la classe NAME e' presente nella lista
	 * */
	public static boolean checkClass(String name) {
		
		for(ClassDescriptor cd : classList) {
			if(cd.getClassName() == name)
				return true;
		}
		
		return false;
	}
	
	/*
	 * Aggiunge una nuova classe alla classList
	 * */
	public static void addClass(String name,String parent) {
		
		SemanticError semanticError;
		
		ClassDescriptor cd = new ClassDescriptor(name, parent);
		
		classList.add(cd);
	}
	
	/*
	 * Resetta lo stato dell'object handler
	 * */
	public static void resetHandler() {
		classList = new ArrayList<ClassDescriptor>();
	}
	
	public static void addAttribute(String name,String id,int offset) {
		// Se la classe e' derivata aggiungo prima gli attributi della classe genitore 
		// e poi quelli della classe derivata
		
		
	}
	
	public static void addMethod(String name) {
		
	}

}

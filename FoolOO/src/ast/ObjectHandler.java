package ast;

import java.util.ArrayList;
import java.util.HashMap;

import lib.ClassDescriptor;
/*
 * Gestisce il sistema object oriented del linguaggio FOOL 
 * durante la fase di generazione del codice  
 * 
 */
import util.SemanticError;
public class ObjectHandler {
	
	public static boolean active = false;
	
	private static ArrayList<ClassDescriptor> classList = new ArrayList<ClassDescriptor>(); 
	
	/*
	 * Viene controllato se la classe NAME e' presente nella lista
	 * */
	public static boolean checkClass(String name) {
		
		for(ClassDescriptor cd : classList) {
			if(cd.getClassName().equals(name) )
				return true;
		}
		
		return false;
	}
	
	public static ClassDescriptor getClass(String name) {
		for(ClassDescriptor c:classList) {
			if (c.getClassName().equals(name))
				return c;
		}
		
		return null;
	}
	
	/*
	 * Aggiunge una nuova classe alla classList
	 * */
	public static SemanticError addClass(String name,String parent,HashMap<String, Integer> attr) {
		
		SemanticError semanticError = new SemanticError("");
		
		ClassDescriptor cd = new ClassDescriptor(name, parent);
		
		String tmpPar = parent;
		
		int tmpSize = attr.size();
		
		// Aggiungo i parametri al class descriptor
		if(parent == null) {
			
			cd.setSize(tmpSize);

			System.out.println("Class "+ name + " size is " + tmpSize);
		}else {
			// Se la nuova classe e` derivata risalgo tutta la catena di derivazione
			// per calcolare la dimensione effettiva dei nuovi oggetti
			while(tmpPar != null) {
				for (ClassDescriptor classDescriptor : classList) {
				    // Aggiungere gli attributi della classe da cui si deriva
					if(classDescriptor.getClassName().equals(tmpPar)) {
						tmpSize += classDescriptor.getSize();
						tmpPar = classDescriptor.getParent();
						break;
					}
				}
				
			}
			
					
			// Riaggiusto gli offset dei nuovi parametri
			final int size = tmpSize;
			attr.replaceAll((key,oldValue) -> oldValue - size);
		
			cd.setSize(size);

			System.out.println("Class "+ name + " size is " + size);
		}
		
		
		cd.setAttrList(attr);
		classList.add(cd);
		
		return semanticError;
	}
	
	/*
	 * Resetta lo stato dell'object handler
	 * */
	public static void resetHandler() {
		classList = new ArrayList<ClassDescriptor>();
	}
	
	public static void addAttribute(String className,String id,int offset) {
		// Se la classe e' derivata aggiungo prima gli attributi della classe genitore 
		// e poi quelli della classe derivata
		
		
	}
	
	public static void addMethods(String className,ArrayList<String> mname) {
		
		for(ClassDescriptor cd : classList) {
		   if(cd.getClassName().equals(className)) {
			   cd.setMethodList(mname);
		   }
		}
		
	}

}

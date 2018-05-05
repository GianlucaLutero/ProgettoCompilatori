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
	private int objectSize;
	// Da implementare la lista degli attributi come lista di coppie 
	// < nome_attributo , offset >
	private HashMap<String, Integer> attrList;
	
	private ArrayList<String> mList;
	
	public ClassDescriptor(String n,String p) {
		className = n;
		parent = p;
		attrList = new HashMap<String, Integer>();
		mList = new ArrayList<>();
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
	
	public HashMap<String, Integer> getAttList(){
		return attrList;
	}
	
	public void setAttrList(HashMap<String, Integer> att) {
		attrList.putAll(att);
		
		//DEBUG Show attr list
		System.out.println("Attribute list: "+att.toString());
		
	}
	
	public void setSize(int s) {
		objectSize = s;
	}
	
	public void setMethodList(ArrayList<String> ml) {
		mList.addAll(ml);
		
		//DEBUG: Show method list
		System.out.println("Method List: "+ ml.toString());
		
		// Per ricavare il nome della classe dal metodo
		/*
		for(String s: ml) {
			String[] ss = s.split("_");
			System.out.println(ss[ss.length-1]);
		}
		*/
	}
}

package lib;

import ast.*;

public class FOOLlib {
  
  private static int labCount=0; 
  
  private static int funLabCount=0; 

  private static String funCode=""; 

  //valuta se il tipo "a" � <= al tipo "b", dove "a" e "b" sono tipi di base: int o bool
  public static boolean isSubtype (Node a, Node b) { 
	
	boolean objectSubtype = false;
	
	// Viene fatto il controllo solo con elementi di tipo ObjectType
	// Class a <: Class b se :
	//			Class a = Class b
	//			Esiste Class c. Class a <: Class c && Class c <: Class b
	//			Esiste Class c. Class a <: Class c && Class b <: Class c
	
	if(a instanceof ObjectTypeNode && b instanceof ObjectTypeNode) {
		
		ClassDescriptor c = ObjectHandler.getClass(((ObjectTypeNode)a).getType());
		
		if(c.getClassName().equals(((ObjectTypeNode)b).getType()))
			return true;
		
		// Risalgo la catena di ereditarieta' per verificare il subtyping
		while(c.getParent() != null) {
			
			
			ClassDescriptor d = ObjectHandler.getClass(((ObjectTypeNode)b).getType());
			
			while(d != null) {
			
				if(c.getParent().equals(d.getClassName())) {
					//objectSubtype = true;
					return true;
					//break;
				}
				
				d = ObjectHandler.getClass(d.getParent());
			}
		
			
			c = ObjectHandler.getClass(c.getParent());
			
			
		}
		
		
		
		
		return objectSubtype;
	}
	
    return a.getClass().equals(b.getClass()) ||
    	   ( (a instanceof BoolTypeNode) && (b instanceof IntTypeNode) ); //
  } 
  
  public static String freshLabel() { 
	return "label"+(labCount++);
  } 

  public static String freshFunLabel() { 
	return "function"+(funLabCount++);
  } 
  
  public static void putCode(String c) { 
    funCode+="\n"+c; //aggiunge una linea vuota di separazione prima di funzione
  } 
  
  public static String getCode() { 
    return funCode;
  } 
  
  // Resetta lo stato di FOOLlib
  public static void reset() {
	  labCount = 0;
	  funLabCount = 0;
	  funCode = "";  
  }


}
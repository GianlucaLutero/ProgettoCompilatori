package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class ObjectTypeNode  implements Node{
	// classe per definire il tipo "oggetto di una classe"
	private String type; //tipo dell'oggetto
	
	 public ObjectTypeNode (String t) {
		 this.type = t;
	  }
	  
	 public String getType(){
		 return type;
	 }
	  public String toPrint(String s) {
		return s+"ObjectType: " +type+"\n";  
	  }
	    
	  //non utilizzato
	  public Node typeCheck() {
		  
	    return null;
	  }

	  @Override
	  public ArrayList<SemanticError> checkSemantics(Environment env) {

		  return new ArrayList<SemanticError>();
	  }

	  //non utilizzato
	  public String codeGeneration() {
		  return "";
	  }

}

package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class ParNode implements Node {
// parametri
  private String id;
  private Node type;
  
  public ParNode (String i, Node t) {
   id=i;
   type=t;
  }
  
  public String getId(){
	  return id;
  }
  
  public Node getType(){
	  return type;
  }
  
  @Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
	  ArrayList<SemanticError> se = new ArrayList<SemanticError>();

	  if( type instanceof ObjectTypeNode){ 
		  if( !ObjectHandler.checkClass(((ObjectTypeNode) type).getType() )) 
				  se.add(new SemanticError("class " + ((ObjectTypeNode) type).getType() + " not declared"));}
	  
	  return se;
	}
  
  public String toPrint(String s) {
	  return s+"Par:" + id +"\n"
			 +type.toPrint(s+"  ") ; 
  }
  
  //non utilizzato
  public Node typeCheck () {
     return null;
  }
  
  //non utilizzato
  public String codeGeneration() {
		return "";
  }
    
}  
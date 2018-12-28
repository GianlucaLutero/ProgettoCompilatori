package ast;
import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class ArrowTypeNode implements Node {

// TIPO DELLE FUNZIONE 
  private ArrayList<Node> parlist; 
  private Node ret;
  
  public ArrowTypeNode (ArrayList<Node> p, Node r) {
    parlist=p;
    ret=r;
  }
    
  public String toPrint(String s) { //
	String parlstr="";
    for (Node par:parlist)
      parlstr+=par.toPrint(s+"  ");
	return s+"ArrowType\n" + parlstr + ret.toPrint(s+"  ->") ; 
  }
  
  public Node getRet () { //
    return ret;
  }
  
  public String getRetId() {
	  if(ret instanceof ObjectTypeNode) return ((ObjectTypeNode)ret).getType(); // <-- NEW 
	  if(ret instanceof IntTypeNode) return "int";
	  if(ret instanceof BoolTypeNode) return "bool";
	  
	  return "void";
  }
  
  public ArrayList<Node> getParList () { //
    return parlist;
  }

  @Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		return new ArrayList<SemanticError>();
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
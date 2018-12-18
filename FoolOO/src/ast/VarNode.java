package ast;

import java.util.ArrayList;
import java.util.HashMap;

import util.Environment;
import util.SemanticError;
import lib.FOOLlib;

public class VarNode implements Node {
// dichiarazione di una variabile di qualsiasi tipo
  private String id;
  private Node type;
  private Node exp;
  private String dType;
  
  public VarNode (String i, Node t, Node v) {
    id=i;
    type=t;
    exp=v;
  }
  
  	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
  	//create result list
  	  ArrayList<SemanticError> res = new ArrayList<SemanticError>();
  	  
  	  //env.offset = -2;
  	  HashMap<String,STentry> hm = env.symTable.get(env.nestingLevel);
        STentry entry = new STentry(env.nestingLevel,type, env.offset--); //separo introducendo "entry"
        
        if(type instanceof ObjectTypeNode) {
        	entry.addDecType(new ObjectTypeNode(dType));
        }
        
        if ( hm.put(id,entry) != null )
          res.add(new SemanticError("Var id "+id+" already declared"));
        
        res.addAll(exp.checkSemantics(env));
        
        return res;
	}
  
  public String toPrint(String s) {
	return s+"Var:" + id +"\n"
	  	   +type.toPrint(s+"  ")  
           +exp.toPrint(s+"  "); 
  }
  
  //valore di ritorno non utilizzato
  public Node typeCheck () {
	
//	Node decType = exp.typeCheck();
	  
	  
    if (! (FOOLlib.isSubtype(exp.typeCheck(),type)) ){      
      System.out.println("incompatible value for variable "+id);
      throw new Error("Incompatible value for variable "+id);
      //System.exit(0);
    }     

    return type;
  }
  
  public String codeGeneration() {
		return exp.codeGeneration();
  }  
  
  public void addDtype(String t) {
	  dType = t;
  }
    
}  
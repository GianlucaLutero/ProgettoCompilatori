package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import lib.ClassDescriptor;
import util.Environment;
import util.SemanticError;

public class IdNode implements Node {

  private String id;
  private STentry entry;
  private int nestinglevel;
  
  public IdNode (String i) {
    id=i;
  }
  
  public String toPrint(String s) {
	return s+"Id:" + id + " at nestlev " + nestinglevel +"\n" + entry.toPrint(s+"  ") ;  
  }
  
  @Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
	  
	  //create result list
	  ArrayList<SemanticError> res = new ArrayList<SemanticError>();
	  
	  int j=env.nestingLevel;
	  STentry tmp=null; 
	  while (j>=0 && tmp==null)
		  tmp=(env.symTable.get(j--)).get(id);
      if (tmp==null) {
          
          if(!ObjectHandler.lastCall.equals("main")) {
        	  System.out.println("Sono dentro la classe: "+ ObjectHandler.lastCall);
        	  System.out.println("Cerco l'id "+id+" tra gli attributi di "+ObjectHandler.lastCall);
        	  
        	  ClassDescriptor c = ObjectHandler.getClass(ObjectHandler.lastCall);
        	  HashMap<String, Integer> attr = c.getAttList();
        	  HashMap<String, Node> attrType = c.getAttType();
        	  int offset;
        	  Node aType;
        	  
        	  if(attr.get(id) != null) {
        		
        		  offset = attr.get(id);
        		  aType = attrType.get(id);
        		
        		  System.out.println("Attributo "+ id + " trovato");
        		  
        		  entry = new STentry(env.nestingLevel, aType, offset);
        		  nestinglevel = env.nestingLevel;
        		  entry.setAttribute();
        	  }else{
        		  
        		   res.add(new SemanticError("Id "+id+" not declared")); 		  
        	  
        	  }
        	  
        	  
          }else {
        	  res.add(new SemanticError("Id "+id+" not declared"));
          }
          
      }else{
    	  entry = tmp;
    	  nestinglevel = env.nestingLevel;
      }
	  
	  return res;
	}
  
  public Node typeCheck () {
	if (entry.getType() instanceof ArrowTypeNode) { //
	  System.out.println("Wrong usage of function identifier");
      System.exit(0);
    }	 
    return entry.getType();
  }
  
  public String codeGeneration() {
      String getAR="";
      String sh = "";
      String ad = "";
      String offst ="push "+entry.getOffset()+"\n";
      
      System.out.println("Offset della variabile "+id+":" + entry.getOffset());
      if(ObjectHandler.lastCall.equals("main")) {
    	  offst = "push "+entry.getOffset()+"\n";
    	  sh = "lfp\n";
    	  ad = "add\n";
    	  
    	  if(entry.isAttribute()) {
    		  System.out.println("La variabile "+id+ " e' un'attributo");
    		  offst = "push "+entry.getOffset()+"\n"+"lfp\n"+"sub\n" ;
    	  }
    	  
      }else {
    	  sh = new ThisExpNode().codeGeneration();
    	  ad = "sub\n";
    	  
      }
      
	  for (int i=0; i<nestinglevel-entry.getNestinglevel(); i++) 
	    	 getAR+="lw\n";
	    return //"push "+entry.getOffset()+"\n"+ //metto offset sullo stack
		       offst+
		       sh+getAR+ //risalgo la catena statica
			   ad+ 
               "lw\n"; //carico sullo stack il valore all'indirizzo ottenuto

  }
}  
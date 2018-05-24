package ast;

import java.util.ArrayList;

import lib.ClassDescriptor;
import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

public class MethodExpNode implements Node {
	
	private String id;
	// Dall'id del caller ricavo il puntatore this
	private String caller;
	private ArrayList<Node> parlist;
	private STentry methodNode;
	private STentry objectNode;
	private int nestinglevel;
	
	
	public MethodExpNode(String c, String i, ArrayList<Node> p) {
		caller = c;
		id = i;
		parlist = p;
	}

	@Override
	public String toPrint(String indent) {
		String methodxp = "";
		
		methodxp = indent + caller + " calls method " + id; 
		
		return methodxp;
	}

	@Override
	public Node typeCheck() {
		// controllo del tipo dell'oggetto
		//controllo se il metodo � un ArrowType
		// controllo del numero dei parametri del metodo
		// controllo del tipo dei parametri del metodo
		
		ObjectTypeNode o=null; 
	     if (objectNode.getType() instanceof ObjectTypeNode) o=(ObjectTypeNode) objectNode.getType(); 
	     else {
	       System.out.println("Invocation of a non-object "+caller); // da sistemare messaggio errore
	       System.exit(0);
	     }
		
		ArrowTypeNode m=null;
	     if (methodNode.getType() instanceof ArrowTypeNode) m=(ArrowTypeNode) methodNode.getType(); 
	     else {
	       System.out.println("Invocation of a non-method "+id);
	       System.exit(0);
	     }
	     
	     ArrayList<Node> p = m.getParList();
	     if ( !(p.size() == parlist.size()) ) {
	       System.out.println("Wrong number of parameters in the invocation of "+id);
	       System.exit(0);
	     } 
	     for (int i=0; i<parlist.size(); i++) 
	       if ( !(FOOLlib.isSubtype( (parlist.get(i)).typeCheck(), p.get(i)) ) ) {
	         System.out.println("Wrong type for "+(i+1)+"-th parameter in the invocation of "+id);
	         System.exit(0);
	       } 
		
		return m.getRet();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		int j=env.nestingLevel;
		STentry objCaller= null; 
		STentry method = null;
		boolean foundMethod = false;
		
		// cerco nella symbol table se l'oggetto � stato dichiarato
		while (j>=0 && objCaller==null){
			objCaller=(env.symTable.get(j--)).get(caller);
		}
		
		if (objCaller==null) { // se l'oggetto chiamante non � stato dichiarato
			 res.add(new SemanticError("Caller "+ caller +" not declared"));		 
		} else {
			objectNode = objCaller;
			ObjectTypeNode obj = (ObjectTypeNode) objectNode.getType();
			String tipo = obj.getType();
			//verifico se esiste la classe dell'oggetto
			boolean foundClass = ObjectHandler.checkClass(tipo);
			ClassDescriptor objClassDescr = null;
			
			if(foundClass){
				objClassDescr = ObjectHandler.getClass(tipo);
				// controllo se la classe ha quel metodo
				//TO DO: controllare anche nella parent class!!!!
				ArrayList<String> methodList = objClassDescr.getMethodList();
				for(String s : methodList){
					if(s.equals(id)){
						foundMethod=true;
					}
				}
			} else {
				res.add(new SemanticError("Class" + tipo + " not found"));
			}
			
		 }
		
		if(!foundMethod){ // se il metodo non esiste
			res.add(new SemanticError("Method "+ id +" not declared"));
		} else {
			
			j=env.nestingLevel;
			while (j>=0 && method==null){
				method=(env.symTable.get(j--)).get(caller);
			}
			methodNode= method;
			
			// il metodo esiste e quindi controllo la semantica dei parametri del metodo
			for(Node arg : parlist)
			 res.addAll(arg.checkSemantics(env));
		}
		nestinglevel = env.nestingLevel;
		return res;
	}

	@Override
	public String codeGeneration() {
		
		String objectCode= "";
		
		String parCode="";
	    for (int i=parlist.size()-1; i>=0; i--)
	    	parCode+=parlist.get(i).codeGeneration();
	    
	    String getAR="";
		  for (int i=0; i< nestinglevel - objectNode.getNestinglevel(); i++) 
		    	 getAR+="lw\n";
	    
		return "";
	}
}

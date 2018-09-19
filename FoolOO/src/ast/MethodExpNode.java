package ast;

import java.util.ArrayList;
import java.util.HashMap;

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
		// controllo se il metodo è un ArrowType
		// controllo del numero dei parametri del metodo
		// controllo del tipo dei parametri del metodo

		ObjectTypeNode o=null; 
		if (objectNode.getType() instanceof ObjectTypeNode) o=(ObjectTypeNode) objectNode.getType(); 
		else {
			throw new Error("Invocation of a non-object "+caller); // da sistemare messaggio errore
			//System.exit(0);
		}

		ArrowTypeNode m=null;
		if (methodNode.getType() instanceof ArrowTypeNode) m=(ArrowTypeNode) methodNode.getType(); 
		else {

			throw new Error("Invocation of a non-method "+id);
			//System.exit(0);
		}

		ArrayList<Node> p = m.getParList();
		if ( !(p.size() == parlist.size()) ) {
			throw new Error("Wrong number of parameters in the invocation of "+id);
			//System.exit(0);
		} 
		for (int i=0; i<parlist.size(); i++) 
			if ( !(FOOLlib.isSubtype( (parlist.get(i)).typeCheck(), p.get(i)) ) ) {
				throw new Error("Wrong type for "+(i+1)+"-th parameter in the invocation of "+id);
				//System.exit(0);
			} 

		return m.getRet();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		HashMap<String, Integer> attributes = null;
		int j=env.nestingLevel;
		STentry objCaller= null; 
		STentry method = null;
		boolean foundMethod = false;
		String completeName = "";
	
			
		// cerco nella symbol table se l'oggetto è stato dichiarato
		if(caller.equals("this")) {
		
			System.out.println("this call is of type "+ObjectHandler.lastCall);
		//	caller = ObjectHandler.lastCall;
			System.out.println("This is calling "+id);
			objCaller = new STentry(nestinglevel, new ObjectTypeNode(ObjectHandler.lastCall), -2);
			
		}else {
			
		//	ObjectHandler.lastCall = caller;
			while (j>=0 && objCaller==null){
				objCaller=(env.symTable.get(j--)).get(caller);
			}
			
		}

	
		
		if (objCaller==null) { // se l'oggetto chiamante non è stato dichiarato
			 res.add(new SemanticError("Caller "+ caller +" not declared"));		 
		} else {
			objectNode = objCaller;
			//verifico se esiste la classe dell'oggetto
			ObjectTypeNode obj = (ObjectTypeNode) objectNode.getType();
			String tipo = obj.getType();
			ClassDescriptor objClassDescr = ObjectHandler.getClass(tipo);
			
			boolean foundClass = ObjectHandler.checkClass(tipo);
			
			
			if(foundClass){
	
				// controllo se la classe ha quel metodo
				//TO DO: controllare anche nella parent class!!!!
				while(objClassDescr != null) { 
					
					completeName = id + "_"+tipo;
					
					ArrayList<String> methodList = objClassDescr.getMethodList();
					for(String s : methodList){
						if(s.equals(completeName)){
							foundMethod=true;
						}
					}
					
					if(foundMethod) {
						id = id +"_"+tipo;
						attributes = objClassDescr.getAttList();
						break;
					}else {
						System.out.println("Search in parent!!");
						tipo = objClassDescr.getParent();
						objClassDescr = ObjectHandler.getClass(objClassDescr.getParent());
					}
				}
			} else {
				res.add(new SemanticError("Class " + tipo + " not found"));
			}
			
		 }
		
		if(!foundMethod){ // se il metodo non esiste		
			res.add(new SemanticError("Method "+ id+" not declared"));
		} else {
			
			j=env.nestingLevel;
			while (j>=0 && method==null){
				method=(env.symTable.get(j--)).get(id);
			}
			
			//method = (env.symTable.get(0)).get(id);
			
			methodNode= method;
			
			// attributes.forEach((key,value) ->parlist.add( new IdNode(key)));
			// il metodo esiste e quindi controllo la semantica dei parametri del metodo
			System.out.println("Lista parametri: "+ parlist);
			for(Node arg : parlist)
			res.addAll(arg.checkSemantics(env));
			
			
		}
		nestinglevel = env.nestingLevel;
		
		ObjectHandler.lastEntry = objectNode;
		
		return res;
	}

	@Override
	public String codeGeneration() {
		
		String objectCode= Integer.toString(objectNode.getOffset());
		
		String parCode="";
	    for (int i=parlist.size()-1; i>=0; i--)
	    	parCode+=parlist.get(i).codeGeneration();
	    System.out.println("Object pointer: "+objectCode);
	    System.out.println("Method offset: "+(methodNode.getOffset()+1));
	    System.out.println("Parameter list: "+parCode);
	    
	    String getAR="";
		  for (int i=0; i< nestinglevel - objectNode.getNestinglevel(); i++) 
		    	 getAR+="lw\n";
		  
	    //System.out.println("AR: "+getAR);
		  
		return 	"lfp\n"+
				parCode + "\n"+
				"push " + objectCode + "\n" +
		//		"lfp\n"+getAR+
				"push "+(methodNode.getOffset()+1)+"\n"+
				"lfp\n"+getAR+ //risalgo la catena statica							( push $fp + ?? )
				"add\n"+
	            "lw\n"+ //carico sullo stack il valore all'indirizzo ottenuto		
			    "js\n";	//jump to instruction pointed by top of stack and store next instruction in ra
	}
}

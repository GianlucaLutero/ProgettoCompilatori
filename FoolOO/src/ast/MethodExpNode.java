package ast;

import java.util.ArrayList;

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
		//controllo se il metodo è un ArrowType
		// controllo del numero dei parametri del metodo
		// controllo del tipo dei parametri del metodo
		
		Node o=null; // impostare il tipo dell'oggetto
	     if (objectNode.getType() instanceof Node) o=/*(ObjectTypeNode)*/ objectNode.getType(); 
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
		STentry objCaller=null; 
		STentry method=null;
		boolean foundMethod = false;
		
		// cerco nella symbol table se l'oggetto è stato dichiarato
		while (j>=0 && objCaller==null){
			objCaller=(env.symTable.get(j--)).get(caller);
		}
		
		if (objCaller==null) { // se l'oggetto chiamante non è stato dichiarato
			 res.add(new SemanticError("Caller "+ caller +" not declared"));		 
		} else {
			objectNode = objCaller;
			 // TO DO: controllo se nella classe è effettivamente presente il metodo richiamato
			// se trovo il metodo imposto foundMethod a true	
			// dalla symbol table prendi il tipo dell'oggetto da STentry e ritorna un nodo di tipo ObjectTypeNode
			// dal quale mi prendo la classe di appartenenza
			//dall'arraylist di objectHandler prendo il classdescriptor della classe e controllo se contiene il metodo richiamato
					
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
		
		return res;
	}

	@Override
	public String codeGeneration() {
		
		String objectCode= "";
		
		String parCode="";
	    for (int i=parlist.size()-1; i>=0; i--)
	    	parCode+=parlist.get(i).codeGeneration();
	    
		return "";
	}
}

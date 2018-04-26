package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class MethodExpNode implements Node {
	
	String id;
	// Dall'id del caller ricavo il puntatore this
	String caller;
	
	
	public MethodExpNode(String c, String i) {
		caller = c;
		id = i;
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
		// controllo del numero dei parametri del metodo
		// controllo del tipo dei parametri del metodo
		return new BoolTypeNode();
	}

	@Override
	public String codeGeneration() {
		// vai a prendere lista argomenti da ObjectHandler
		return "";
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// controllo se l'oggetto è stato dichiarato
		// controllo se esiste il metodo nella classe 
		// e poi richiamo checkSemantics sugli argomenti del metodo 
		
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		int j=env.nestingLevel;
		STentry objCaller=null; 
		boolean foundMethod = false;
		
		// cerco nella symbol table se l'oggetto è stato dichiarato
		while (j>=0 && objCaller==null){
			objCaller=(env.symTable.get(j--)).get(caller);
		}
		
		if (objCaller==null) { // se l'oggetto chiamante non è stato dichiarato
			 res.add(new SemanticError("Caller "+ caller +" not declared"));		 
		} else {
			 // TO DO: controllo se nella classe è effettivamente presente il metodo richiamato
			// se trovo il metodo imposto foundMethod a true	
			// dalla simol table prendi il tipo dell'oggetto da STentry
		 }
		
		if(!foundMethod){ // se il metodo non esiste
			res.add(new SemanticError("Method "+ id +" not declared"));
		} else {
			// il metodo esiste e quindi controllo la semantica dei parametri del metodo
			/*for(Node arg : parlist)
			 res.addAll(arg.checkSemantics(env));*/
		}
		
		/*this.entry = tmp;
		 this.nestinglevel = env.nestingLevel;*/
		
		return res;
	}

}

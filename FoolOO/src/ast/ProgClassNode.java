package ast;

import java.util.ArrayList;
import java.util.HashMap;

import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

public class ProgClassNode implements Node{

	private ArrayList<Node> declist;
	private Node exp;
	
	public  ProgClassNode(ArrayList<Node> d, Node e) {
		declist = d;
		exp = e;
	}
	
	@Override
	public String toPrint(String indent) {
        String declstr = "";
        
        for (Node dec:declist)
            declstr+=dec.toPrint(indent +"  ");
        
        return "ProgClassNode\n" + declstr + exp.toPrint(indent+"  ") ;
        
	}

	@Override
	public Node typeCheck() {
		// Controllo prima il tipo della dichiarazione di una classe
		// e poi dell'espressione
		for(Node cl:declist) {
			cl.typeCheck();
		}	
		return exp.typeCheck();
	}

	@Override
	public String codeGeneration() {
		// Genero prima il codice per le classi e poi
		// per l'espressione
		
		// Alla fine il programma cancella il contenuto dello heap

		String res = "";
		
		for(Node n:declist) {
			res += n.codeGeneration();
		}
		
		return  res +
				exp.codeGeneration() ;
//		        "\n"+
//				FOOLlib.getCode();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// Controllo prima la semantica delle dichiarazioni delle classi
		// e poi per l'espressione 
		
	    HashMap<String,STentry> hm = new HashMap<String,STentry> ();
	    env.symTable.add(hm);
	    env.nestingLevel ++;
	    ObjectHandler.resetHandler();
	    // Dichiaro l'array degli errori semantici
	    ArrayList<SemanticError> se = new ArrayList<SemanticError>();
	    
	    // Controllo la semantica nella dichiarazione delle classi
	    if(declist.size() > 0) {
	    	env.offset = -2;
	    	
	    	for(Node c : declist) {
	    	  	
		    	se.addAll(c.checkSemantics(env));
		    }	
	    }
	    
	    //Test: cambiare nome main
	    ObjectHandler.lastCall = "main";
	    // Controllo la semantica del corpo del programma
	    se.addAll(exp.checkSemantics(env));
	    	    
		return se;
	}

}

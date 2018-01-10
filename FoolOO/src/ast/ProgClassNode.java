package ast;

import java.util.ArrayList;

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
        
        return indent+"ProgClassNode\n" + declstr + exp.toPrint(indent+"  ") ;
        
	}

	@Override
	public Node typeCheck() {
		// TODO Auto-generated method stub
		// Controllo prima il tipo della dichiarazione di una classe
		// e poi dell'espressione
		for(Node cl:declist) {
			cl.typeCheck();
		}	
		return exp.typeCheck();
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		// Genero prima il codice per le classi e poi
		// per l'espressione
		return "halt\n";
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		// Controllo prima la semintica delle dichiarazioni delle classi
		// e poi per l'espressione 
		return exp.checkSemantics(env);
	}

}

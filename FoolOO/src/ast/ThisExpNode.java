package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parser.FOOLParser.FunContext;
import parser.FOOLParser.VardecContext;
import util.Environment;
import util.SemanticError;

public class ThisExpNode implements Node{
	
	private STentry entry; // entry point oggetto
	String classDef; //Da testare
	
	public ThisExpNode() {
		classDef = ObjectHandler.lastCall;
	}
	
	@Override
	public String toPrint(String s) {
		 return s+"this\n";
	}


	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();		

		entry = ObjectHandler.lastEntry;
		
		
		
		return res;
	}
	
	@Override
	public String codeGeneration() {
		return  "push "+ entry.getOffset() +"\n" + //metto offset sullo stack			
				"lfp\n"+ //risalgo la catena statica							( push $fp + ?? )
				
				"add\n"+															
				"lw\n"; //carico sullo stack il valore all'indirizzo ottenuto		
	}


	@Override
	public Node typeCheck() {
		
		System.out.println("This expression of type: "+ObjectHandler.lastCall);
		return new ObjectTypeNode(classDef);
		
		//return entry.getType();
	}

}

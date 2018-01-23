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
		// TODO Auto-generated method stub
		return new BoolTypeNode();
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		return res;
	}

}

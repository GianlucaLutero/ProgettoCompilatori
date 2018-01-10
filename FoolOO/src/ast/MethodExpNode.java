package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class MethodExpNode implements Node {
	
	String id;
	
	public MethodExpNode(String i) {
		id = i;
	}

	@Override
	public String toPrint(String indent) {
		String methodxp = "";
		
		methodxp = indent + "Call method " + id; 
		
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

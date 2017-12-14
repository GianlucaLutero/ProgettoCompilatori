package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class ClassdecNode implements Node {
	
	String className;
	ArrayList<Node> classAttr;
	
	public  ClassdecNode(String cName,ArrayList<Node> attr) {
		
		className = cName;
		classAttr = attr;
	
	}

	@Override
	public String toPrint(String indent) {
		// TODO Auto-generated method stub
		String classAst = indent + "Declared " + className+"\n";
		
	/*	
		for(Node n: classAttr) {
			classAst += n.toPrint(indent + "  ");
		}
	*/	
		
		return classAst;
	}

	@Override
	public Node typeCheck() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		return null;
	}

}

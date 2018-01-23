package ast;

import java.util.ArrayList;
import java.util.List;

import parser.FOOLParser.FunContext;
import parser.FOOLParser.VardecContext;
import util.Environment;
import util.SemanticError;

public class ClassdecNode implements Node {
	
	String className;
	String parent;
//	ArrayList<Node> classAttr;
	ArrayList<Node> classAttr;
	ArrayList<Node> methodList;
	
	public  ClassdecNode(String cName,String pName,ArrayList<Node> list,ArrayList<Node> m) {
		
		className = cName;
		parent = pName;
		classAttr = list;
		methodList = m;
	
	}

	@Override
	public String toPrint(String indent) {
		// TODO Auto-generated method stub
		String classAst = indent + "Class " + className+"\n";
		
		if(parent != null)
			classAst += indent + "imlements "+ parent+"\n";
		
		
		
		for(Node n: classAttr) {
			classAst += indent + "  "+n+"\n";
		}
		
		for(Node fc: methodList) {
			classAst += indent + "  "+ fc+"\n";
		}
		
		
		return classAst;
	}

	@Override
	public Node typeCheck() {
		// TODO Auto-generated method stub
		// controllo che il tipo degli attributi dichiarati
		// e dei metodi siano corretti
		
		return null;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		// genero il codice per i metodi
		return null;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		// salvo il template della classe in ObjectHandler
		
		return null;
	}

}

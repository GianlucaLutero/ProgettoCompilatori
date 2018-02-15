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
	ArrayList<Node> classAttr = new ArrayList<>();
	ArrayList<Node> methodList;
	
	public  ClassdecNode(String cName,String pName,ArrayList<Node> m) {
		
		className = cName;
		parent = pName;
		//classAttr = list;
		methodList = m;
	
	}
	
	public void addAttr(Node p) {
		classAttr.add(p);
	}

	@Override
	public String toPrint(String indent) {
		// TODO Auto-generated method stub
		String classAst = indent + "Class " + className+"\n";
		
		if(parent != null)
			classAst += indent + "implements "+ parent+"\n";
		
		
		for(Node n: classAttr) {
		//	classAst += indent + "  "+n.type().getText()+" "+n.ID().getText()+"\n";
	    	classAst += n.toPrint(indent + " ");
		}
	
		for(Node fc: methodList) {
			//classAst += indent + "  "+ fc.ID().getText()+"\n";
	//		classAst += fc.toPrint(indent + " ");
			
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
		String codeClass = "";
		
		
		return null;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		// salvo il template della classe in ObjectHandler
		
		return null;
	}

}

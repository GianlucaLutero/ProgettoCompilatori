package ast;

import java.util.ArrayList;
import java.util.HashMap;
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
	
    // Possibile modifica: ArrayList<HashMap<Id,Node>> 
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
		String classAst = indent + "Class " + className+" ";
		
		if(parent != null)
			classAst += indent + "implements "+ parent;
		
		classAst += "\n";
		
		for(Node n: classAttr) {
	    	classAst += n.toPrint(indent + " ");
		}
	
		for(Node fc: methodList) {
			classAst += fc.toPrint(indent + " ");
			
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
		
		for(Node fun: methodList) {
			codeClass += fun.codeGeneration();
		}
		
		return codeClass;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		// salvo il template della classe in ObjectHandler
		ArrayList<SemanticError> se = new ArrayList<SemanticError>();
	    
		
		if(!ObjectHandler.checkClass(className)) {
		
			// Aggiungo la classe all'object handler
			ObjectHandler.addClass(className, parent);
		
		}else {
			
			se.add(new SemanticError("Class " + className + " already defined"));
		}

		for(Node p: classAttr) {
	        ParNode g = (ParNode)p;
	        ObjectHandler.addAttribute(className, g.getId(), -2);
		}
		
		for(Node m: methodList) {
			se.addAll(m.checkSemantics(env));
		}
		
		return se;
	}

}

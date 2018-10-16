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

	ArrayList<Node> classAttr = new ArrayList<>();
 	ArrayList<Node> methodList;
	
	public  ClassdecNode(String cName,String pName,ArrayList<Node> m) {
		
		className = cName;
		parent = pName;
		//classAttr = list;
		//Aggiungo il nome della classe al nome del metodo
		for(Node al : m) {
			((FunNode)al).setFunId(((FunNode)al).getTypeId()+"_"+((FunNode)al).getFunId().substring(9)+"_"+cName);
	//		((FunNode)al).setFunId(((FunNode)al).getFunId().substring(9)+"_"+cName);			
		}
		methodList = m;
	}
	
	public void addAttr(Node p) {
		classAttr.add(p);
	}

	@Override
	public String toPrint(String indent) {
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
	
		// Controllo il tipo dei metodi
		for(Node m: methodList) {
			m.typeCheck();
		}
		
		for (Node a: classAttr){
			a.typeCheck();
		}
		// new ObjectTypeNode(className)
		return new ObjectTypeNode(className);
	}

	@Override
	public String codeGeneration() {
		// genero il codice per i metodi
		String codeClass = "";
		
		for(Node fun: methodList) {
			codeClass += fun.codeGeneration();
		}
		
		return codeClass;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// salvo il template della classe in ObjectHandler
		ArrayList<SemanticError> se = new ArrayList<SemanticError>();
		HashMap<String, Integer> aList = new HashMap<String, Integer>();
		HashMap<String,Node> aTypeList = new HashMap<String, Node>();
		String mList = "";
		Integer offset = new Integer(0);
		
		if(!ObjectHandler.checkClass(className)) {

			for(Node p: classAttr) {
		        ParNode g = (ParNode)p;
		      
		        aList.put(g.getId(), offset);
		        aTypeList.put(g.getId(), g.getType());
		        offset -= 1;    
			}
			
			ObjectHandler.lastCall = className;
			System.out.println("Dichiaro la classe: "+ObjectHandler.lastCall);
					
			// Aggiungo la classe all'object handler
		
			ObjectHandler.addClass(className, parent , aList,aTypeList);
		
		}else {
			
			se.add(new SemanticError("Class " + className + " already defined"));
		}

		
		for(Node a: classAttr) {
			se.addAll(a.checkSemantics(env));
		}
		
		for(Node m: methodList) {
			mList=(((FunNode)m).getFunId());
			ObjectHandler.addMethods(className, mList);
			se.addAll(m.checkSemantics(env));
			
		}
		
		
		
		return se;
	}
	
/*
	public String getClassName() {
		return className;
	}
	
	public String getParent() {
		return parent;
	}
	
	public ArrayList<Node> getClassAttr() {
        return classAttr;
    }
    */

}

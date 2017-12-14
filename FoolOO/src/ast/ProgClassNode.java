package ast;

import java.util.ArrayList;

import parser.FOOLParser.ClassdecContext;
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
		return exp.typeCheck();
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return "halt\n";
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		return exp.checkSemantics(env);
	}

}

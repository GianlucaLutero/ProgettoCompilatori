package ast;

import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

public class NewExpNode implements Node {
	
	private String object_id; // nome dell'oggetto
	private ArrayList<Node> arguments; // argomenti passati al costruttore
	private ClassdecNode class_istance; // classe di cui è istanza
	
	
	
	public NewExpNode(String object_id, ArrayList<Node> arguments) {
		
		this.object_id = object_id;
		this.arguments = arguments;
	}
	
	public String getObject_id(){
		return object_id;
	}
	
	

	@Override
	public String toPrint(String indent) {
		return "";
	}

	@Override
	public Node typeCheck() {
		//for(int i = 0; class_istance.)
		
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

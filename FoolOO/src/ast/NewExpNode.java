package ast;

import java.util.ArrayList;

import lib.FOOLlib;
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
		for(int i = 0; i < class_istance.classAttr.size(); i++){
			Node args = arguments.get(i).typeCheck();
			Node var_node_type = ((VarNode) class_istance.getClassAttr().get(i)).typeCheck();
			
			if (!FOOLlib.isSubtype(args, var_node_type)){
				System.out.println("Incompatible parameter at position " + i + " during instantiation of class " + object_id);
			      System.exit(0);
			}
              
		}
		
		//Prendere dalla classe creata da gian BoolTypeNode il return
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

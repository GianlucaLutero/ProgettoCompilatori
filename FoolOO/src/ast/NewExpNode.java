package ast;

import java.util.ArrayList;
import java.util.HashMap;

import lib.ClassDescriptor;
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
		
	ObjectTypeNode ot = new ObjectTypeNode(object_id);
	
	return ot;//valore di ritorno
}

	@Override
	public String codeGeneration() {
		
		int objectSize = arguments.size();
		
		return  "lhp\n" +
				"push " + objectSize + "\n" +
				"add\n" +
	            "shp\n"	+
				"lhp\n";
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		//create result list
	  	  ArrayList<SemanticError> res = new ArrayList<SemanticError>();
	  	  
	  	HashMap<String,STentry> hm = env.symTable.get(env.nestingLevel);
        STentry entry = new STentry(env.nestingLevel,env.offset--);
        
        if ( hm.put(object_id,entry) != null )
            res.add(new SemanticError("Object id "+object_id+" already declared"));
        
        for(Node arg: arguments)
        	res.addAll(arg.checkSemantics(env));
        
		return res;
	}

}

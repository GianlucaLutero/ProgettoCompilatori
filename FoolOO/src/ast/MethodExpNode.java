package ast;

import java.util.ArrayList;
import java.util.HashMap;

import lib.ClassDescriptor;
import lib.FOOLlib;
import util.Environment;
import util.SemanticError;

public class MethodExpNode implements Node {
	// invocazione metodi
	private String id;
	// Dall'id del caller ricavo il puntatore this
	private String caller;
	private ArrayList<Node> parlist;
	private STentry methodNode;
	private STentry objectNode;
	private int nestinglevel;
	
	
	public MethodExpNode(String c, String i, ArrayList<Node> p) {
		caller = c;
		id = i;
		parlist = p;
	}

	@Override
	public String toPrint(String indent) {
		String methodxp = "";
		
		methodxp = indent + caller + " calls method " + id; 
		
		return methodxp;
	}

	@Override
	public Node typeCheck() {
		// controllo del tipo dell'oggetto
		// controllo se il metodo è un ArrowType
		// controllo del numero dei parametri del metodo
		// controllo del tipo dei parametri del metodo

		ObjectTypeNode o=null; 
		if (objectNode.getType() instanceof ObjectTypeNode) o=(ObjectTypeNode) objectNode.getType(); 
		else {
			throw new Error("Invocation of a non-object "+caller); // da sistemare messaggio errore
			//System.exit(0);
		}

		ArrowTypeNode m=null;
		if (methodNode.getType() instanceof ArrowTypeNode) m=(ArrowTypeNode) methodNode.getType(); 
		else {

			throw new Error("Invocation of a non-method "+id);
			//System.exit(0);
		}

		ArrayList<Node> p = m.getParList();
		if ( !(p.size() == parlist.size()) ) {
			throw new Error("Wrong number of parameters in the invocation of "+id);
			//System.exit(0);
		} 
		for (int i=0; i<parlist.size(); i++) 
			if ( !(FOOLlib.isSubtype( (parlist.get(i)).typeCheck(), p.get(i)) ) ) {
				throw new Error("Wrong type for "+(i+1)+"-th parameter in the invocation of "+id);
				//System.exit(0);
			} 

		return m.getRet();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		HashMap<String, Integer> attributes = null;
		int j=env.nestingLevel;
		STentry objCaller= null; 
		STentry method = null;
		boolean foundMethod = false;
		String completeName = "";
			
		// cerco nella symbol table se l'oggetto è stato dichiarato
		if(caller.equals("this")) {
			ObjectHandler.isThis = true;
			System.out.println("this call is of type "+ObjectHandler.lastCall);
			//caller = ObjectHandler.lastVariable;
			System.out.println("This is calling "+id);
		//	System.out.println("Entry of this: "+ObjectHandler.lastEntry);
			objCaller = new STentry(env.nestingLevel-1, new ObjectTypeNode(ObjectHandler.lastCall), -2);
			objCaller.addDecType(new ObjectTypeNode(ObjectHandler.lastCall));
		//	objCaller = ObjectHandler.lastEntry;
		}else {
			
		//	ObjectHandler.lastCall = caller;
			while (j>=0 && objCaller==null){
				objCaller=(env.symTable.get(j--)).get(caller);
			}
			
		}

	
		
		if (objCaller==null) { // se l'oggetto chiamante non è stato dichiarato
			 res.add(new SemanticError("Caller "+ caller +" not declared"));		 
		} else {
			objectNode = objCaller;
			//verifico se esiste la classe dell'oggetto
			ObjectTypeNode obj = (ObjectTypeNode) objectNode.getType(); // obj destra
			ObjectTypeNode declObj = (ObjectTypeNode) objectNode.getDecType(); // obj sinistra
			String tipo = obj.getType();
			ClassDescriptor objClassDescr = ObjectHandler.getClass(tipo);
			ClassDescriptor objClassImpl = null;
			
			boolean foundClass = ObjectHandler.checkClass(tipo);
			
			if(declObj.getType() != null && !obj.getType().equals(declObj.getType())) {
				//obj = declObj;
				foundClass = ObjectHandler.checkClass(declObj.getType());
				tipo = declObj.getType();
			//	objClassDescr = ObjectHandler.getClass(tipo);
				objClassImpl = ObjectHandler.getClass(tipo);
			}else {
				objClassImpl = objClassDescr;
			}
			
			
			if(foundClass){
	
				String lastRet = "";
				String callType = "";
				
				// controllo se la classe ha quel metodo
				while(objClassImpl != null) { 
					
					completeName = id + "_"+tipo;
					
					ArrayList<String> methodList = objClassImpl.getMethodList();
					
					for(String s : methodList){
						
						String[] namParts = s.split("_",2);
						System.out.println("Nome metodo: "+s);
						
						if(namParts[1].equals(completeName)){ // nome metodo
							
						//	if((namParts[0].equals(lastRet))) {
								
								ArrayList<String> parentMethod = ObjectHandler.getClass(objClassDescr.getClassName()).getMethodList();
								
								
								System.out.println("Call type: "+objClassDescr.getClassName());
								
								if(parentMethod != null) {
									for(String ps: parentMethod) {
										String[] psParts = ps.split("_",2);
										
										// confronti i tipi di ritorno perchè è stato trovato lo stesso metodo nel parent
										if(psParts[0].equals(namParts[0])) { // se il nome del metodo e il tipo di ritorno sono uguali
											callType = objClassImpl.getClassName();
											lastRet = namParts[0];
											System.out.println("1) Da qui chiamo: "+lastRet+"_"+id+"_"+callType); // chiami metodo dell'oggetto a destra (overriding)
											break;
										}else { // se il nome del metodo è uguale ma il tipo di ritorno è diverso
											callType = objClassDescr.getClassName();
											lastRet = psParts[0];
											System.out.println("2) Da qui chiamo: "+lastRet+"_"+id+"_"+callType); // chiami metodo del parent
										}
									}
								}else {
									System.out.println("No parent!");
								}
								
							//	callType = declObj.getType();
								//lastRet = namParts[0];
							//	foundMethod=true;
								
						//	}	else {
						//		callType = tipo;
						//		lastRet = namParts[0];
							//	foundMethod = true;
						//	}
							
							foundMethod=true;
						}
						
					}
					
					if(foundMethod) {
						
						completeName = lastRet +"_"+ id +"_"+callType;
						System.out.println("Calling: "+completeName);
						attributes = objClassImpl.getAttList(); // prendi i dati degli attributi
						break;
					}else {
						System.out.println("Search in parent!!"); // controlli risalendo tutti i parent
						tipo = objClassImpl.getParent();
						objClassImpl = ObjectHandler.getClass(objClassImpl.getParent());
					}
				}
			} else {
				res.add(new SemanticError("Class " + tipo + " not found"));
			}
			
		 }
		
		id = completeName;
		
		if(!foundMethod){ // se il metodo non esiste		
			res.add(new SemanticError("Method "+ id+" not declared"));
		} else {
			
			j=env.nestingLevel;
			while (j>=0 && method==null){
				method=(env.symTable.get(j--)).get(id); // prendi offset del metodo
			}
			
			//method = (env.symTable.get(0)).get(id);
		//	System.out.println("AAAAAAAAAAAAAAAa Method: "+method);
			methodNode = method;
		//	System.out.println("AAAAAAAAAAAAAAAa Method: "+methodNode);
		//	System.out.println("AAAAAAAAAAAAAAAAA Type: "+methodNode.getDecType());
			
			// attributes.forEach((key,value) ->parlist.add( new IdNode(key)));
			// il metodo esiste e quindi controllo la semantica dei parametri del metodo
			System.out.println("Lista parametri: "+ parlist);
			for(Node arg : parlist)
			res.addAll(arg.checkSemantics(env));
			
			ObjectHandler.lastEntry = objectNode;
			
		}
		
		nestinglevel = env.nestingLevel;
		
		
		
		return res;
	}

	@Override
	public String codeGeneration() {
		
		String objectCode= Integer.toString(objectNode.getOffset());
		String callerCode = "push " + objectCode + "\n";
		
		String parCode="";
		int pad = 1;
		
		if(ObjectHandler.isThis) {
			pad = 2;
		}
		
	    for (int i=parlist.size()-1; i>=0; i--)
	    	parCode+=parlist.get(i).codeGeneration();
	 //   System.out.println("Object pointer: "+objectCode);
	 //   System.out.println("Method offset: "+(methodNode.getOffset()+pad));
	 //   System.out.println("Parameter list: "+parCode);
	    
	    
	 //   System.out.println("Nesting level: "+nestinglevel);
	 //   System.out.println("Nesting level caller: "+objectNode.getNestinglevel());
	    
	    String getAR="";
	    


	    if(caller.equals("this")) {

	    	for (int i=0; i< nestinglevel-1; i++) 
	    		getAR+="lw\n";


	    	objectCode =Integer.toString(ObjectHandler.lastEntry.getOffset());
	    	//	System.out.println("Offset in cui si trova l'oggetto: "+objectCode);
	    	callerCode = "lfp\n"+getAR+"push "+ objectCode +"\n"+"add\n"+"lw\n"; 
	    	//	callerCode = "push " + objectCode + "\n";

	    	//	System.out.println(callerCode);
	    }else {
	    	for (int i=0; i< nestinglevel - objectNode.getNestinglevel(); i++) 
	    		getAR+="lw\n";	
	    }

	  //	System.out.println("AR: "+getAR);
			
		
		return 	"lfp\n"+
				parCode + "\n"+
		//		"push " + objectCode + "\n" +
				callerCode+
		//		"lfp\n"+getAR+"add\n"+"lw\n"+
				"push "+(methodNode.getOffset()+pad)+"\n"+
				"lfp\n"+getAR+ //risalgo la catena statica							( push $fp + ?? )
				"add\n"+
	            "lw\n"+ //carico sullo stack il valore all'indirizzo ottenuto		
			    "js\n";	//jump to instruction pointed by top of stack and store next instruction in ra
	}
}

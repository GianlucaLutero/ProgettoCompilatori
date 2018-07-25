package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import parser.*;
import parser.FOOLParser.BaseExpContext;
import parser.FOOLParser.BoolValContext;
import parser.FOOLParser.ClassExpContext;
import parser.FOOLParser.ClassdecContext;
import parser.FOOLParser.DecContext;
import parser.FOOLParser.ExpContext;
import parser.FOOLParser.FactorContext;
import parser.FOOLParser.FunContext;
import parser.FOOLParser.FunExpContext;
import parser.FOOLParser.IfExpContext;
import parser.FOOLParser.IntValContext;
import parser.FOOLParser.LetInExpContext;
import parser.FOOLParser.MethodExpContext;
import parser.FOOLParser.NewExpContext;
import parser.FOOLParser.SingleExpContext;
import parser.FOOLParser.TermContext;
import parser.FOOLParser.ThisExpContext;
import parser.FOOLParser.TypeContext;
import parser.FOOLParser.VarAssignmentContext;
import parser.FOOLParser.VarExpContext;
import parser.FOOLParser.VarasmContext;
import parser.FOOLParser.VardecContext;
import util.SemanticError;

public class FoolVisitorImpl extends FOOLBaseVisitor<Node> {
	
	@Override
	public Node visitMethodExp(MethodExpContext ctx) {
		// TO DO
		// Simile alla chiamata di funzione
		// in piu' si deve controllare che il metodo esista nella
		// classe, da fare nel checksemantics
		// si deve anche passare l'oggetto che esegue la chiamata
		
		String caller = "";
	    String methodID = "";
	    
	    if(ctx.THIS() != null) {
	    	caller = "this";
	    	methodID = ctx.ID(0).getText();
	    }else {
	    	caller = ctx.ID(0).getText();
	    	methodID = ctx.ID(1).getText();
	    }
	    
	    ArrayList<Node> args = new ArrayList<>();
	    
	    for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		return new MethodExpNode(caller, methodID, args);
	}
	
	@Override
	public Node visitNewExp(NewExpContext ctx) {
		// TO DO
		// Si deve ricavare il nome della classe
		// e la lista dei parametri con cui inizializzare l'oggetto
		
		ArrayList<Node> argument = new ArrayList<>();
		
		for(ExpContext c: ctx.exp()) {
			argument.add(visit(c));
		}
	
		return new NewExpNode(ctx.ID().toString(),argument);
	}
	
	@Override
	public Node visitThisExp(ThisExpContext ctx) {
		
	//    return null;
		return new ThisExpNode();
	}

	
	@Override
	public Node visitClassdec(ClassdecContext ctx) {
		// Si devono ricavare il nome della classe, eventuali classi genitore,
		// la lista degli attributi e la lista dei metodi
		
		ClassdecNode res;
		
        // res = new ClassdecNode(ctx.ID().toString(),classAttributes);
		
		String className = ctx.ID(0).toString();
		String classParent = null;
		ArrayList<Node> mList = new ArrayList<Node>();
		
		if(ctx.ID().size() > 1)
			//System.out.println("Class "+className+" implements: "+ctx.ID(1));
		    classParent = ctx.ID(1).toString();
		
		for(FunContext f : ctx.fun())
			mList.add(visit(f));
		
	//	System.out.println("Number of Methods: "+ mList.size());
		
		res = new ClassdecNode(className,classParent,mList);
		
		for(VardecContext v : ctx.vardec())
			res.addAttr(new ParNode(v.ID().getText(), visit(v.type())) );
		
		
		return res;
	}
	
	
	@Override
	public Node visitClassExp(ClassExpContext ctx) {
	    
		// risultato della visita di classExp
		ProgClassNode res;
		
		// lista delle dichiarazioni di classi
		ArrayList<Node> classDeclarations = new ArrayList<Node>();
		
		// visita delle dichiarazioni
		for(ClassdecContext cc: ctx.classdec()) {
			
			classDeclarations.add(visit(cc));
			
		}
		
		// visita dell'espressione
		// verifica che funzioni anche con le espressioni let
		Node exp = visit(ctx.exp()); 
		
		if(ctx.let() != null) {
			
			ArrayList<Node> decl = new ArrayList<Node>();
			
			for(DecContext dc : ctx.let().dec()){
				decl.add( visit(dc) );
			}
			
			exp = new ProgLetInNode(decl, exp);
		}
		else
			exp = visit(ctx.exp());
		
		
		res = new ProgClassNode(classDeclarations,exp);		
		
		return res;
		
	}
	
	
	@Override
	public Node visitLetInExp(LetInExpContext ctx) {
		
		//resulting node of the right type
		ProgLetInNode res;
		
		//list of declarations in @res
		ArrayList<Node> declarations = new ArrayList<Node>();
		
		//visit all nodes corresponding to declarations inside the let context and store them in @declarations
		//notice that the ctx.let().dec() method returns a list, this is because of the use of * or + in the grammar
		//antlr detects this is a group and therefore returns a list
		for(DecContext dc : ctx.let().dec()){
			declarations.add( visit(dc) );
		}
		
		//visit exp context
		Node exp = visit( ctx.exp() );
		
		//build @res accordingly with the result of the visits to its content
		res = new ProgLetInNode(declarations,  exp);
		
		return res;
	}
	
	@Override
	public Node visitSingleExp(SingleExpContext ctx) {
		
		//simply return the result of the visit to the inner exp
	//	return visit(ctx.exp());
		Node exp = visit(ctx.exp());
		
		return new ProgNode(exp);
		
	}
	
	@Override
	public Node visitVardec(VardecContext ctx) {
		
		return visitChildren(ctx);
	}
	
	@Override
	public Node visitVarasm(VarasmContext ctx) {
		
		//declare the result node
		//VarNode result;
		
		Node typeNode = visit(ctx.vardec().type());
		
		//visit the exp
		Node expNode = visit(ctx.exp());
		
		//build the varNode
		return new VarNode(ctx.vardec().ID().getText(), typeNode, expNode);
	}
	
	@Override
	public Node visitFun(FunContext ctx) {
		
		//initialize @res with the visits to the type and its ID
		FunNode res = new FunNode(ctx.ID().getText(), visit(ctx.type()));
		
		//add argument declarations
		//we are getting a shortcut here by constructing directly the ParNode
		//this could be done differently by visiting instead the VardecContext
		for(VardecContext vc : ctx.vardec())
			res.addPar( new ParNode(vc.ID().getText(), visit( vc.type() )) );
		
		//add body
		//create a list for the nested declarations
		ArrayList<Node> innerDec = new ArrayList<Node>();
		
		//check whether there are actually nested decs
		if(ctx.let() != null){
			//if there are visit each dec and add it to the @innerDec list
			for(DecContext dc : ctx.let().dec())
				innerDec.add(visit(dc));
		}
		
		//get the exp body
		Node exp = visit(ctx.exp());
		
		//add the body and the inner declarations to the function
		res.addDecBody(innerDec, exp);
		
		return res;		
		
	}
	
	@Override
	public Node visitType(TypeContext ctx) {
		
		if(ctx.getText().equals("int"))
			return new IntTypeNode();
		else if(ctx.getText().equals("bool"))
			return new BoolTypeNode();
		else 
			return new ObjectTypeNode(ctx.getText());
		
		
		
		//this will never happen thanks to the parser
		//return null;

	}
	
	@Override
	public Node visitExp(ExpContext ctx) {
		
		//this could be enhanced
		
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			
			//it is a binary expression, you should visit left and right
			if(ctx.PLUS() != null){   // ha senso??
				return new PlusNode(visit(ctx.left), visit(ctx.right));
			} else {
				return new MinusNode(visit(ctx.left), visit(ctx.right)); // gestione operazione sottrazione
			}
	
		}
		
	}
	
	@Override
	public Node visitTerm(TermContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			//it is a binary expression, you should visit left and right
			return new MultNode(visit(ctx.left), visit(ctx.right));
		}
	}
	
	
	@Override
	public Node visitFactor(FactorContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			//it is a binary expression, you should visit left and right
			return new EqualNode(visit(ctx.left), visit(ctx.right));
		}
	}
	
	
	@Override
	public Node visitIntVal(IntValContext ctx) {
		// notice that this method is not actually a rule but a named production #intVal
		
		//there is no need to perform a check here, the lexer ensures this text is an int
		return new IntNode(Integer.parseInt(ctx.INTEGER().getText()));
	}
	
	@Override
	public Node visitBoolVal(BoolValContext ctx) {
		
		//there is no need to perform a check here, the lexer ensures this text is a boolean
		return new BoolNode(Boolean.parseBoolean(ctx.getText())); 
	}
	
	@Override
	public Node visitBaseExp(BaseExpContext ctx) {
		
		//this is actually nothing in the sense that for the ast the parenthesis are not relevant
		//the thing is that the structure of the ast will ensure the operational order by giving
		//a larger depth (closer to the leafs) to those expressions with higher importance
		
		//this is actually the default implementation for this method in the FOOLBaseVisitor class
		//therefore it can be safely removed here
		
		return visit (ctx.exp());

	}
	
	@Override
	public Node visitIfExp(IfExpContext ctx) {
		
		//create the resulting node
		IfNode res;
		
		//visit the conditional, then the then branch, and then the else branch
		//notice once again the need of named terminals in the rule, this is because
		//we need to point to the right expression among the 3 possible ones in the rule
		
		Node condExp = visit (ctx.cond);
		
		Node thenExp = visit (ctx.thenBranch);
		
		Node elseExp = visit (ctx.elseBranch);
		
		//build the @res properly and return it
		res = new IfNode(condExp, thenExp, elseExp);
		
		return res;
	}
	
	@Override
	public Node visitVarExp(VarExpContext ctx) {
		
		//this corresponds to a variable access
		
		return new IdNode(ctx.ID().getText());

	}
	
	@Override
	public Node visitFunExp(FunExpContext ctx) {
		//this corresponds to a function invocation
		
		//declare the result
		Node res;
		
		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<Node>();
		
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		//especial check for stdlib func
		//this is WRONG, THIS SHOULD BE DONE IN A DIFFERENT WAY
		//JUST IMAGINE THERE ARE 800 stdlib functions...
		if(ctx.ID().getText().equals("print"))
			res = new PrintNode(args.get(0));
		else
			//instantiate the invocation
			res = new CallNode(ctx.ID().getText(), args);
		
		return res;
	}
	
}

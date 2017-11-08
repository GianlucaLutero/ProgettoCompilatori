import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import parser.ExecuteVM;
import parser.FOOLLexer;
import parser.FOOLParser;
import parser.SVMLexer;
import parser.SVMParser;
import parser.FOOLParser.DecContext;
import parser.FOOLParser.FunContext;
import parser.FOOLParser.FunDeclarationContext;
import parser.FOOLParser.LetContext;
import parser.FOOLParser.LetInExpContext;
import parser.FOOLParser.ProgContext;
import util.Environment;
import util.SemanticError;
import ast.FoolVisitorImpl;
import ast.Node;

public class Test2 {
	static int count_var (ParseTree t){
		if (t.getClass().getName().equals("parser.FOOLParser$LetContext")){
			LetContext s = (LetContext) t ;
			int n = 0 ;
			for (DecContext dc : s.dec()) {
				if (dc.getClass().getName().equals("parser.FOOLParser$VarAssignmentContext")) {
					n = n+1 ;
				} else n = n + count_var(dc) ;
			}		
			return(n) ;	
		}
		else if (t.getClass().getName().equals("parser.FOOLParser$LetInExpContext")){
			LetInExpContext s = (LetInExpContext) t ;
			return(count_var(s.let())) ;
		} 
		else if (t.getClass().getName().equals("parser.FOOLParser$FunDeclarationContext")){
			FunDeclarationContext s = (FunDeclarationContext) t ;
			if (s.fun().let() == null){
				return(1) ;
			} else return (1 + count_var(s.fun().let()) );	
		}
		else return(0) ;
	}
	
	public static void main(String[] args) throws Exception {
      
        String fileName = "prova.fool";
      
        FileInputStream is = new FileInputStream(fileName);
        ANTLRInputStream input = new ANTLRInputStream(is);
        FOOLLexer lexer = new FOOLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        //SIMPLISTIC BUT WRONG CHECK OF THE LEXER ERRORS
        if(lexer.lexicalErrors > 0){
        	System.out.println("The program was not in the right format. Exiting the compilation process now");
        }else{
        
	        FOOLParser parser = new FOOLParser(tokens);
	        
	        ParseTree t = parser.prog() ;
	        	        
	        System.out.println(t.getText()) ;
	        
	        System.out.println(count_var(t)) ;
	        
	        /*	        FoolVisitorImpl visitor = new FoolVisitorImpl();
	        
	        Node ast = visitor.visit(parser.prog()); //generazione AST 

	        Environment env = new Environment();
	        ArrayList<SemanticError> err = ast.checkSemantics(env);
	        
	        if(err.size()>0){
	        	System.out.println("You had: " +err.size()+" errors:");
	        	for(SemanticError e : err)
	        		System.out.println("\t" + e);
	        }else{
	        
	        	
	
		        System.out.println("Visualizing AST...");
		        System.out.println(ast.toPrint(""));
		
		        Node type = ast.typeCheck(); //type-checking bottom-up 
		        System.out.println(type.toPrint("Type checking ok! Type of the program is: "));
		        
		      
		        // CODE GENERATION  prova.fool.asm
		        String code=ast.codeGeneration(); 
		        BufferedWriter out = new BufferedWriter(new FileWriter(fileName+".asm")); 
		        out.write(code);
		        out.close(); 
		        System.out.println("Code generated! Assembling and running generated code.");
		        
		        FileInputStream isASM = new FileInputStream(fileName+".asm");
		        ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
		        SVMLexer lexerASM = new SVMLexer(inputASM);
		        CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
		        SVMParser parserASM = new SVMParser(tokensASM);
		        
		        parserASM.assembly();
		        
		        System.out.println("You had: "+lexerASM.lexicalErrors+" lexical errors and "+parserASM.getNumberOfSyntaxErrors()+" syntax errors.");
		        if (lexerASM.lexicalErrors>0 || parserASM.getNumberOfSyntaxErrors()>0) System.exit(1);
		
		        System.out.println("Starting Virtual Machine...");
		        ExecuteVM vm = new ExecuteVM(parserASM.code);
		        vm.cpu();
	        }
        */
        }
       
        
    }
}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import ast.FoolVisitorImpl;
import ast.Node;
import parser.ExecuteVM;
import parser.FOOLLexer;
import parser.FOOLParser;
import parser.SVMLexer;
import parser.SVMParser;
import util.Environment;
import util.SemanticError;

/*
 * Interprete FoolOO
 * 
 * L'interprete carica i file dalla cartella test e li esegue uno alla volta
 * TO DO: aggiungere la possibilita' di segliere se eseguire i test o un file arbitrario
 * 
 * 
 * Authors: Davide Allevi, Silvia Borgiani, Gianluca Lutero e Filippo Soncini
 * 
 * */

public class FoolOOInterpreter {

	public static void main(String[] args) {
		
		// Carico tutti i file di test con estensione .fool
		File testDirectory = new File("./test");
		
		File[] testList = testDirectory.listFiles( new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".fool");
			}
		});
		
		System.out.println("Number of tests: "+ testList.length);
		
		//Inizia l'esecuzione dei programmi
		for(int i = 0; i < testList.length; ++i) {
			
			
			if(testList[i].isFile()) {
				System.out.println("---------------------------------------------------");
			    System.out.println("Executing test: " + testList[i].getName());
			    
			    
			    try {
			    	
			    	// Inizializzo il file di output dei risultati
			    	PrintWriter writer = new PrintWriter("./test/" + testList[i].getName().substring(0, testList[i].getName().length() - 5)+".res","UTF-8");
					
			    	
					FileInputStream in = new FileInputStream(testList[i]);
					CharStream input = CharStreams.fromFileName(testList[i].getPath());
					FOOLLexer lexer = new FOOLLexer(input);
					CommonTokenStream tokenStream = new CommonTokenStream(lexer);
					
					if(lexer.lexicalErrors > 0) {
					 	System.out.println("The program was not in the right format. Exiting the compilation process now");
			        }else {
			        	
			        	
			        	//Parsing del programma fool
			        	FOOLParser parser = new FOOLParser(tokenStream);
			        	
				        FoolVisitorImpl visitor = new FoolVisitorImpl();  
				        
				        Node ast = visitor.visit(parser.prog());  

				        Environment env = new Environment();
				        
				        ArrayList<SemanticError> err = ast.checkSemantics(env);
				        
				        if(err.size()>0){
				        	writer.println("You had: " +err.size()+" errors:");
				        	for(SemanticError e : err)
				        		writer.println("\t" + e);
				        }else{
				        
					        writer.println("Visualizing AST...");
					        writer.println(ast.toPrint(""));
					
					        Node type = ast.typeCheck(); //type-checking bottom-up 
					        writer.println(type.toPrint("Type checking ok! Type of the program is: "));
					        
					      
					        // CODE GENERATION  
					        String code=ast.codeGeneration(); 
					        BufferedWriter out = new BufferedWriter(new FileWriter(testList[i].getName()+".asm")); 
					        out.write(code);
					        out.close(); 
					        writer.println("Code generated! Assembling and running generated code.");
					        
					        FileInputStream isASM = new FileInputStream(testList[i].getName()+".asm");
					        CharStream inputASM = CharStreams.fromStream(isASM);
					        SVMLexer lexerASM = new SVMLexer(inputASM);
					        CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
					        SVMParser parserASM = new SVMParser(tokensASM);
					        
					        parserASM.assembly();
					        
					        writer.println("You had: "+lexerASM.lexicalErrors+" lexical errors and "+parserASM.getNumberOfSyntaxErrors()+" syntax errors.");
					        if (lexerASM.lexicalErrors>0 || parserASM.getNumberOfSyntaxErrors()>0) System.exit(1);
					
					        //Chiudo il file
					        writer.close();
					        
					        System.out.println("Starting Virtual Machine...");
					        ExecuteVM vm = new ExecuteVM(parserASM.code);
					        vm.cpu();
					        
					     
				        }
			        }
					
					
			    
			    } catch (IOException e ) {
			    	
					System.out.println("No file with name: " + testList[i].getName());
					e.printStackTrace();
				
			    } catch(Throwable t) {
			    	
			    	t.printStackTrace();
			    	
			    }
			    
			    System.out.println("End test: " + testList[i].getName());
			}			
			
		}

	}

}

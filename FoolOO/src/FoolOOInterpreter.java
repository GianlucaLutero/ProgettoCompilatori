import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import ast.FoolVisitorImpl;
import ast.Node;
import parser.FOOLLexer;
import parser.FOOLParser;
import util.Environment;
import util.SemanticError;

/*
 * Main del progetto 
 * 
 * TEST 
 * 
 * Vengono caricati i file dalla cartella test ed eseguiti uno alla volta
 * TO DO: aggiungere la possibilita' di segliere se eseguire i test o un file arbitrario
 * 
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
		
		//Esegue i test
		for(int i = 0; i < testList.length; ++i) {
			
			if(testList[i].isFile()) {
			    System.out.println("Executing test: " + testList[i].getName());
			    
			    
			    // Inizio parsing di un test
			    try {
			    	
			    	
					FileInputStream in = new FileInputStream(testList[i]);
					CharStream input = CharStreams.fromFileName(testList[i].getPath());
					FOOLLexer lexer = new FOOLLexer(input);
					CommonTokenStream tokenStream = new CommonTokenStream(lexer);
					
					if(lexer.lexicalErrors > 0) {
					 	System.out.println("The program was not in the right format. Exiting the compilation process now");
			        }else {
			        	
			        	
			        	FOOLParser parser = new FOOLParser(tokenStream);
			        	
				        FoolVisitorImpl visitor = new FoolVisitorImpl();  
				        
				        Node ast = visitor.visit(parser.prog());  

				        Environment env = new Environment();
				        				        
				        // Capire perche' ast = null 
				        //ArrayList<SemanticError> err = ast.checkSemantics(env);
			        }
					
					
			    
			    } catch (IOException e ) {
			    	
					System.out.println("No file with name: " + testList[i].getName());
					e.printStackTrace();
				
			    }
			    
			    System.out.println("End test: " + testList[i].getName());
			}			
			
		}

	}

}

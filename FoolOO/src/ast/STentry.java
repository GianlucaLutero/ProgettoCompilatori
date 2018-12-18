package ast;
public class STentry {
 // oggetti nella symbol table
	
  private int nl;
  private Node type;
  private Node decType;
  private int offset;
  private Boolean isAttribute = false; // controllo se è un attributo dell'oggetto usato in IdNode
  
  public STentry (int n, int os)
  {nl=n;
  offset=os;} 
   
  public STentry (int n, Node t, int os)
  {nl=n;
   type=t;
   offset=os;}
  
  public void addDecType(Node dt) {
	  decType = dt;
  }
  
  public Node getDecType() {
	  return decType;
  }
  
  public void addType (Node t)
  {type=t;}
  
  public Node getType ()
  {return type;}

  public int getOffset ()
  {return offset;}
  
  public int getNestinglevel ()
  {return nl;}
  
  public void setAttribute()
  {isAttribute = true;}
  
  public Boolean isAttribute()
  {return isAttribute;}
  
  public String toPrint(String s) { //
	   return s+"STentry: nestlev " + Integer.toString(nl) +"\n"+
			  s+"STentry: type\n" + 
			  type.toPrint(s+"  ") + 
		      s+"STentry: offset " + Integer.toString(offset) + "\n";
  }
}  
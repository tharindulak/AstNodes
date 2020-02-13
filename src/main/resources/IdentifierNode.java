public class IdentifierNode extends AstNode{
private String identifier;
public IdentifierNode () { 
}
public IdentifierNode (String identifier){
this.identifier=identifier;
}public String toString (){
return identifier ;
}
}


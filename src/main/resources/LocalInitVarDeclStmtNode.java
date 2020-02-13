public class LocalInitVarDeclStmtNode extends AstNode {
private String finalKeyword;
private String typeBindingPattern;
private IdentifierNode paramName;
private String assignmentOperator;
private int literal;
private String semicolon;
public LocalInitVarDeclStmtNode () { 
}
public LocalInitVarDeclStmtNode (String finalKeyword,String typeBindingPattern,IdentifierNode paramName,String assignmentOperator,int literal,String semicolon){
this.finalKeyword=finalKeyword;
this.typeBindingPattern=typeBindingPattern;
this.paramName=paramName;
this.assignmentOperator=assignmentOperator;
this.literal=literal;
this.semicolon=semicolon;
}public String toString (){
return finalKeyword +" "+typeBindingPattern +" "+paramName.toString() +" "+assignmentOperator +" "+literal+"" +" "+semicolon ;
}
}


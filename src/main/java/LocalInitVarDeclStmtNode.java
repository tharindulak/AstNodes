public class LocalInitVarDeclStmtNode extends StatementNode{
private KeywordNode finalKeyword;
private String typeBindingPattern;
private IdentifierNode paramName;
private OperatorNode assignmentOperator;
private int literal;
private OtherTokenNode semicolon;
public LocalInitVarDeclStmtNode () { 
}
public LocalInitVarDeclStmtNode (KeywordNode finalKeyword,String typeBindingPattern,IdentifierNode paramName,OperatorNode assignmentOperator,int literal,OtherTokenNode semicolon){
this.finalKeyword=finalKeyword;
this.typeBindingPattern=typeBindingPattern;
this.paramName=paramName;
this.assignmentOperator=assignmentOperator;
this.literal=literal;
this.semicolon=semicolon;
}
public String toString (){
return finalKeyword.toString()+" "+typeBindingPattern +" "+paramName.toString() +" "+assignmentOperator.toString() +" "+literal+"" +" "+semicolon.toString() ;
}
}


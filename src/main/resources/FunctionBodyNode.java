public class FunctionBodyNode extends AstNode {
private LocalVarDeclStmtNode localVarDeclStmt;
public FunctionBodyNode () { 
}
public FunctionBodyNode (LocalVarDeclStmtNode localVarDeclStmt){
this.localVarDeclStmt=localVarDeclStmt;
}public String toString (){
return localVarDeclStmt.toString();
}
}


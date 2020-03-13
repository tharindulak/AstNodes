package generator;

public class FunctionBodyNode extends StatementNode{
private LocalVarDeclStmtNode localVarDeclStmt;
public FunctionBodyNode () { 
}
public FunctionBodyNode (LocalVarDeclStmtNode localVarDeclStmt){
this.localVarDeclStmt=localVarDeclStmt;
}
public String toString (){
return localVarDeclStmt.toString();
}
}


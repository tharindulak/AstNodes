public class LocalVarDeclStmtNode extends AstNode{
private LocalInitVarDeclStmtNode localInitVarDeclStmtNode;
public LocalVarDeclStmtNode () { 
}
public LocalVarDeclStmtNode (LocalInitVarDeclStmtNode localInitVarDeclStmtNode){
this.localInitVarDeclStmtNode=localInitVarDeclStmtNode;
}public String toString (){
return localInitVarDeclStmtNode.toString();
}
}


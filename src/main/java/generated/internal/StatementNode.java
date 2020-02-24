package generated.internal;
import generated.facade.*;
public class StatementNode extends SyntaxNode{

public StatementNode(SyntaxKind kind ){
super(kind );


}
public StatementNode(SyntaxKind kind, int width ) {
super(kind, width );


}

public BLNode createFacade(int position, BLNonTerminalNode parent) {
return new BLStatementNode(this, position, parent);
}
}

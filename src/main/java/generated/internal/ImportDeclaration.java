package generated.internal;
import generated.facade.*;

public  class ImportDeclaration extends SyntaxNode{

public ImportDeclaration(SyntaxKind kind ){
super(kind );
this.bucketCount = 6;
this.childBuckets = new SyntaxNode[6];
}

public ImportDeclaration(SyntaxKind kind, int width ) {
super(kind, width );
this.bucketCount = 6;
this.childBuckets = new SyntaxNode[6];
}


public BLNode createFacade(int position, BLNonTerminalNode parent) {
return new BLImportDeclaration(this, position, parent);
}
}

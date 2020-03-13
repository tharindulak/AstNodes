package generated.internal;
import generated.facade.*;

public  class MissingToken extends SyntaxToken{
public final boolean IS_MISSING;

public MissingToken(SyntaxKind kind , boolean IS_MISSING){
super(kind ,null,null);
this.IS_MISSING = IS_MISSING;
this.bucketCount = 1;
this.childBuckets = new SyntaxNode[1];
}

public MissingToken(SyntaxKind kind, int width , boolean IS_MISSING) {
super(kind, width ,null,null);
this.IS_MISSING = IS_MISSING;
this.bucketCount = 1;
this.childBuckets = new SyntaxNode[1];
}


}

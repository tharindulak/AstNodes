package generated.internal;
import generated.facade.*;

public  class STFunctionDefinition extends STNode{
public final STToken visibilityQual;
public final STToken functionKeyword;
public final STToken functionName;
public final STToken openParenToken;
public final STToken closeParenToken;
public final STNode functionBody;

public STFunctionDefinition(SyntaxKind kind , STToken visibilityQual, STToken functionKeyword, STToken functionName, STToken openParenToken, STToken closeParenToken, STNode functionBody){
super(kind );
this.visibilityQual = visibilityQual;
this.functionKeyword = functionKeyword;
this.functionName = functionName;
this.openParenToken = openParenToken;
this.closeParenToken = closeParenToken;
this.functionBody = functionBody;
this.bucketCount = 6;
this.childBuckets = new STNode[6];
this.addChildNode(visibilityQual, 0);
this.addChildNode(functionKeyword, 1);
this.addChildNode(functionName, 2);
this.addChildNode(openParenToken, 3);
this.addChildNode(closeParenToken, 4);
this.addChildNode(functionBody, 5);
}

public STFunctionDefinition(SyntaxKind kind, int width , STToken visibilityQual, STToken functionKeyword, STToken functionName, STToken openParenToken, STToken closeParenToken, STNode functionBody) {
super(kind, width );
this.visibilityQual = visibilityQual;
this.functionKeyword = functionKeyword;
this.functionName = functionName;
this.openParenToken = openParenToken;
this.closeParenToken = closeParenToken;
this.functionBody = functionBody;
this.bucketCount = 6;
this.childBuckets = new STNode[6];
this.addChildNode(visibilityQual, 0);
this.addChildNode(functionKeyword, 1);
this.addChildNode(functionName, 2);
this.addChildNode(openParenToken, 3);
this.addChildNode(closeParenToken, 4);
this.addChildNode(functionBody, 5);
}


public Node createFacade(int position, NonTerminalNode parent) {
return new FunctionDefinition(this, position, parent);
}
}

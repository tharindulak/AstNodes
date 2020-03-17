package generated.facade;
import generated.internal.STNode;

public  class FunctionDefinition extends NonTerminalNode{
private Token visibilityQual;
private Token functionKeyword;
private Token functionName;
private Token openParenToken;
private Token closeParenToken;
private Node functionBody;

public FunctionDefinition(STNode node, int position, NonTerminalNode parent) {
super(node, position, parent);
}

public Token visibilityQual() {
if (visibilityQual != null) {
return visibilityQual;
}
visibilityQual = createToken(0);
return visibilityQual;
}
public Token functionKeyword() {
if (functionKeyword != null) {
return functionKeyword;
}
functionKeyword = createToken(1);
return functionKeyword;
}
public Token functionName() {
if (functionName != null) {
return functionName;
}
functionName = createToken(2);
return functionName;
}
public Token openParenToken() {
if (openParenToken != null) {
return openParenToken;
}
openParenToken = createToken(3);
return openParenToken;
}
public Token closeParenToken() {
if (closeParenToken != null) {
return closeParenToken;
}
closeParenToken = createToken(4);
return closeParenToken;
}
public Node functionBody() {
if (functionBody != null) {
return functionBody;
}
functionBody = node.childInBucket(5).createFacade(getChildPosition(5), this);
return functionBody;
}

public Node childInBucket(int bucket) {
switch (bucket) {
case 0:
return visibilityQual();
case 1:
return functionKeyword();
case 2:
return functionName();
case 3:
return openParenToken();
case 4:
return closeParenToken();
case 5:
return functionBody();
}
return null;
}
}

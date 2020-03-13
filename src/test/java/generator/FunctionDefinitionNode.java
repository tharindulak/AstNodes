package generator;

public class FunctionDefinitionNode extends AstNode{
private KeywordNode visibility;
private KeywordNode functionKeyword;
private IdentifierNode functionName;
private OtherTokenNode startBracket;
private RequiredParamsNode requiredParams;
private OtherTokenNode endBracket;
private OtherTokenNode beginParenthesis;
private FunctionBodyNode functionBody;
private OtherTokenNode endParenthesis;
public FunctionDefinitionNode () { 
}
public FunctionDefinitionNode (KeywordNode visibility,KeywordNode functionKeyword,IdentifierNode functionName,OtherTokenNode startBracket,RequiredParamsNode requiredParams,OtherTokenNode endBracket,OtherTokenNode beginParenthesis,FunctionBodyNode functionBody,OtherTokenNode endParenthesis){
this.visibility=visibility;
this.functionKeyword=functionKeyword;
this.functionName=functionName;
this.startBracket=startBracket;
this.requiredParams=requiredParams;
this.endBracket=endBracket;
this.beginParenthesis=beginParenthesis;
this.functionBody=functionBody;
this.endParenthesis=endParenthesis;
}
public String toString (){
return visibility.toString()+" "+functionKeyword.toString() +" "+functionName.toString() +" "+startBracket.toString() +" "+requiredParams.toString() +" "+endBracket.toString() +" "+beginParenthesis.toString() +" "+functionBody.toString() +" "+endParenthesis.toString() ;
}
}


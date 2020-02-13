public class FunctionDefinitionNode extends AstNode{
private String visibility;
private String functionKeyword;
private String functionName;
private String startBracket;
private RequiredParamsNode requiredParams;
private String endBracket;
private String beginParenthesis;
private FunctionBodyNode functionBody;
private String endParenthesis;
public FunctionDefinitionNode () { 
}
public FunctionDefinitionNode (String visibility,String functionKeyword,String functionName,String startBracket,RequiredParamsNode requiredParams,String endBracket,String beginParenthesis,FunctionBodyNode functionBody,String endParenthesis){
this.visibility=visibility;
this.functionKeyword=functionKeyword;
this.functionName=functionName;
this.startBracket=startBracket;
this.requiredParams=requiredParams;
this.endBracket=endBracket;
this.beginParenthesis=beginParenthesis;
this.functionBody=functionBody;
this.endParenthesis=endParenthesis;
}public String toString (){
return visibility +" "+functionKeyword +" "+functionName +" "+startBracket +" "+requiredParams.toString() +" "+endBracket +" "+beginParenthesis +" "+functionBody.toString() +" "+endParenthesis ;
}
}


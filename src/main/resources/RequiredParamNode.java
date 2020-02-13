public class RequiredParamNode extends AstNode{
private String publicKeyword;
private String typeDescriptor;
private IdentifierNode paramName;
public RequiredParamNode () { 
}
public RequiredParamNode (String publicKeyword,String typeDescriptor,IdentifierNode paramName){
this.publicKeyword=publicKeyword;
this.typeDescriptor=typeDescriptor;
this.paramName=paramName;
}public String toString (){
return publicKeyword +" "+typeDescriptor +" "+paramName.toString() ;
}
}


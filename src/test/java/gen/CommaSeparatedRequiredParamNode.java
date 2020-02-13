package gen;

public class CommaSeparatedRequiredParamNode extends AstNode{
private OtherTokenNode comma;
private RequiredParamNode requiredParam;
public CommaSeparatedRequiredParamNode () { 
}
public CommaSeparatedRequiredParamNode (OtherTokenNode comma,RequiredParamNode requiredParam){
this.comma=comma;
this.requiredParam=requiredParam;
}
public String toString (){
return comma.toString()+" "+requiredParam.toString() ;
}
}


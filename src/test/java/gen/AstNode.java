package gen;

public abstract class AstNode{
private String missingFlagValue;
public AstNode () { 
}
public AstNode (String missingFlagValue){
this.missingFlagValue=missingFlagValue;
}
public String toString (){
return missingFlagValue ;
}
}


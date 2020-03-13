package generator;

public class OtherTokenNode extends AstNode{
private String tokenValue;
public OtherTokenNode () { 
}
public OtherTokenNode (String tokenValue){
this.tokenValue=tokenValue;
}
public String toString (){
return tokenValue ;
}
}


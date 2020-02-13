public class OperatorNode extends AstNode{
private String operator;
public OperatorNode () { 
}
public OperatorNode (String operator){
this.operator=operator;
}
public String toString (){
return operator ;
}
}


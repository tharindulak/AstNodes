public class KeywordNode extends AstNode{
private String keyword;
public KeywordNode () { 
}
public KeywordNode (String keyword){
this.keyword=keyword;
}
public String toString (){
return keyword ;
}
}


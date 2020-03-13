package generator;

public class RequiredParamNode extends AstNode {
    private KeywordNode publicKeyword;
    private String typeDescriptor;
    private IdentifierNode paramName;

    public RequiredParamNode() {
    }

    public RequiredParamNode(KeywordNode publicKeyword, String typeDescriptor, IdentifierNode paramName) {
        this.publicKeyword = publicKeyword;
        this.typeDescriptor = typeDescriptor;
        this.paramName = paramName;
    }

    public String toString() {
        return publicKeyword.toString() + " " + typeDescriptor + " " + paramName.toString();
    }
}


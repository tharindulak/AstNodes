import java.util.List;

public class RequiredParamsNode extends AstNode {
    private RequiredParamNode requiredParam;
    private String requiredParamSeparator;
    private List<RequiredParamNode> requiredParamList;

    public RequiredParamsNode() {
    }

    public RequiredParamsNode(RequiredParamNode requiredParam, String requiredParamSeparator, List<RequiredParamNode> requiredParamList) {
        this.requiredParam = requiredParam;
        this.requiredParamSeparator = requiredParamSeparator;
        this.requiredParamList = requiredParamList;
    }

    public String toString() {
        return requiredParam.toString() + " " + requiredParamSeparator + " " + requiredParamList.toString();
    }
}


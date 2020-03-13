package generator;

import java.util.List;

public class RequiredParamsNode extends AstNode {
    private RequiredParamNode requiredParam;
    private List<CommaSeparatedRequiredParamNode> requiredParamList;

    public RequiredParamsNode() {
    }

    public RequiredParamsNode(RequiredParamNode requiredParam, List<CommaSeparatedRequiredParamNode> requiredParamList) {
        this.requiredParam = requiredParam;
        this.requiredParamList = requiredParamList;
    }

    public String toString() {
        StringBuilder requiredParamListBuilder = new StringBuilder();
        for (CommaSeparatedRequiredParamNode var : requiredParamList) {
            requiredParamListBuilder.append(var).append(" ");
        }
        return requiredParam.toString() + " " + requiredParamListBuilder;
    }
}


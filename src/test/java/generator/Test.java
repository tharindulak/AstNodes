package gen;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        gen.IdentifierNode identifier = new gen.IdentifierNode("x");
        gen.KeywordNode finalKeyword = new gen.KeywordNode("final");
        gen.OperatorNode assignmentOperator = new gen.OperatorNode("=");
        gen.OtherTokenNode semicolon = new gen.OtherTokenNode(";");
        gen.LocalInitVarDeclStmtNode localInitVarDeclStmtNode = new gen.LocalInitVarDeclStmtNode(finalKeyword, "int", identifier, assignmentOperator, 10, semicolon);
        gen.LocalVarDeclStmtNode localVarDeclStmtNode = new gen.LocalVarDeclStmtNode(localInitVarDeclStmtNode);
        gen.FunctionBodyNode functionBody = new gen.FunctionBodyNode(localVarDeclStmtNode);
        gen.IdentifierNode p = new gen.IdentifierNode("p");
        gen.IdentifierNode r = new gen.IdentifierNode("r");
        gen.KeywordNode publicKeyword = new gen.KeywordNode("public");
        gen.IdentifierNode q = new gen.IdentifierNode("q");
        gen.RequiredParamNode requiredParamNodeP = new gen.RequiredParamNode(publicKeyword, "int", p);
        gen.RequiredParamNode requiredParamNodeQ = new gen.RequiredParamNode(publicKeyword, "int", q);
        gen.RequiredParamNode requiredParamNodeR = new gen.RequiredParamNode(publicKeyword, "int", r);
        gen.OtherTokenNode comma = new gen.OtherTokenNode(",");
        gen.CommaSeparatedRequiredParamNode commaSeparatedQ = new gen.CommaSeparatedRequiredParamNode(comma, requiredParamNodeQ);
        gen.CommaSeparatedRequiredParamNode commaSeparatedR = new gen.CommaSeparatedRequiredParamNode(comma, requiredParamNodeR);
        List<gen.CommaSeparatedRequiredParamNode> commaSeparatedRequiredParamNodes = new ArrayList<>();
        commaSeparatedRequiredParamNodes.add(commaSeparatedQ);
        commaSeparatedRequiredParamNodes.add(commaSeparatedR);
        gen.RequiredParamsNode requiredParamsNode = new gen.RequiredParamsNode(requiredParamNodeP, commaSeparatedRequiredParamNodes);
        gen.KeywordNode functionKeyword = new gen.KeywordNode("function");
        gen.IdentifierNode functionName = new gen.IdentifierNode("hello");
        gen.OtherTokenNode startBracket = new gen.OtherTokenNode("(");
        gen.OtherTokenNode endBracket = new gen.OtherTokenNode(")");
        gen.OtherTokenNode beginPara = new gen.OtherTokenNode("{");
        gen.OtherTokenNode endPara = new gen.OtherTokenNode("}");
        gen.FunctionDefinitionNode functionDefinitionNode = new gen.FunctionDefinitionNode(publicKeyword, functionKeyword, functionName, startBracket, requiredParamsNode, endBracket, beginPara, functionBody, endPara);
        System.out.println(functionDefinitionNode.toString());
    }
}

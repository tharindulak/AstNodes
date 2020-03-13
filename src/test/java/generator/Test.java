package generator;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        generator.IdentifierNode identifier = new generator.IdentifierNode("x");
        generator.KeywordNode finalKeyword = new generator.KeywordNode("final");
        generator.OperatorNode assignmentOperator = new generator.OperatorNode("=");
        generator.OtherTokenNode semicolon = new generator.OtherTokenNode(";");
        generator.LocalInitVarDeclStmtNode localInitVarDeclStmtNode = new generator.LocalInitVarDeclStmtNode(finalKeyword, "int", identifier, assignmentOperator, 10, semicolon);
        generator.LocalVarDeclStmtNode localVarDeclStmtNode = new generator.LocalVarDeclStmtNode(localInitVarDeclStmtNode);
        generator.FunctionBodyNode functionBody = new generator.FunctionBodyNode(localVarDeclStmtNode);
        generator.IdentifierNode p = new generator.IdentifierNode("p");
        generator.IdentifierNode r = new generator.IdentifierNode("r");
        generator.KeywordNode publicKeyword = new generator.KeywordNode("public");
        generator.IdentifierNode q = new generator.IdentifierNode("q");
        generator.RequiredParamNode requiredParamNodeP = new generator.RequiredParamNode(publicKeyword, "int", p);
        generator.RequiredParamNode requiredParamNodeQ = new generator.RequiredParamNode(publicKeyword, "int", q);
        generator.RequiredParamNode requiredParamNodeR = new generator.RequiredParamNode(publicKeyword, "int", r);
        generator.OtherTokenNode comma = new generator.OtherTokenNode(",");
        generator.CommaSeparatedRequiredParamNode commaSeparatedQ = new generator.CommaSeparatedRequiredParamNode(comma, requiredParamNodeQ);
        generator.CommaSeparatedRequiredParamNode commaSeparatedR = new generator.CommaSeparatedRequiredParamNode(comma, requiredParamNodeR);
        List<generator.CommaSeparatedRequiredParamNode> commaSeparatedRequiredParamNodes = new ArrayList<>();
        commaSeparatedRequiredParamNodes.add(commaSeparatedQ);
        commaSeparatedRequiredParamNodes.add(commaSeparatedR);
        generator.RequiredParamsNode requiredParamsNode = new generator.RequiredParamsNode(requiredParamNodeP, commaSeparatedRequiredParamNodes);
        generator.KeywordNode functionKeyword = new generator.KeywordNode("function");
        generator.IdentifierNode functionName = new generator.IdentifierNode("hello");
        generator.OtherTokenNode startBracket = new generator.OtherTokenNode("(");
        generator.OtherTokenNode endBracket = new generator.OtherTokenNode(")");
        generator.OtherTokenNode beginPara = new generator.OtherTokenNode("{");
        generator.OtherTokenNode endPara = new generator.OtherTokenNode("}");
        generator.FunctionDefinitionNode functionDefinitionNode = new generator.FunctionDefinitionNode(publicKeyword, functionKeyword, functionName, startBracket, requiredParamsNode, endBracket, beginPara, functionBody, endPara);
        System.out.println(functionDefinitionNode.toString());
    }
}

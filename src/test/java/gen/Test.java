package gen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        gen.KeywordNode publicKeyword = new gen.KeywordNode("public");
        gen.RequiredParamNode requiredParamNodeP = new gen.RequiredParamNode(publicKeyword, "int", p);
        gen.IdentifierNode q = new gen.IdentifierNode("q");
        gen.RequiredParamNode requiredParamNodeQ = new gen.RequiredParamNode(publicKeyword, "int", q);
        gen.OtherTokenNode comma = new gen.OtherTokenNode(",");
        gen.CommaSeparatedRequiredParamNode commaSeparatedQ = new gen.CommaSeparatedRequiredParamNode(comma, requiredParamNodeQ);
        gen.CommaSeparatedRequiredParamNode commaSeparatedP = new gen.CommaSeparatedRequiredParamNode(comma, requiredParamNodeP);
        List<gen.CommaSeparatedRequiredParamNode> commaSeparatedRequiredParamNodes = new ArrayList<>();
        commaSeparatedRequiredParamNodes.add(commaSeparatedP);
        commaSeparatedRequiredParamNodes.add(commaSeparatedQ);
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

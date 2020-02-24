package generator;

import generator.util.Common;
import generator.util.Constants;
import model.Field;
import model.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FacadeGenerator {
    public static void generateFacade() throws IOException {
        List<Node> nodes;
        nodes = Common.getTreeNodes();
        String facadeClassString = "";
        if (nodes != null) {
            List<Node> nodeList = restructureNodes(nodes);
            for (Node node : nodeList) {
                facadeClassString = new String(Files.readAllBytes(Paths.get("FacadeTemplateClass.txt")));
                facadeClassString = facadeClassString.replace(Constants.PACKAGE_PLACEHOLDER, Constants.FACADE_PACKAGE);
                facadeClassString = facadeClassString.replace(Constants.IMPORTS_PLACEHOLDER, Constants.FACADE_IMPORTS);
                facadeClassString = Common.buildClassName(node, facadeClassString);
                facadeClassString = facadeClassString.replace(Constants.ATTRIBUTES_PLACEHOLDER,
                        defineFacadeAttributes(node.getFields()));
                facadeClassString = facadeClassString.replace(Constants.CONSTRUCTOR_PLACEHOLDER,
                        Constants.FACADE_CONSTRUCTOR.replace(Constants.CLASSNAME_PLACEHOLDER, node.getName()));
                facadeClassString = facadeClassString.replace(Constants.FACADE_MEMBER_PLACEHOLDER,
                        generateFacadeMembers(node.getFields()));
                facadeClassString = facadeClassString.replace(Constants.CASE_STATEMENTS_PLACEHOLDER,
                        buildCaseStatements(node.getFields()));
                Common.writeToFile(facadeClassString, "src/main/java/generated/facade/" + node.getName()
                        + Constants.DOT + Constants.JAVA_EXT);
            }
        } else {
//            todo throw exception
        }
    }

//    private static String generateImports(List<Field> fieldList) {
//        StringBuilder importString = new StringBuilder();
//        if (fieldList == null) {
//            return "";
//        }
//        for (Field field : fieldList) {
//            if (field.getName().contains(Constants.LIST_KEYWORD)) {
//                importString.append(Constants.LIST_IMPORT_STRING).append(Constants.NEW_LINE);
//            }
//            // add other imports as well
//        }
//        return importString.toString();
//    }

    private static List<Node> restructureNodes(List<Node> nodeList) {
        List<Node> modifiedNodeList = new ArrayList<>();
        for (Node node : nodeList) {
            if (!(node.getName().contains(Constants.TOKEN) || node.getName().contains(Constants.TRIVIA)) ||
                    node.getName().contains(Constants.SYNTAX_TOKEN)) {
                Node newNode = new Node();
                newNode.setExt(Constants.NON_TERMINAL_NODE);
                newNode.setName(Constants.BL + node.getName());
                if (node.getFields() != null) {
                    List<Field> fields = new ArrayList<>();
                    for (Field field : node.getFields()) {
                        Field newField = new Field();
                        newField.setName(field.getName());
                        if (field.getType().contains(Constants.TOKEN)) {
                            newField.setType(Constants.BL_TOKEN);
                        } else if (field.getName().contains(Constants.LIST_KEYWORD)) {
                            newField.setType(Constants.BL_LIST.replace(Constants.NODE_VARIABLE_PLACEHOLDER,
                                    Constants.BLNode));
//                            newField.setType(BL_LIST.replace(NODE_VARIABLE_PLACEHOLDER, field.getName().replace(LIST_KEYWORD, "")));
                        } else {
                            newField.setType(Constants.BLNode);
                        }
                        fields.add(newField);
                    }
                    newNode.setFields(fields);
                }
                modifiedNodeList.add(newNode);
            }
        }
        return modifiedNodeList;
    }

    public static String defineFacadeAttributes(List<Field> fieldList) {
        if (fieldList != null) {
            StringBuilder attributes = new StringBuilder();
            for (Field field : fieldList) {
                attributes.append(Constants.WHITE_SPACE).append(Constants.WHITE_SPACE).append(field.getType())
                        .append(Constants.WHITE_SPACE).append(field.getName());
                if (field.getDefaultValue() != null) {
                    attributes.append(Constants.ASSIGNMENT_OPERATOR).append(field.getDefaultValue());
                }
                attributes.append(Constants.SEMI_COLON).append(Constants.NEW_LINE);
            }
            return attributes.toString();
        } else {
            return "";
        }
    }

    private static String generateFacadeMembers(List<Field> fieldList) {
        StringBuilder facadeMembersString = new StringBuilder();
        if (fieldList == null) {
            return "";
        } else {
            int i = 0;
            for (Field field : fieldList) {
                facadeMembersString.append(Constants.FACADE_MEMBER_FUNCTION.replace(Constants.TYPE_PLACEHOLDER,
                        field.getType()).replace(Constants.ATTRIBUTE_PLACEHOLDER, field.getName()));
                if (field.getType().equals(Constants.BLNode)) {
                    facadeMembersString = new StringBuilder(facadeMembersString.toString()
                            .replace(Constants.FUNCTION_CALL_PLACEHOLDER, Constants.CALL_CREATE_FACADE));
                } else if (field.getType().contains(Constants.LIST_KEYWORD)) {
                    facadeMembersString = new StringBuilder(facadeMembersString.toString()
                            .replace(Constants.FUNCTION_CALL_PLACEHOLDER, Constants.CALL_CREATE_NODE_LIST));
                } else {
                    facadeMembersString = new StringBuilder(facadeMembersString.toString()
                            .replace(Constants.FUNCTION_CALL_PLACEHOLDER, Constants.CALL_CREATE_TOKEN));
                }
                facadeMembersString = new StringBuilder(facadeMembersString.toString()
                        .replace(Constants.INDEX_PLACEHOLDER, String.valueOf(i)));
                facadeMembersString.append(Constants.NEW_LINE);
                i++;
            }
            return facadeMembersString.toString();
        }
    }

    private static String buildCaseStatements(List<Field> fieldList) {
        StringBuilder caseStatements = new StringBuilder();
        if (fieldList == null) {
            return "";
        }
        int i = 0;
        for (Field field : fieldList) {
            caseStatements.append(Constants.CASE_KEYWORD).append(Constants.WHITE_SPACE).append(i)
                    .append(Constants.COLON).append(Constants.NEW_LINE).append(Constants.RETURN_KEYWORD)
                    .append(Constants.WHITE_SPACE).append(field.getName()).append(Constants.OPEN_BRACKETS)
                    .append(Constants.CLOSE_BRACKETS).append(Constants.SEMI_COLON).append(Constants.NEW_LINE);
            i++;
        }
        return caseStatements.toString();
    }
}

package generator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import generator.util.Common;
import generator.util.Constants;
import model.Field;
import model.Node;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacadeGenerator {
    private static List<Field> currentFieldList;
    private static List<AttributeFunction> attributeFunctionList;
    private static SwitchFunction switchFunction;

    static class AttributeFunction {
        String returnObject;
        String parentMethod;
        String name;
        int index;
        CreateFacadeCall createFacadeCall;
        AttributeFunction(String returnObject, String parentMethod, String name, int index) {
            this.returnObject = returnObject;
            this.parentMethod = parentMethod;
            this.name = name;
            this.index = index;
        }
        AttributeFunction(String returnObject, String parentMethod, String name, int index, CreateFacadeCall createFacadeCall) {
            this.returnObject = returnObject;
            this.parentMethod = parentMethod;
            this.name = name;
            this.index = index;
            this.createFacadeCall = createFacadeCall;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Node> nodes;
        nodes = Common.getTreeNodes();
        if (nodes != null) {
            List<Node> nodeList = restructureNodes(nodes);
            for (Node node : nodeList) {
                MustacheFactory mf = new DefaultMustacheFactory();
                Mustache mustache = mf.compile("facadeTemplate.mustache");
                Map<String, String> mapScope = new HashMap<>();
                mapScope.put(Constants.PACKAGE_PLACEHOLDER, Constants.FACADE_PACKAGE);
                mapScope.put(Constants.IMPORTS_PLACEHOLDER, Constants.FACADE_IMPORTS);
                mapScope.put(Constants.CLASSNAME_PLACEHOLDER, node.getName());
                mapScope.putAll(Common.buildClassName(node));
                currentFieldList = node.getFields();
                populateAttributeFunction(node.getFields());
                populateSwitchFunction(node.getFields());
                Writer writer = new StringWriter();
                Object[] scopes = new Object[2];
                scopes[0] = mapScope;
                scopes[1] = new FacadeGenerator();
                mustache.execute(writer, scopes);
                writer.flush();
                Common.writeToFile( writer.toString().replace("&lt;", "<").
                                replace("&gt;", ">"),
                        "/Users/tharindu/golang/src/github.com/wso2/AstNodes/src/main/java/generated/facade/" +
                                node.getName() + Constants.DOT + Constants.JAVA_EXT);
                currentFieldList = null;
                attributeFunctionList = null;
                switchFunction = null;
            }
        } else {
//            todo throw exception
        }
    }

    private static List<Node> restructureNodes(List<Node> nodeList) {
        List<Node> modifiedNodeList = new ArrayList<>();
        for (Node node : nodeList) {
            if (!(node.getBase().equals(Constants.SYNTAX_TOKEN)) || node.getName().equals(Constants.SYNTAX_TOKEN)) {
                Node newNode = new Node();
                newNode.setBase(Constants.NON_TERMINAL_NODE);
                newNode.setName(Constants.BL + node.getName());
                newNode.setType(node.getType());
                if (node.getFields() != null) {
                    List<Field> fields = new ArrayList<>();
                    for (Field field : node.getFields()) {
                        Field newField = new Field();
                        newField.setName(field.getName());
                        Node immediateParent = Common.getImmediateParentNode(field.getType(), nodeList);
                        if (immediateParent != null && immediateParent.getName().equals(Constants.SYNTAX_TOKEN)) {
                            newField.setType(Constants.BL_TOKEN);
                        } else if (field.getName().contains(Constants.LIST_KEYWORD)) {
                            newField.setType(Constants.BL_LIST.replace(Constants.NODE_VARIABLE_PLACEHOLDER,
                                    Constants.BLNode));
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

    private static void populateAttributeFunction(List<Field> fieldList) {
        if (fieldList != null) {
            int i = 0;
            attributeFunctionList = new ArrayList<>();
            for (Field field : fieldList) {
                if (field.getType().equals(Constants.BLNode)) {
                    attributeFunctionList.add(new AttributeFunction(Constants.BLNode, "node.childInBucket",
                            field.getName(), i, new CreateFacadeCall(i)));
                } else if (field.getType().contains(Constants.LIST_KEYWORD)) {
                    attributeFunctionList.add(new AttributeFunction("BLNodeList<BLNode>",
                            "createListNode",
                            field.getName(), i));
                } else {
                    attributeFunctionList.add(new AttributeFunction(Constants.BL_TOKEN, "createToken",
                            field.getName(), i));
                }
                i++;
            }
        }
    }

    private static void populateSwitchFunction(List<Field> fieldList) {
        if (fieldList != null) {
            int i = 0;
            List<Case> caseList = new ArrayList<>();
            for (Field field : fieldList) {
                caseList.add(new Case(field.getName(), i));
                i++;
            }
            switchFunction = new SwitchFunction(caseList);
        }
    }

    static class SwitchFunction {
        List<Case> caseList;
        SwitchFunction(List<Case> caseList) {
            this.caseList = caseList;
        }
    }

    static class Case {
         String name;
         int index;
         Case(String name, int index){
             this.name = name;
             this.index = index;
         }
    }

    static class CreateFacadeCall {
        int index;
        CreateFacadeCall(int index) {
            this.index = index;
        }
    }

    public List<Field> attributes() {
        return currentFieldList;
    }
    public List<AttributeFunction> attributeFunction() {
        return attributeFunctionList;
    }
    public SwitchFunction switchFunction(){
        return switchFunction;
    }
}

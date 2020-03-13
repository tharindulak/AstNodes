package generator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import generator.util.Common;
import generator.util.Constants;
import model.Field;
import model.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InternalGenerator {

    private static List<Node> nodes;
    private static List<Field> currentFieldList;
    private static Bucket bucket;
    private static List<ChildNode> addChildNodeList;
    private static ToStringFunction toStringFunction;
    private static FacadeClass facadeFunction;

    public static void main(String[] args) throws IOException {
        nodes = Common.getTreeNodes();
        for (Node node : nodes) {
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile("internalTemplate.mustache");
            Map<String, String> mapScope = new HashMap<>();
            mapScope.put(Constants.PACKAGE_PLACEHOLDER, Constants.INTERNAL_PACKAGE);
            mapScope.put(Constants.IMPORTS_PLACEHOLDER, Constants.INTERNAL_IMPORTS);
            mapScope.put(Constants.CLASSNAME_PLACEHOLDER, node.getName());
            mapScope.putAll(Common.buildClassName(node));
            currentFieldList = node.getFields();
            if (currentFieldList != null ) {
                bucket = new Bucket(currentFieldList.size());
                if (!node.getBase().equals(Constants.SYNTAX_TOKEN)) {
                    populateChildNode();
                }
            }
            if (!(node.getName().equals(Constants.SYNTAX_TOKEN) || node.getBase().equals(Constants.SYNTAX_TOKEN)) &&
                    !node.getName().equals(Constants.MISSING_TOKEN)) {
                populateToStringFunction(node);
            }
            if (!((node.getType() != null) && (node.getType().equals(Constants.ABSTRACT_KEYWORD)))) {
                if (!node.getBase().equals(Constants.SYNTAX_TOKEN) || node.getName().contains(Constants.SYNTAX_TOKEN)) {
                    facadeFunction = new FacadeClass(node.getName());
                }
            }
            if (node.getBase() != null) {
                Node parentNode = Common.getImmediateParentNode(node.getBase(), nodes);
                if (parentNode != null) {
                    mapScope.put(Constants.IMMEDIATE_PARENT_PLACEHOLDER,
                            superConstructorParentValues(parentNode.getFields()));
                }
            }

            Writer writer = new StringWriter();
            Object[] scopes = new Object[2];
            scopes[0] = mapScope;
            scopes[1] = new InternalGenerator();
            mustache.execute(writer, scopes);
            writer.flush();
            Common.writeToFile(writer.toString(),
                    "/Users/tharindu/golang/src/github.com/wso2/AstNodes/src/main/java/generated/internal/"
                    + node.getName() + Constants.DOT + Constants.JAVA_EXT);
            toStringFunction = null;
            facadeFunction = null;
            currentFieldList = null;
            addChildNodeList = null;
        }
    }

    private static void populateChildNode() {
        int index = 0;
        addChildNodeList = new ArrayList<>();
        for (Field field : currentFieldList) {
            addChildNodeList.add(new ChildNode(field.getName(), index));
            index++;
        }
    }

    private static void populateToStringFunction(Node node) {
        if (node.getName().equals(Constants.SYNTAX_TOKEN)) {
            toStringFunction = new ToStringFunction(Constants.KIND_PROPERTY);
        } else if (node.getBase().equals(Constants.SYNTAX_TOKEN)) {
            toStringFunction = new ToStringFunction(Constants.PROPERTY);
        }
    }

    private static String superConstructorParentValues(List<Field> fields) {
        if (fields == null) {
            return "";
        }
        StringBuilder values = new StringBuilder();
        fields.forEach(field -> values.append(Constants.PARAMETER_SEPARATOR).append(Constants.NULL_KEYWORD));
        return values.toString();
    }

    public List<Field> attributes() {
        return currentFieldList;
    }

    public Bucket bucket() {
        return bucket;
    }

    public List<ChildNode> addChildNode() {
        return addChildNodeList;
    }

    public ToStringFunction toStringFunction() {
        return toStringFunction;
    }

    public FacadeClass facadeFunction() {
        return facadeFunction;
    }

    static class Bucket {
        int bucketCount;
        Bucket(int bucketCount) {
            this.bucketCount = bucketCount;
        }
    }

    static class ChildNode {
        String name;
        int index;
        ChildNode(String name, int index) {
            this.name = name;
            this.index = index;
        }
    }

    static class ToStringFunction {
        String kind;
        ToStringFunction(String kind) {
            this.kind = kind;
        }
    }

    static class FacadeClass {
        String facadeClass;
        FacadeClass(String facadeClass) {
            this.facadeClass = facadeClass;
        }
    }
}

package gen;

import com.google.gson.Gson;
import javafx.scene.SnapshotParametersBuilder;
import model.Field;
import model.Node;
import model.Tree;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ClassGenerator {
    private static final String ABSTRACT_CLASS = "abstract class";
    private static final String ABSTRACT_KEYWORD = "abstract";
    private static final String ATTRIBUTE_VISIBILITY = "private";
    private static final String CLASS_VISIBILITY = "public";
    private static final String PUBLIC_KEYWORD = "public";
    private static final String EXTENDS_KEYWORD = "extends";
    private static final String OPEN_BRACKETS = "(";
    private static final String CLOSE_BRACKETS = ")";
    private static final String OPEN_PARENTHESIS = "{";
    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final String THIS_KEYWORD = "this";
    private static final String DOT = ".";
    private static final String SEMI_COLON = ";";
    private static final String INTERFACE = "interface";
    private static final String CLOSE_PARENTHESIS = "}";
    private static final String LESS_THAN_SYMBOL = "<";
    private static final String GREATER_THAN_SYMBOL = ">";
    private static final String ASSIGNMENT_OPERATOR = "=";
    private static final String WHITE_SPACE = " ";
    private static final String PARAMETER_SEPARATOR = ",";
    private static final String CLASS_KEYWORD = "class";
    private static final String JAVA_EXT = "java";
    private static final String INT_TYPE = "int";
    private static final String DOUBLE_TYPE = "double";
    private static final String TO_STRING_SIGNATURE = "toString";
    private static final String RETURN_KEYWORD = "return";
    private static final String STRING_KEYWORD = "String";
    private static final String DOUBLE_QUOTES = "\"\"";
    private static final String CONCAT_SYMBOL = "+";
    private static final String GEN_WHITESPACE = "\" \"";
    private static final String LIST_KEYWORD = "List";
    private static final String LIST_IMPORT_STRING = "import java.util.List;";
    private static final String SUB_STRING_KEYWORD = "substring";
    private static final String LENGTH_METHOD = "length()";
    private static final String MINUS = "-";
    private static final String ONE = "1";
    private static final String LIST_ITERATOR_FUNCTION = "StringBuilder <builderName> = new StringBuilder();" +
            "\nfor (<Type> var: <ListName>) {\n<builderName>.append(var).append(\" \");}";
    private static final String TYPE_REGEX = "<Type>";
    private static final String LIST_NAME_REGEX = "<ListName>";
    private static final String BUILDER_REGEX = "<builderName>";
    private static final String BUILDER = "Builder";
    private static List<Node> nodes;

//    private static Map<String, Node> nodeMap;

    public static void main(String[] args) /*throws IOException*/ {
        try {
            initTreeComponents();
        } catch (IOException e) {
            System.out.println(e);
        }
        for (Node node : nodes) {
            StringBuilder generateClass = new StringBuilder();
            if (node.getField() != null) {
                generateClass.append(generateImports(node.getField()))
                        .append(buildClassName(node))
                        .append(defineAttributes(node.getField()))
                        .append(buildDefaultConstructor(node.getName()))
                        .append(buildParameterizedConstructor(node.getName(), node.getField()))
                        .append(createToStringMethod(node.getField()))
                        .append(NEW_LINE).append(CLOSE_PARENTHESIS).append(NEW_LINE).append(NEW_LINE);
            } else {
                generateClass.append(buildClassName(node))
                        .append(buildDefaultConstructor(node.getName()))
                        .append(NEW_LINE).append(CLOSE_PARENTHESIS).append(NEW_LINE);
            }

            try {
                writeToFile(generateClass.toString(), "src/main/java/" + node.getName() + DOT + JAVA_EXT);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
//        System.out.println(generatedClasses);
    }

    private static void initTreeComponents() throws IOException {
        Gson gson = new Gson();
        Tree ast = gson.fromJson(new FileReader("tree.json"), Tree.class);
        nodes = ast.getNode();
    }

    private static void writeToFile(String data, String filePath) throws IOException {
        File file = new File(filePath);
        FileWriter fr = new FileWriter(file);
        fr.write(data);
        fr.close();
    }

    private static String buildClassName(Node node) {
        StringBuilder generatedClassName = new StringBuilder(CLASS_VISIBILITY + WHITE_SPACE);

        // Check for interface or abstract classes
        if (node.getType() == null) {
            generatedClassName.append(CLASS_KEYWORD).append(WHITE_SPACE).append(node.getName());
        } else if (node.getType().equals(INTERFACE)) {
            generatedClassName.append(INTERFACE).append(WHITE_SPACE).append(node.getName());
        } else if (node.getType().equals(ABSTRACT_KEYWORD)) {
            generatedClassName.append(ABSTRACT_CLASS).append(WHITE_SPACE).append(node.getName());
        }
        // Check for parent classes
        if (node.getExt() != null) {
            generatedClassName.append(WHITE_SPACE).append(EXTENDS_KEYWORD).append(WHITE_SPACE).append(node.getExt());
        }
        generatedClassName.append(OPEN_PARENTHESIS).append(NEW_LINE);
        return generatedClassName.toString();
    }

    private static String buildDefaultConstructor(String className) {
        return PUBLIC_KEYWORD + WHITE_SPACE + className + WHITE_SPACE + OPEN_BRACKETS + CLOSE_BRACKETS
                + WHITE_SPACE + OPEN_PARENTHESIS + WHITE_SPACE + NEW_LINE + CLOSE_PARENTHESIS + NEW_LINE;
    }

    private static String defineAttributes(List<Field> fieldList) {
        if (fieldList != null) {
            StringBuilder attributes = new StringBuilder();
            fieldList.forEach(field -> attributes.append(ATTRIBUTE_VISIBILITY).append(WHITE_SPACE)
                    .append(field.getType()).append(WHITE_SPACE).append(field.getName())
                    .append(SEMI_COLON).append(NEW_LINE));
            return attributes.toString();
        } else {
            return "";
        }
    }

    private static String buildParameterizedConstructor(String className, List<Field> fieldList) {
        StringBuilder constructor = new StringBuilder(PUBLIC_KEYWORD + WHITE_SPACE + className + WHITE_SPACE +
                OPEN_BRACKETS);
        StringBuilder initValues = new StringBuilder();
        int i = 0;
        for (Field field : fieldList) {
            initValues.append(THIS_KEYWORD).append(DOT).append(field.getName()).append(ASSIGNMENT_OPERATOR)
                    .append(field.getName()).append(SEMI_COLON);
            constructor.append(field.getType()).append(WHITE_SPACE).append(field.getName());
            i++;
            if (i != fieldList.size()) {
                constructor.append(PARAMETER_SEPARATOR);
                initValues.append(NEW_LINE);
            }
        }
        constructor.append(CLOSE_BRACKETS).append(OPEN_PARENTHESIS).append(NEW_LINE).append(initValues)
                .append(NEW_LINE).append(CLOSE_PARENTHESIS).append(NEW_LINE);
        return constructor.toString();
    }

    private static String generateImports(List<Field> fieldList) {
        StringBuilder importString = new StringBuilder();
        for (Field field : fieldList) {
            if (field.getName().contains(LIST_KEYWORD)) {
                importString.append(LIST_IMPORT_STRING).append(NEW_LINE);
            }
            // add other imports as well
        }
        return importString.toString();
    }

    private static String createToStringMethod(List<Field> fieldList) {
        StringBuilder toStringText = new StringBuilder(PUBLIC_KEYWORD + WHITE_SPACE + STRING_KEYWORD + WHITE_SPACE
                + TO_STRING_SIGNATURE + WHITE_SPACE + OPEN_BRACKETS + CLOSE_BRACKETS + OPEN_PARENTHESIS + NEW_LINE);

        int i = 0;
        for (Field field : fieldList) {
            if (field.getType().contains(LIST_KEYWORD)){
                String forEachLoop = LIST_ITERATOR_FUNCTION.replace(TYPE_REGEX, getListType(field.getType()))
                        .replace(LIST_NAME_REGEX, field.getName())
                        .replace(BUILDER_REGEX, field.getName() + BUILDER);
                toStringText.append(forEachLoop).append(NEW_LINE);
            }
        }
        toStringText.append(RETURN_KEYWORD).append(WHITE_SPACE);

        for (Field field : fieldList) {
            if ((i == 0) || (fieldList.size() == 1)) {
                if (field.getType().equals(INT_TYPE) || field.getType().equals(DOUBLE_TYPE)) {
                    toStringText.append(field.getName()).append(CONCAT_SYMBOL).append(DOUBLE_QUOTES)
                            .append(WHITE_SPACE);
                } else if (field.getType().equals(STRING_KEYWORD)) {
                    toStringText.append(field.getName()).append(WHITE_SPACE);
                } else if (field.getType().contains(LIST_KEYWORD)) {
//                    toStringText.append(field.getName()).append(DOT).append(TO_STRING_SIGNATURE)
//                            .append(OPEN_BRACKETS + CLOSE_BRACKETS).append(DOT).append(SUB_STRING_KEYWORD)
//                            .append(OPEN_BRACKETS).append(ONE).append(COMMA).append(field.getName()).append(DOT)
//                            .append(TO_STRING_SIGNATURE).append(OPEN_BRACKETS).append(CLOSE_BRACKETS).append(DOT)
//                            .append(LENGTH_METHOD).append(MINUS).append(ONE).append(CLOSE_BRACKETS);
                    toStringText.append(field.getName() + BUILDER);
                } else {
                    toStringText.append(field.getName()).append(DOT).append(TO_STRING_SIGNATURE)
                            .append(OPEN_BRACKETS + CLOSE_BRACKETS);
                }
            } else {
                if (field.getType().equals(INT_TYPE) || field.getType().equals(DOUBLE_TYPE)) {
                    toStringText.append(CONCAT_SYMBOL).append(GEN_WHITESPACE).append(CONCAT_SYMBOL)
                            .append(field.getName()).append(CONCAT_SYMBOL).append(DOUBLE_QUOTES).append(WHITE_SPACE);
                } else if (field.getType().equals(STRING_KEYWORD)) {
                    toStringText.append(CONCAT_SYMBOL).append(GEN_WHITESPACE).append(CONCAT_SYMBOL)
                            .append(field.getName()).append(WHITE_SPACE);
                } else if (field.getType().contains(LIST_KEYWORD)) {
                    toStringText.append(CONCAT_SYMBOL).append(GEN_WHITESPACE).append(CONCAT_SYMBOL).append(field.getName() + BUILDER);
//                            .append(field.getName()).append(DOT).append(TO_STRING_SIGNATURE)
//                            .append(OPEN_BRACKETS).append(CLOSE_BRACKETS).append(DOT).append(SUB_STRING_KEYWORD)
//                            .append(OPEN_BRACKETS).append(ONE).append(COMMA).append(field.getName()).append(DOT)
//                            .append(TO_STRING_SIGNATURE).append(OPEN_BRACKETS).append(CLOSE_BRACKETS).append(DOT)
//                            .append(LENGTH_METHOD).append(MINUS).append(ONE).append(CLOSE_BRACKETS);
                } else {
                    toStringText.append(CONCAT_SYMBOL).append(GEN_WHITESPACE).append(CONCAT_SYMBOL)
                            .append(field.getName()).append(DOT).append(TO_STRING_SIGNATURE)
                            .append(OPEN_BRACKETS + CLOSE_BRACKETS).append(WHITE_SPACE);
                }
            }
            i++;
        }
        toStringText.append(SEMI_COLON).append(NEW_LINE).append(CLOSE_PARENTHESIS);
        return toStringText.toString();
    }

    private static String getListType (String listTxt) {
        return (listTxt.replace(LIST_KEYWORD + LESS_THAN_SYMBOL, ""))
                .replace(GREATER_THAN_SYMBOL, "");
    }

//    private void createTreeMap() {
//        nodes.forEach(node -> nodeMap.put(node.getName(), node));
//    }

}

package generator;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import java.io.IOException;
import java.util.*;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class HandleBars {
//    public static void main(String[] args) throws IOException {
//        Handlebars handlebars = new Handlebars();
//        Template template = handlebars.compile("test");
//        Map<String, String> model = new HashMap<>();
//        model.put("x", "AAAAAAAA");
//        model.put("y", "BBBB");
//        System.out.println(template.apply("{\n" +
//                "  blogs: [\n" +
//                "    title: {\n" +
//                "      story: {\n" +
//                "        name: \"Handlebars.java rocks!\"\n" +
//                "      }\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}"));
//    }

    static List<Item> params(String s) {
        return Arrays.asList(
                new Item("Item 1", "$19.99", Arrays.asList(new Feature("New!"), new Feature("Awesome!"))),
                new Item("Item 2", "$29.99", Arrays.asList(new Feature("Old."), new Feature("Ugly."))),
        new Item("Item 3", "$39.99", Arrays.asList(new Feature("Old."), new Feature("Ugly.")))
        );
    }

    List<Item> x = new ArrayList<>();

    static class Item {
        Item(String name, String price, List<Feature> features) {
            this.name = name;
            this.price = price;
        }

        String name, price;
        List<Feature> features;
    }

    static class Feature {
        Feature(String description) {
            this.description = description;
        }

        String description;
    }

    public static void main(String[] args) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("template.mustache");
        mustache.execute(new PrintWriter(System.out), new HandleBars()).flush();
    }
}

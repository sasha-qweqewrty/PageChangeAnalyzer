package com.agileengine;

import com.agileengine.util.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.util.List;

public class App {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage:\n[path to original document] [path to updated document]");
            System.err.println("Expected 2 arguments, will now exit.");
        } else {
            File newFile = new File(args[1]);
            File originalFile = new File(args[0]);

            Element element = Utils.findElementById(originalFile, "make-everything-ok-button");
            Document newDocument = Utils.findElementById(newFile);

            List<Element> similarElements = new SimilarElementFinder(0.1f)
                    .getPathToElementForCriteria(newDocument, element.attributes().clone());
            if (!similarElements.isEmpty()) {
                System.out.println("List of possible matches for given minimal match rate:");
                similarElements.forEach(e -> System.out.println(Utils.getPathAsString(e)));
            } else {
                System.out.println("Unable to locate changed element.");
            }
        }
    }
}

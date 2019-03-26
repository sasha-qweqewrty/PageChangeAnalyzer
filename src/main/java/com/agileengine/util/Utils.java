package com.agileengine.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

public class Utils {

    private static String CHARSET_NAME = "utf8";

    public static Element findElementById(File htmlFile, String targetElementId) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            return doc.getElementById(targetElementId);

        } catch (IOException e) {
            System.err.println("Error reading file '" + htmlFile.getAbsolutePath() + "'");
            return null;
        }
    }

    public static Document findElementById(File htmlFile) {
        try {
            return Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error reading file '" + htmlFile.getAbsolutePath() + "'");
            return null;
        }
    }

    public static String getPathAsString(Element element) {
        String result = element.tagName();
        while (element.hasParent()) {
            result = element.parent().tagName() + " > " + result;
            element = element.parent();
        }

        return result;
    }
}

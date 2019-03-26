package com.agileengine;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimilarElementFinder {

    private static final float DEFAULT_TOLERANCE = 0.5f;

    private final float minimalMatchRate;

    public SimilarElementFinder() {
        this(DEFAULT_TOLERANCE);
    }

    public SimilarElementFinder(float minimalMatchRate) {
        if (minimalMatchRate <= 0 || minimalMatchRate > 1) {
            throw new IllegalArgumentException();
        }
        this.minimalMatchRate = minimalMatchRate;
    }

    public List<Element> getPathToElementForCriteria(Document document, Attributes criteria) {
        List<Element> elements = new ArrayList<>();
        for (Element e : document.getAllElements()) {
            if (isElementMatchingCriteria(e, criteria)) {
                elements.add(e);
            }
        }

        return elements;
    }

    private boolean isElementMatchingCriteria(Element element, Attributes criteria) {
        int amountOfMatchedCriteria = 0;
        for (Attribute attr : criteria) {
            String documentValue = element.attributes().get(attr.getKey());
            String criteriaValue = attr.getValue();
            if (Objects.equals(documentValue, criteriaValue)) {
                amountOfMatchedCriteria++;
            }
        }

        return amountOfMatchedCriteria > criteria.size() * minimalMatchRate;
    }
}

package com.xml.analyzer.result;

import org.xml.sax.Attributes;

/**
 * Part of the xml parsing Result. Concrete subclasses that implement the interface
 * are obliged to implement resetDetails, accumulateDetails and finalizeResultDetails methods.
 */
public interface ResultDetails {
    void resetDetails();

    void accumulateDetails(String qName, Attributes attrs);

    void finalizeResultDetails();
}

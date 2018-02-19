package com.xml.analyzer.parser.posts.sax;

import com.xml.analyzer.node.XmlNode;
import com.xml.analyzer.result.Result;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.net.URL;

@Component
public class SaxXmlParser {
    @Getter
    @Setter
    @Autowired
    private SaxXmlHandler saxXmlHandler;

    public void parseXMLFromUrl(String url, XmlNode xmlNode, Result result) {
        saxXmlHandler.setResult(result);
        saxXmlHandler.setXmlNode(xmlNode);

        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            URL linkURL = new URL(url);

            sp.parse(new InputSource(linkURL.openStream()), saxXmlHandler);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

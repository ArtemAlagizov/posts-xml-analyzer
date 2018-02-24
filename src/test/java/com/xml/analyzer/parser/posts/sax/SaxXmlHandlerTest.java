package com.xml.analyzer.parser.posts.sax;

import com.xml.analyzer.node.XmlNode;
import com.xml.analyzer.result.Result;
import com.xml.analyzer.result.ResultDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.xml.sax.Attributes;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaxXmlHandlerTest {
    @InjectMocks
    private SaxXmlHandler saxXmlHandler;

    @Mock
    private Result result;

    @Mock
    private ResultDetails resultDetails;

    @Mock
    private Attributes attrs;

    @Mock
    private XmlNode xmlNode;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        when(xmlNode.getName()).thenAnswer(invocationOnMock -> "xmlNodeName");
        when(xmlNode.isInsideThisNode()).thenAnswer(invocationOnMock -> true);
        when(result.getResultDetails()).thenAnswer(invocationOnMock -> resultDetails);
    }

    @Test
    public void startElementShouldCallCorrectMethodsWhenNotInsideXmlNode() throws Exception {
        saxXmlHandler.startElement("namespaceURI", "localName", "xmlNodeName", attrs);

        verify(xmlNode, times(1)).getName();
        verify(resultDetails, times(1)).resetDetails();
        verify(resultDetails, never()).accumulateDetails(anyString(), eq(attrs));
    }

    @Test
    public void startElementShouldCallCorrectMethodsWhenIsInsideXmlNode() throws Exception {
        saxXmlHandler.startElement("namespaceURI", "localName", "xmlNodeNameInside", attrs);

        verify(xmlNode, times(1)).getName();
        verify(resultDetails, times(1)).accumulateDetails(anyString(), eq(attrs));
        verify(xmlNode, times(1)).isInsideThisNode();
        verify(resultDetails, never()).resetDetails();
    }

    @Test
    public void endElementShouldCallCorrectMethods() throws Exception {
        saxXmlHandler.endElement("namespaceURI", "localName", "xmlNodeName");

        verify(xmlNode, times(1)).getName();
        verify(xmlNode, times(1)).isInsideThisNode();
        verify(xmlNode, times(1)).setInsideThisNode(anyBoolean());
    }
}
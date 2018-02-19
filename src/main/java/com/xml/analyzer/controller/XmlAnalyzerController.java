package com.xml.analyzer.controller;

import com.xml.analyzer.parser.posts.PostsXmlParser;
import com.xml.analyzer.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller, provides endpoint for /analyze/
 */
@RestController
@RequestMapping("/analyze")
public class XmlAnalyzerController {

    @Autowired
    private PostsXmlParser postsXmlParser;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Result analyzePosts(@RequestParam("url") final String url) {
        return postsXmlParser.parseXMLFromUrl(url);
    }
}

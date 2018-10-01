package hackathon.rest;
/**
 * Copyright 2018, Charter Communications,  All rights reserved.
 */

import hackathon.service.SentimentAnalysisService;
import hackathon.TwitterInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jkendall1
 */
@RestController
public class SentimentRS {

   @Autowired
    SentimentAnalysisService sentimentAnalysisService;

   @RequestMapping("/runanalysis")
    void runAnalysis() {
       sentimentAnalysisService.doSentimentAnalysis();

   }

   @RequestMapping("/getByAuthor")
   public List<TwitterInfo> getByAuthor(@RequestParam String author) {
       return sentimentAnalysisService.getByAuthor(author);

    }

}

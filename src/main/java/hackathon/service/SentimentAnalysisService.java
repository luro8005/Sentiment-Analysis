package hackathon.service;
/**
 * Copyright 2018, Charter Communications,  All rights reserved.
 */

import hackathon.Sentiment;
import hackathon.TwitterInfo;
import hackathon.repository.TwitterInfoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jkendall1
 */
@Service
public class SentimentAnalysisService {

    @Autowired
    TwitterInfoRepository repository;

    public void doSentimentAnalysis() {

        List<TwitterInfo> twitterInfos = repository.findAll();

        for (TwitterInfo twitterInfo : twitterInfos) {

            String sentiment = Sentiment.getSentiment(twitterInfo.getText());
            System.out.println(twitterInfo.getText() + " ===>>> " + sentiment);
        }
    }

    public List<TwitterInfo> getByAuthor(String author) {

        return repository.findByAuthor(author);
    }


}

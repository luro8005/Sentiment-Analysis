package hackathon;
/**
 * Copyright 2018, Charter Communications,  All rights reserved.
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jkendall1
 */
public class SentimentAnalysis {

    @Autowired
    SentimentRepository repository;

    public void doSentimentAnalysis() {

        List<TweetInfo> tweetInfos = repository.findAll();

        for (TweetInfo tweetInfo : tweetInfos) {

            String sentiment = Sentiment.getSentiment(tweetInfo.getText());
            System.out.println(tweetInfo.getText() + " ===>>> " + sentiment);
        }
    }


}

package hackathon;
/**
 * Copyright 2018, Charter Communications,  All rights reserved.
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jkendall1
 */
public class Curator {

    @Autowired
    SentimentRepository repository;

    List<TweetInfo> tweetInfos = repository.findAll();




}

package hackathon;
/**
 * Copyright 2018, Charter Communications,  All rights reserved.
 */

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author jkendall1
 */
public interface SentimentRepository extends MongoRepository<TweetInfo, String> {

}

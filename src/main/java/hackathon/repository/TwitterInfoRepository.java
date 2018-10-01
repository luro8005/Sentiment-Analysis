package hackathon.repository;
/**
 * Copyright 2018, Charter Communications,  All rights reserved.
 */

import hackathon.TwitterInfo;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jkendall1
 */
@Repository
public interface TwitterInfoRepository extends MongoRepository<TwitterInfo, String> {

    List<TwitterInfo> findByAuthor(String author);

}

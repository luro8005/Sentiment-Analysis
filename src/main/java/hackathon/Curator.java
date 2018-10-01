package hackathon;
/**
 * Copyright 2018, Charter Communications,  All rights reserved.
 */

import hackathon.repository.TwitterInfoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jkendall1
 */
public class Curator {

    @Autowired
    TwitterInfoRepository repository;

    List<TwitterInfo> twitterInfos = repository.findAll();




}

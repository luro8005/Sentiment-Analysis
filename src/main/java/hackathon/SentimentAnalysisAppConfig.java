package hackathon; /**
 * Copyright 2018, Charter Communications,  All rights reserved.
 */

import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author jkendall1
 */
@EnableMongoAuditing
@EnableMongoRepositories
@SpringBootApplication(scanBasePackages = {"hackathon"})
@PropertySources({
    @PropertySource(value = "classpath:/SentimentAnalysis.properties", ignoreResourceNotFound = false),
})

public class SentimentAnalysisAppConfig extends AbstractMongoConfiguration {

    public static void main(String[] args) {SpringApplication.run(SentimentAnalysisAppConfig.class, args);}

    @Value("${spring.data.mongodb.database}")
    String mongoDbName;
    @Value("${spring.data.mongodb.host}")
    String hostName;

    @Override
    protected String getDatabaseName() {
        return mongoDbName;
    }

    @Override
    public com.mongodb.MongoClient mongoClient() {
        return new MongoClient(hostName);
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        ArrayList<String> basePackages = new ArrayList();
        basePackages.add("hackathon");
        return basePackages;
    }

    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        return super.mongoTemplate();
    }
}

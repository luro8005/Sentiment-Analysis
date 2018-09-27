package hackathon; /**
 * Copyright 2018, Charter Communications,  All rights reserved.
 */

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author jkendall1
 */
@EnableMongoAuditing
@EnableReactiveMongoRepositories
@SpringBootApplication(scanBasePackages = {"hackathon"})
@PropertySources({
    @PropertySource(value = "classpath:/sentimentanalysis-local.properties", ignoreResourceNotFound = false),
    @PropertySource(value = "file:/config-volume/sentimentanalysis-${CLOUD_STACK}.properties", ignoreResourceNotFound = true)
})
public class SentimentAnalysisAppConfig extends AbstractReactiveMongoConfiguration {

    public static void main(String[] args) {SpringApplication.run(SentimentAnalysisAppConfig.class, args);}

    @Value("${spring.data.mongodb.database}")
    String mongoDbName;

    @Override
    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return mongoDbName;
    }
}

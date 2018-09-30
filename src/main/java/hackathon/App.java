package hackathon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.Iterator;

import static com.mongodb.client.model.Filters.eq;

public class App {
    public static void main(String[] args) {

        MongoDatabase database = getMongoDatabase();

        MongoCollection<Document> twits = database.getCollection("sentiment");

        FindIterable<Document> tweetsWithoutSentiments = getTweetsWithoutSentiments(twits);

        Iterator it = tweetsWithoutSentiments.iterator();
        while (it.hasNext()) {
            Document document = (Document) it.next();
            String json = document.toJson();
            try {
                updateSentiments(twits, json);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void updateSentiments(MongoCollection<Document> twits, String json) throws IOException {
        TweetInfo tweet = new ObjectMapper().readValue(json, TweetInfo.class);
        tweet.setSentiment(Sentiment.getSentiment(tweet.getText()));

        twits.updateOne(eq("timestamp", tweet.getTimestamp()), Updates.set("sentiment", tweet.getSentiment()));
    }

    private static FindIterable<Document> getTweetsWithoutSentiments(MongoCollection<Document> twits) {
        Bson query = eq("sentiment", "");

        return twits.find(query);
    }

    private static MongoDatabase getMongoDatabase() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        return mongoClient.getDatabase("sentimentanalysis");
    }

}
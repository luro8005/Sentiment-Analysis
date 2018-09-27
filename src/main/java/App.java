import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.Iterator;

import static com.mongodb.client.model.Filters.eq;

public class App {
    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> twits = database.getCollection("twitterinfo");

        Bson query = eq("sentiment", "");

        FindIterable<Document> iterDoc = twits.find(query);
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            Document document = (Document) it.next();
            String json = document.toJson();
            try {
                TweetInfo tweet = new ObjectMapper().readValue(json, TweetInfo.class);
                String sentiment = Sentiment.getSentiment(tweet.getText());
                System.out.println(tweet.getText() + " ===>>> " + sentiment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

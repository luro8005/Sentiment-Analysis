import twitter4j.*;

public class App {
    public static void main(String[] args) {
        String text = "I don't like the #Spectrum app";

        Sentiment.getSentiment(text);

        System.out.println("Done.");

        try {
            Twitter twitter = TwitterFactory.getSingleton();
            Query query = new Query("hello");
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }
        }
        catch (TwitterException e) {
            e.printStackTrace();
        }
    }

}

import twitter4j.*;

public class Tweets {
    // The factory instance is re-useable and thread safe.

    public Tweets() throws TwitterException {

        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("hello");
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }

}

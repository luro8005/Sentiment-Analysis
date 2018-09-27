package hackathon;

public class App {
    public static void main(String[] args) {
        String text = "I don't like the #Spectrum app";

        Sentiment.getSentiment(text);

        System.out.println("Done.");

    }

}

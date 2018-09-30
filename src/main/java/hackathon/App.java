package hackathon;

import hackathon.service.SentimentAnalysisService;

public class App {
    public static void main(String[] args) {

        SentimentAnalysisService sentimentAnalysis = new SentimentAnalysisService();
        sentimentAnalysis.doSentimentAnalysis();
    }

}

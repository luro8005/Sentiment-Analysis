package hackathon;

import hackathon.dataVisualization.Figures;
import hackathon.dataVisualization.SampleData;

public class App {
    public static void main(String[] args) {
        String text = "I don't like the #Spectrum app";

        Sentiment.getSentiment(text);

        System.out.println("Done.");

        System.out.println();

        SampleData.showSampleTables();

        System.out.println();

        SampleData.SeparateByMonth();

        System.out.println();

        Figures.CreateLineChart();

    }

}
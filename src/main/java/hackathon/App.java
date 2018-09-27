package hackathon;

import tech.tablesaw.api.DoubleColumn;

public class App {
    public static void main(String[] args) {
        String text = "I don't like the #Spectrum app";

        Sentiment.getSentiment(text);

        System.out.println("Done.");

        System.out.println();

        double[] numbers = {1, 2, 3, 4};
        DoubleColumn nc = DoubleColumn.create("Test", numbers);
        System.out.println(nc.print());

        DoubleColumn nc2 = nc.multiply(7);
        System.out.println(nc2.print());

    }

}

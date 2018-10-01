package hackathon.dataVisualization;

import tech.tablesaw.api.*;

import java.time.LocalDate;

public class SampleData {



    public static void showSampleColumns() {

        double[] numbers = {1, 2, 3, 4};
        DoubleColumn nc = DoubleColumn.create("Test", numbers);
        System.out.println(nc.print());

        DoubleColumn nc2 = nc.multiply(7);
        System.out.println(nc2.print());

        double stdDev = nc2.standardDeviation();
        System.out.println(stdDev);

    }

    public static void showSampleTables() {

        String[] author = {"Luis", "Joe", "William","Pavel", "Andy", "Ashley", "Jim", "Jesus", "Tim", "Bill", "AJ", "CJ"};
        String[] text = {"yes", "no", "no", "no", "no", "no", "yes", "no", "no", "maybe", "maybe", "yes no"};
        LocalDate firstSep = LocalDate.of(2018, 9,1);
        LocalDate firstOct = LocalDate.of(2018, 10,1);
        LocalDate firstNov = LocalDate.of(2018, 11,1);
        LocalDate firstDec = LocalDate.of(2018, 12,1);
        LocalDate[] dates = {firstSep, firstSep.plusDays(5), firstSep.plusDays(20), firstOct, firstOct.plusDays(7),
                firstOct.plusDays(5), firstNov, firstNov.plusDays(7), firstDec, firstDec.plusDays(20), firstDec.plusDays(3),
                firstDec.plusDays(10)};


        String[] sentiment = {"positive", "negative","negative", "negative", "negative","negative", "positive", "negative","negative",
                "neutral", "neutral", "mixed"};

        Table tweets = Table.create("Raw Customer Sentiment")
                .addColumns(
                        StringColumn.create("sentiment", sentiment),
                        StringColumn.create("text", text),
                        DateColumn.create("date", dates),
                        StringColumn.create("author", author));

        System.out.println(tweets.print());


//
//        Table positives = newTable.where(newTable.dateColumn("date").isEqualTo(first).and(newTable.
//                stringColumn("sentiment").isEqualTo("positive")));
//
//        System.out.println(positives.print());
//
//        System.out.println("Positives for day 1 count:");
//
//        System.out.println(positives.rowCount());
//
//        System.out.println();
//
//        Table negatives = newTable.where(newTable.dateColumn("date").isEqualTo(first).and(newTable.
//                stringColumn("sentiment").isEqualTo("negative")));
//
//        System.out.println(negatives.print());
//
//        System.out.println("Negatives for day 1 count:");
//
//        System.out.println(negatives.rowCount());
//
//        int sentimentRowSize = singleDates.size();
//        ArrayList<Double> defaultSentimentRows = null;
//
//        for(int i = 0; i <= sentimentRowSize; i++){
//            defaultSentimentRows.add(0.0);
//        }



//
//        Table evaluatedTable = Table.create("Evaluated Table").
//                addColumns(
//                        DateColumn.create("date", singleDates),
//                        DoubleColumn.create("positive", defaultSentimentRows)
//                );
//
//        System.out.println(evaluatedTable.print());
    }

    public static void SeparateByMonth() {

        String[] author = {"Luis", "Joe", "William","Pavel", "Andy", "Ashley", "Jim", "Jesus", "Tim", "Bill", "AJ", "CJ"};
        String[] text = {"yes", "no", "no", "no", "no", "no", "yes", "no", "no", "maybe", "maybe", "yes no"};
        LocalDate firstSep = LocalDate.of(2018, 9,1);
        LocalDate firstOct = LocalDate.of(2018, 10,1);
        LocalDate firstNov = LocalDate.of(2018, 11,1);
        LocalDate firstDec = LocalDate.of(2018, 12,1);
        LocalDate[] dates = {firstSep, firstSep.plusDays(5), firstSep.plusDays(20), firstOct, firstOct.plusDays(7),
                firstOct.plusDays(5), firstNov, firstNov.plusDays(7), firstDec, firstDec.plusDays(20), firstDec.plusDays(3),
                firstDec.plusDays(10)};


        String[] sentiment = {"positive", "negative","negative", "negative", "negative","negative", "positive", "negative","negative",
                "neutral", "neutral", "mixed"};

        Table tweets = Table.create("Raw Customer Sentiment")
                .addColumns(
                        StringColumn.create("sentiment", sentiment),
                        StringColumn.create("text", text),
                        DateColumn.create("date", dates),
                        StringColumn.create("author", author));

        Table septemberTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstSep, firstSep.plusWeeks(4)));
        int septemberPositiveTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstSep, firstSep.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("positive"))).rowCount();
        int septemberNegativeTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstSep, firstSep.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("negative"))).rowCount();
        int septemberNeutralTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstSep, firstSep.plusWeeks(4)).
                        and(tweets.stringColumn("sentiment").isEqualTo("neutral"))).rowCount();
        int septemberMixedTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstSep, firstSep.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("mixed"))).rowCount();

        Table novemberTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstOct,firstOct.plusWeeks(4)));
        int octoberPositiveTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstOct,firstOct.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("positive"))).rowCount();
        int octoberNegativeTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstOct,firstOct.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("negative"))).rowCount();
        int octoberNeutralTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstOct,firstOct.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("neutral"))).rowCount();
        int octoberMixedTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstOct,firstOct.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("mixed"))).rowCount();

        Table octoberTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstNov,firstNov.plusWeeks(4)));
        int novemberPositiveTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstNov,firstNov.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("positive"))).rowCount();
        int novemberNegativeTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstNov,firstNov.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("negative"))).rowCount();
        int novemberNeutralTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstNov,firstNov.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("neutral"))).rowCount();
        int novemberMixedTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstNov,firstNov.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("mixed"))).rowCount();

        Table decemberTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstDec,firstDec.plusWeeks(4)));
        int decemberPositiveTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstDec,firstDec.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("positive"))).rowCount();
        int decemberNegativeTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstDec,firstDec.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("negative"))).rowCount();
        int decemberNeutralTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstDec,firstDec.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("neutral"))).rowCount();
        int decemberMixedTweets = tweets.where(tweets.dateColumn("date").isBetweenIncluding(firstDec,firstDec.plusWeeks(4)).
                and(tweets.stringColumn("sentiment").isEqualTo("mixed"))).rowCount();

        System.out.println(septemberTweets);
        System.out.println("positive = " + septemberPositiveTweets);
        System.out.println("negative = " + septemberNegativeTweets);
        System.out.println("neutral = " + septemberNeutralTweets);
        System.out.println("mixed = " + septemberMixedTweets);
        System.out.println();
        System.out.println(novemberTweets);
        System.out.println("positive = " + octoberPositiveTweets);
        System.out.println("negative = " + octoberNegativeTweets);
        System.out.println("neutral = " + octoberNeutralTweets);
        System.out.println("mixed = " + octoberMixedTweets);
        System.out.println();
        System.out.println(octoberTweets);
        System.out.println("positive = " + novemberPositiveTweets);
        System.out.println("negative = " + novemberNegativeTweets);
        System.out.println("neutral = " + novemberNeutralTweets);
        System.out.println("mixed = " + novemberMixedTweets);
        System.out.println();
        System.out.println(decemberTweets);
        System.out.println("positive = " + decemberPositiveTweets);
        System.out.println("negative = " + decemberNegativeTweets);
        System.out.println("neutral = " + decemberNeutralTweets);
        System.out.println("mixed = " + decemberMixedTweets);


    }
}

package hk.edu.hkbu;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] stocks = new String[] {
                "BAC",
                "MARA",
                "SOFI",
                "PFE"
        };
        Arrays.stream(stocks).forEach( e ->
                (new YahooFinancier(e)).generateFile()
        );

    }
}
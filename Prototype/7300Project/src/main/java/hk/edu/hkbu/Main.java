package hk.edu.hkbu;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        if (args.length == 1) {
            String e = args[0];
            YahooFinancier yf = new YahooFinancier(e);
            yf.generateFile();
            yf.generateFile2();
        }
//        String[] stocks = new String[] {
//
//                "AMD",
//                "F",
//                "GD",
//                "GE",
//                "T",
//                "MCD",
//                "GD",
//                "LMT",
//                "RTX",
//                "AAPL",
//        };
//        Arrays.stream(stocks).forEach( e -> {

//        });

    }
}
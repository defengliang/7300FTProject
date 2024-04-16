package hk.edu.hkbu;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        if (args.length >= 1) {
            String e = args[0];
            YahooFinancier yf = new YahooFinancier(e);
            yf.generateFile();
            yf.generateFile2();
            System.out.println("Files generated!");
        } else {
            Arrays.stream(new String[]{
//                    "AMD",
//                    "F",
//                    "GD",
//                    "GE",
//                    "T",
//                    "MCD",
//                    "GD",
//                    "LMT",
//                    "RTX",
                    "JOBY",
            }).forEach(e -> {
                YahooFinancier yf = new YahooFinancier(e);
                yf.generateFile();
                yf.generateFile1();
                yf.generateFile2();
                System.out.println("Files generated!");
            });
        }
    }
}
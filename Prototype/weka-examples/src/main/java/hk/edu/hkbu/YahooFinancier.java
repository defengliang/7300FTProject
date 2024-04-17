package com.company;

import java.io.FileNotFoundException;
import java.net.URL;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.ZoneOffset;


/**
 * Class used for obtaining daily financial data from Yahoo Finance.
 * This class contains a number of methods which are all used together in #downloadFile.
 *
 * To use this class
 * ------------------
 * Perform the following steps:
 * - Create and instance of YFinancier
 *     o   You may pass it two strings:
 *          1.  The stock symbol
 *          2.  File name or path for where the data should be stored
 * - Call the #downloadFile method from your instance
 * - The most recent full day's data on Yahoo Finance for the specified stock
 *   will be downloaded to the specified file location.
 *
 *
 * Note:  If no parameters are explicitly set, the method will download data
 * on the S&P 500 Index Fund (SPY). The data will be stored within the directory
 * from which the program has been executed. The default filename is "finance.csv".
 *
 */
public class YahooFinancier  {
    String stockSymbol;
    String outputFileName;

    String url;

    /**
     * Initializer constructor.
     * @param symbol - String, the stock symbol for which to download data
     * @param outputFile - String, the name of the file to which the data should be saved
     */
    public YFinancier(String symbol, String outputFile) {

        LocalDate today = LocalDate.now();

        // Convert today's date to epoch time
        long epochTime = today.atStartOfDay(ZoneOffset.UTC).toEpochSecond();

        String returnURL = "https://query1.finance.yahoo.com/v7/finance/download/"
                + symbol
                + "?period1=315532800&period2="
                + epochTime
                + "&interval=1d&events=history&includeAdjustedClose=true";
        this.url = returnURL;

        setSymbol(symbol);
        setOutputFileName(outputFile);
    }

    /**
     * Method used for setting the symbol of the stock you are attempting to download.
     * For example the S&P 500 Index fund is "SPY"
     * @param symbol - String, the symbol of the stock
     * @throws java.security.InvalidParameterException
     */
    public void setSymbol(String symbol) {
        if (symbol == null) {
            throw new InvalidParameterException("Symbol cannot be null.");
        }
        this.stockSymbol = symbol;
    }

    /**
     * Method used for specifying the file in which the day's financial information will be downloaded
     * method will accept a String or a full file path
     * @param fileName - String, the name of the file to which you would like to save the day's financial data
     * @throws java.security.InvalidParameterException
     */
    public void setOutputFileName(String fileName) {
        if (fileName == null) {
            throw new InvalidParameterException("Output File Name cannot be null.");
        }
        this.outputFileName = fileName;
    }

    /**
     * Get method for returning the current stock symbol of the instance
     * @return String, the stock symbol
     */
    public String getStockSymbol() {
        return this.stockSymbol;
    }

    /**
     * Get method for returning the current output file name of the instance
     * @return String, the output file name or path
     */
    public String getOutputFileName() {
        return this.outputFileName;
    }

    /**
     * Method used for getting the current day's date. This is a utility method used for algorithmically
     * constructing a URL, and getting the current day's financial data.
     * @return - a String containing the current day of month, as a number
     */
    public String getDay() {
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        String dayOfMonthStr = String.valueOf(dayOfMonth);

        return dayOfMonthStr;
    }

    /**
     * Method used for getting the current month. This is a utility method used for algorithmically
     * constructing a URL, and getting the current month's financial data.
     * @return - a String containing the current month of year, as a number
     */
    public String getMonth() {
        Calendar cal = Calendar.getInstance();
        int monthOfYear = cal.get(Calendar.MONTH);

        String monthOfYearStr = String.valueOf(monthOfYear);

        if (Integer.parseInt(monthOfYearStr) < 10) {
            monthOfYearStr = "0" + monthOfYearStr;
        }
        return monthOfYearStr;
    }

    /**
     * Method used for getting the current year. This is a utility method used for algorithmically
     * constructing a URL, and getting the current day's financial data.
     * @return - a String containing the current year as a number
     */
    public String getYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        String yearStr = String.valueOf(year);

        return yearStr;
    }

    /**
     * Method used for downloading a file from the internet and saving to the user's computer.
     * @param filename - Name for the file to be created, containing the information downloaded from the web
     * @param urlString - URL from which to download data
     * @throws IOException - When the file cannot be created or written to
     */
    public void saveUrl(final String filename, final String urlString)
            throws IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
            System.out.println(filename);
        }
    }

    /**
     * Method used within @downloadFile to algorithmically create a valid URL string, by subtracting
     * a single day from the calendar date until a valid file is found.
     * @param day - Day of the week, as a number in a String
     * @param month - Month of the year, as a number in a String
     * @param year - Year, as a number in a String
     * @return - an ArrayList<String> object with data for use by the @downloadFile class
     */
    public ArrayList<String> subtractDay(String day, String month, String year) {
        int dayAsInt = Integer.parseInt(day);
        int monthAsInt = Integer.parseInt(month);
        int yearAsInt = Integer.parseInt(year);


        // Drop day/month/year
        dayAsInt = dayAsInt - 1;
        if (dayAsInt == 0) {
            dayAsInt = 31;
            monthAsInt = monthAsInt - 1;

            if (monthAsInt == 0) {
                monthAsInt = 12;
                yearAsInt = yearAsInt - 1;
            }
        }
        // Transform to String
        String returnDay = Integer.toString(dayAsInt);
        String returnMonth = Integer.toString(monthAsInt);
        String returnYear = Integer.toString(yearAsInt);

        // Create array list for return value
        ArrayList<String> returnList = new ArrayList<String>();
        returnList.add(returnDay);
        returnList.add(returnMonth);
        returnList.add(returnYear);

        return returnList;
    }

    /**
     * Method used for algorithmically ensuring that a valid file can be downloaded by @saveURL.
     * The method uses @getDay, @getMonth, @getYear, @subtractDay and @createURLString to ensure that @savURL
     * continues attempting to download files until a valid one is found.
     */
    public void downloadFile() {

        YFinancier yFinancier = new YFinancier(this.stockSymbol, this.outputFileName);

        System.out.println("DAY OF MONTH IS: " + yFinancier.getDay());
        System.out.println("MONTH OF YEAR IS: " + yFinancier.getMonth());
        System.out.println("YEAR IS: " + yFinancier.getYear());

        // Get day, month, year, as Strings
        String day = yFinancier.getDay();
        String month = yFinancier.getMonth();
        String year = yFinancier.getYear();

        // Increment day +1, will subtract below to create
        // a loop that iterates through days until a valid file is found
        int dayInt = Integer.parseInt(day) + 1;
        day = String.valueOf(dayInt);

        ArrayList<String> dates = new ArrayList<String>();

        boolean fileUnavailable = true;
        while (fileUnavailable) {

            // Try tomorrow's day, then today, and so on until a valid file is found
            dates = subtractDay(day, month, year);
            day = dates.get(0);
            month = dates.get(1);
            year = dates.get(2);
            String financeURL = this.url;

            try {
                // Attempt to download file
                yFinancier.saveUrl(outputFileName, financeURL);
                fileUnavailable = false;
            } catch (FileNotFoundException fnfe) {
                System.out.println("File was not found, trying previous day.");
                fileUnavailable = true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                fileUnavailable = true;
            }
        }

    }
    
}

package hk.edu.hkbu;

import java.io.*;
import java.net.URL;
import java.security.InvalidParameterException;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.LocalDate;
import java.time.ZoneOffset;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class YahooFinancier  {
    String stockSymbol;
    String outputFileName;
    String url;


    public YahooFinancier(String symbol) {

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
        setOutputFileName(symbol + "_stock_info.csv");
    }


    public void setSymbol(String symbol) {
        if (symbol == null) {
            throw new InvalidParameterException("Symbol cannot be null.");
        }
        this.stockSymbol = symbol;
    }


    public void setOutputFileName(String fileName) {
        if (fileName == null) {
            throw new InvalidParameterException("Output File Name cannot be null.");
        }
        this.outputFileName = fileName;
    }


    public String getStockSymbol() {
        return this.stockSymbol;
    }

    public String getOutputFileName() {
        return this.outputFileName;
    }

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

    private long daysSince1970(Date date) {

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Calculate the number of days between Jan 1, 1970 and the specified date
        long daysSince1970 = ChronoUnit.DAYS.between(LocalDate.of(1970, 1, 1), localDate);
        return daysSince1970;
    }

    public List<Double> calculateSQRTMA(List<Double> pastData) {
        if (pastData.size() != 30) throw new InvalidParameterException("No enough data to calculate.");

        List<Double> retList = new ArrayList<>(6);

        for (int i = 0; i < 6; i++) {
            retList.add(0.0);
        }

        for (int i = 0; i < pastData.size(); i++) {
            double number = pastData.get(i);

            double sqr = number * number;

            if (i < 5)
                retList.set(0, retList.get(0) + sqr);

            if (i < 10)
                retList.set(1, retList.get(1) + sqr);

            if (i < 15)
                retList.set(2, retList.get(2) + sqr);

            if (i < 20)
                retList.set(3, retList.get(3) + sqr);

            if (i < 25)
                retList.set(4, retList.get(4) + sqr);

            retList.set(5, retList.get(5) + sqr);
        }

        // Calculate the MA5 to MA30
        for (int i = 0; i < 6; i++) {
            retList.set(i, Math.sqrt(retList.get(i)) / ((i + 1) * 5));
        }

        return retList;
    }

    public List<Double> calculateMA(List<Double> pastData) {

        if (pastData.size() != 30) throw new InvalidParameterException("No enough data to calculate.");

        List<Double> retList = new ArrayList<>(6);

        for (int i = 0; i < 6; i++) {
            retList.add(0.0);
        }

        for (int i = 0; i < pastData.size(); i++) {
            double number = pastData.get(i);

            if (i < 5)
                retList.set(0, retList.get(0) + number);

            if (i < 10)
                retList.set(1, retList.get(1) + number);

            if (i < 15)
                retList.set(2, retList.get(2) + number);

            if (i < 20)
                retList.set(3, retList.get(3) + number);

            if (i < 25)
                retList.set(4, retList.get(4) + number);

            retList.set(5, retList.get(5) + number);
        }

        // Calculate the MA5 to MA30
        for (int i = 0; i < 6; i++) {
            retList.set(i, retList.get(i) / (i + 1) * 5);
        }

        return retList;
    }


    public void generateFile2() {

        int lineNum = 0;
        String line;

        List<String[]> outputList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.outputFileName))) {

            String[] data_2 = null;
            String[] data_1 = null;
            String[] lineArray;

            while ((line = br.readLine()) != null) {

                if (lineNum == 0) {
                    lineNum++;
                    continue;
                }

                lineArray = new String[6];

                boolean continueFlag = false;
                String[] data = line.split(",");
                for (String s: data) {
                    if (null == s || s.equals("")) {
                        continueFlag = true;
                    }
                }

                if (continueFlag || data.length != 7) continue;

                if (lineNum == 1) {
                    lineNum++;
                    data_1 = data;
                    continue;
                }

                if (lineNum == 2) {
                    lineNum++;
                    data_2 = data_1;
                    data_1 = data;
                    continue;
                }

                if ((Double.parseDouble(data_1[1])
                        * Double.parseDouble(data_2[2])
                        * Double.parseDouble(data_2[3])
                        * Double.parseDouble(data_2[4])) == 0) {
                    lineNum++;
                    continue;
                }

                String dateStr = data[0];
                String openStr = getRel(data[1], data_1[1]);
                String highStr = getRel(data_1[2], data_2[2]);
                String lowStr = getRel(data_1[3], data_2[3]);
                String closeStr = getRel(data_1[4], data_2[4]);

                lineArray[0] = daysSince1970(stringToDate(dateStr)) + ""; // Date;
                lineArray[1] = openStr;
                lineArray[2] = highStr;
                lineArray[3] = lowStr;
                lineArray[4] = closeStr;

                // Flag
                Double open = Double.parseDouble(data[1]);
                Double close = Double.parseDouble(data[4]);

                if (close > open) {
                    lineArray[5] = "1";
                } else {
                    lineArray[5] = "0"; // FLAG
                }

                outputList.add(lineArray);
                data_2 = data_1;
                data_1 = data;
                lineNum++;
            }

            FileWriter writer = getFileWriter2(outputList);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private FileWriter getFileWriter2(List<String[]> outputList) throws IOException {
        File file = new File(this.stockSymbol + "_rel.arff");
        FileWriter writer = new FileWriter(file);

        String header =
                "@relation " + this.stockSymbol + "\n" + """
@attribute 'Date' numeric
@attribute 'Open' numeric
@attribute 'High' numeric
@attribute 'Low' numeric
@attribute 'Close' numeric
@attribute class-att {0, 1}

@data\n""";

        writer.write(header);
        int k = 0;
        for (String[] array: outputList) {

            boolean first = true;
            int i = 0;
            for (String s: array) {

                if (!first) {
                    writer.write(",");
                }

                writer.write(s);
                first = false;
                i++;
            }

            writer.write("\n");

            k++;
        }
        return writer;
    }

    private String getRel(String current, String previous) {

        if (previous == null || current == null) return "0";

        return (double) (Double.parseDouble(current) - Double.parseDouble(previous)) / Double.parseDouble(previous)
                + "";

    }


    public void generateFile() {

        downloadArffFile();

        int lineNum = 0;
        String line;

        List<String[]> outputList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.outputFileName))) {

            String[] lineArray;
            String previousHigh = null;
            String previousLow = null;
            String previousClose = null;

            Deque<Double> openDeque = new LinkedList<>();

            while ((line = br.readLine()) != null) {

                if (lineNum == 0) {
                    lineNum++;
                    continue;
                }

                boolean continueFlag = false;
                String[] data = line.split(",");
                for (String s: data) {
                    if (null == s || s.equals("")) {
                        continueFlag = true;
                    }
                }

                if (continueFlag || data.length != 7) continue;

                String dateStr = data[0];
                String openStr = data[1];
                String highStr = data[2];
                String lowStr = data[3];
                String closeStr = data[4];

                if ((Double.parseDouble(openStr)
                        * Double.parseDouble(highStr)
                        * Double.parseDouble(lowStr)
                        * Double.parseDouble(closeStr)) == 0) {
                    continue;
                }

                openDeque.addFirst(Double.parseDouble(data[1]));

                // Keep
                if (openDeque.size() == 30) {

                    lineArray = new String[12];

                    lineArray[0] = daysSince1970(stringToDate(dateStr)) + ""; // Data
                    lineArray[1] = toLog(openStr); // Open

                    // set index 2 to 7
                    List<Double> maList = calculateMA((List) openDeque);
                    for (int j = 0; j < maList.size(); j++) {
                        lineArray[2 + j] = toLog(maList.get(j)) + "";
                    }

                    lineArray[8] = toLog(previousHigh); // Previous High
                    lineArray[9] = toLog(previousLow);  // Previous Low
                    lineArray[10] = toLog(previousClose); // Previous Close

                    // Flag
                    Double open = Double.parseDouble(openStr);
                    Double close = Double.parseDouble(closeStr);

                    if (close > open) {
                        lineArray[11] = "1";
                    } else {
                        lineArray[11] = "0"; // FLAG
                    }

                    outputList.add(lineArray);
                    openDeque.removeLast();
                }

                previousHigh = highStr;
                previousLow = lowStr;
                previousClose = closeStr;

                lineNum++;
            }

            FileWriter writer = getFileWriter(outputList);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String toLog(Double dbl) {
        return Math.log(dbl) + "";
    }

    private String toLog(String str) {
        return Math.log(Double.parseDouble(str)) + "";
    }

    private FileWriter getFileWriter(List<String[]> outputList) throws IOException {
        File file = new File(this.stockSymbol + "_converted.arff");
        FileWriter writer = new FileWriter(file);

        String header =
                "@relation " + this.stockSymbol + "\n" + """
@attribute 'Date' numeric
@attribute 'Open' numeric
@attribute 'OpenMA05' numeric
@attribute 'OpenMA10' numeric
@attribute 'OpenMA15' numeric
@attribute 'OpenMA20' numeric
@attribute 'OpenMA25' numeric
@attribute 'OpenMA30' numeric
@attribute 'prevHigh' numeric
@attribute 'prevLow' numeric
@attribute 'prevClose' numeric
@attribute class-att {0, 1}

@data\n""";

        writer.write(header);
        int k = 0;
        for (String[] array: outputList) {

            boolean first = true;
            int i = 0;
            for (String s: array) {

                if (!first) {
                    writer.write(",");
                }

                writer.write(s);
                first = false;
                i++;
            }

            writer.write("\n");

            k++;
        }
        return writer;
    }

    private Date stringToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(str);
        } catch (ParseException e) {
           return null;
        }

    }

    public String downloadArffFile() {

        downloadFile();

        File file = new File(this.outputFileName);

        String header =
                "@relation " + this.stockSymbol + "\n" + """
@attribute 'Date' DATE "yyyy-MM-dd"
@attribute 'OPEN' numeric
@attribute 'HIGH' numeric
@attribute 'LOW' numeric
@attribute 'CLOSE' numeric
@attribute 'ADJCLOSE' numeric

@data\n""";
        File outputFile = new File(this.stockSymbol + "_raw.arff");

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder content = new StringBuilder();
            content.append(header);
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {

                // The first line doesn't need
                if (lineCount != 0)
                    content.append(line).append("\n");

                lineCount++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(content.toString());
            writer.close();

            return content.toString();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertStr(String str) {

        String[] lineArray = str.split(",");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(lineArray[0]);
        for (int i = 1; i < lineArray.length; i++) {
            stringBuffer.append(",");
            stringBuffer.append(logStr(lineArray[i]));
        }
        return stringBuffer.toString();
    }

    private String logStr(String str) {
        return "" + Math.log(Double.parseDouble(str));
    }


    public void downloadFile() {

        boolean fileUnavailable = true;
        while (fileUnavailable) {
            String financeURL = this.url;
            try {
                // Attempt to download file
                saveUrl(outputFileName, financeURL);
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

package com.techelevator.view;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.NumberFormat;

public class Logger {

    static String logFilePath = "capstone/log.txt";
    static String logSalesReport = "salesReport.txt";
    private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance();

    private static String getCurrentTime() {
        SimpleDateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        Date now = new Date();
        return currentDate.format(now);
    }

    public static void log(String event, double startBalance, double endBalance) {
        File filePathToLog = new File(logFilePath);
        try(PrintWriter print = new PrintWriter(new FileWriter(filePathToLog, true))) {
            print.println(getCurrentTime() + " " + event + " " + CURRENCY.format(startBalance)
            + " " + CURRENCY.format(endBalance));
            print.flush();
        } catch (IOException e) {
            System.out.println("log failed");
        }
    }

    public static void secretLog(String log) {
        File filePath = new File(logSalesReport);
        try(PrintWriter print = new PrintWriter(new FileWriter(filePath, true))) {
            print.println(log);
        } catch (IOException e) {
            System.out.println("secret failed");
        }
    }

    public static void timeAndDate() {
        File filePath = new File(logSalesReport);
        try(PrintWriter print = new PrintWriter(new FileWriter(filePath, true))) {
            print.println(getCurrentTime());
        } catch (IOException e) {
            System.out.println("secret failed");
        }
    }
}

















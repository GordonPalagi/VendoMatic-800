package com.techelevator.view;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.NumberFormat;

public class Logger {

    static String LOG_FILE_PATH = "/Users/Gordon/meritamerica/repos/Capstone-Project/module-1-capstone/capstone/src/main/java/com/techelevator/view/log.txt";
    private static NumberFormat currency = NumberFormat.getCurrencyInstance();

    private static String getCurrentTime() {
        SimpleDateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        Date now = new Date();
        return currentDate.format(now);
    }

    public static void log(String event, double startBalance, double endBalance) throws IOException {
        File filePathToLog = new File(LOG_FILE_PATH);
        try(PrintWriter print = new PrintWriter(new FileWriter(filePathToLog, true))) {
            print.println(getCurrentTime() + " " + event + " " + currency.format(startBalance)
            + " " + currency.format(endBalance));
            print.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

}

/*
we need a method that logs the current time and date // done

need a method specific for the event
    make a string in the actual class which will append everytime they hit feedMoney

method that we can call to write to the file


 */














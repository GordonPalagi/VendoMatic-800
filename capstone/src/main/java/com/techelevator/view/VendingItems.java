package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class VendingItems {

    private int itemInventory = 5;
    private String vendingID = " "; // supposed to be an empty string
    private String name;
    private double price;
    private String type;
    List<VendingItems> limiter = new ArrayList<>();

    // constructor
    public VendingItems(String vendingID, String name, double price, String type) {
        this.vendingID = vendingID;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public VendingItems() {}



    public void assignItems() {
            // we use a list to generalize the items within the constructor
            // also using a list will add flexibility, if items are rearranged later on
            // we needed a list to hold our objects

            // we use a try catch block because use a constructor within the while loop, which could cause an exception
        try(Scanner fileScanner = new Scanner(new File("/Users/Gordon/meritamerica/module-1-capstone/capstone/vendingmachine.csv"))) {

            // while the file has the next line...
            while(fileScanner.hasNextLine()) {

                // (line = assignment) assigns the current line of the while loop and then updates for each loop
                String line = fileScanner.nextLine();

                // pass the line to the scanner for each loop in order to parse through the constructor
                // another scanner is necessary because the original scanner isn't done scanning
                // we then scan the individual line in order to obtain the specific properties of our VendingItem objects
                Scanner parseLine = new Scanner(line);

                // the delimiter identifies the pipe character, so we can split the line
                parseLine.useDelimiter(Pattern.quote("|"));

                // we use next() because the line will be split by the delimiter, via pipe character
                // so we need to call the next item, after the delimiter breaks/pauses the line.
                // We also add the constructor to our list
                limiter.add(new VendingItems(parseLine.next(), parseLine.next(), parseLine.nextDouble(), parseLine.next()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // Scans file and adds item remaining variable
    public void displayInventory() {
        for (VendingItems item : limiter) {
            System.out.println(item.getVendingID() + " " + item.getName() + " " + item.getPrice() + " " + item.getType() + " " + item.getItemInventory());
        }
    }



    // getters and setters
    public int getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(int itemInventory) {
        this.itemInventory = itemInventory;
    }

    public String getVendingID() {
        return vendingID;
    }

    public void setVendingID(String vendingID) {
        this.vendingID = vendingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}

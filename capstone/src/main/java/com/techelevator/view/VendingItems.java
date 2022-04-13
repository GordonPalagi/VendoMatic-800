package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingItems {

    private int itemInventory = 5;
    private String vendingID = " "; // supposed to be an empty string
    private String name;
    private int price;
    private String type;

    // constructor
    public VendingItems(String vendingID, String name, int price, String type) {
        this.vendingID = vendingID;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public VendingItems() {

    }


    // Scans file and adds item remaining variable
    public void displayInventory() {
        try(Scanner scanner = new Scanner(new File("/Users/Gordon/meritamerica/module-1-capstone/capstone/vendingmachine.csv"))) {
            while(scanner.hasNextLine()) {
                StringBuilder sb;
                sb = new StringBuilder(scanner.nextLine());
                sb.append(" items remaining: ").append(getItemInventory());
                System.out.println(sb);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // This method scans the csv file,
//    public void buildingString() {
//
//    }



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

}

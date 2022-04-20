package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class VendingItems {

    private final int REFILL = 5;
    private int itemInventory;
    private String vendingID = " "; // supposed to be an empty string
    private String name;
    private double price;
    private String type;
    private String sound;
    private List<VendingItems> listOfItems = new ArrayList<>();
    private int secretCount = 0;

    // constructor
    public VendingItems(String vendingID, String name, double price, String type) {
        this.vendingID = vendingID;
        this.name = name;
        this.price = price;
        this.type = type;
        this.itemInventory = REFILL;
    }

    public VendingItems() {}

    // assignItems segments the lines within the CSV file. This allows us to use .next() to allow the
    // items to later be changed.
    public void assignItems() {

        try(Scanner fileScanner = new Scanner(new File("/Users/Gordon/meritamerica/repos/module-1-capstone/capstone/vendingmachine.csv"))) {

            while(fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                Scanner parseLine = new Scanner(line);

                // the delimiter identifies the pipe character, so we can split the line
                parseLine.useDelimiter(Pattern.quote("|"));

                listOfItems.add(new VendingItems(parseLine.next(), parseLine.next(), parseLine.nextDouble(), parseLine.next()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Dispensing won't go into negative
    public void dispense() {
        if (getItemInventory() != 0) {
            setItemInventory(getItemInventory() - 1);
            secretCount ++;
        }
    }

    // Scans file and adds item remaining variable
    public void displayInventory() {
        for (VendingItems item : listOfItems) {
            System.out.println(item.getVendingID() + " " + item.getName() + " " + item.getPrice() + " " + item.getType() + " " + item.getItemInventory());
        }
    }

    public void displayItem() {
        System.out.println(getName() + " " + getPrice() + " " + getType() + " " + getItemInventory());
        switch(getType()) {
            case "Chip":
                System.out.println("Crunch Crunch, Yum!\n");
                break;
            case "Candy":
                System.out.println("Munch Munch, Yum!\n");
                break;
            case "Drink":
                System.out.println("Glug Glug, Yum!\n");
                break;
            case "Gum":
                System.out.println("Chew Chew, Yum!\n");
                break;
            default:
                System.out.println("");
                break;
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

    public List<VendingItems> getListOfItems() {return listOfItems;}

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
    public int getSecretCount(){
        return secretCount;
    }


}

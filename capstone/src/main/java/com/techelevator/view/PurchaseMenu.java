package com.techelevator.view;

import Enum.Coins;
import Exceptions.BaseException;
import com.techelevator.VendingItems;
import java.io.IOException;
import java.util.*;

public class PurchaseMenu {
    private double moneyProvided = 0;
    private VendingItems tempObj;
    private String event = "";
    private double startBalance;
    private double endBalance;


    // for displaying the menu once the user selects the purchase option within the first display menu
    public void displayPurchaseMenu(VendingItems vendingItems) {

        while (true) {  // displays the new menu options in a while loop, in case the user wants to add more money
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("\nCurrent Money Provided: " + moneyProvided);

            // scans for the users choice...
            Scanner userInput = new Scanner(System.in);
            String userSelection = userInput.nextLine();

            // if the selection equals 1...
            if (userSelection.equals("1")) {

                try {
                    feedMoney(userInput);
                } catch (BaseException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (userSelection.equals("2")) { // if user selects product...
                startBalance = moneyProvided;
                vendingItems.displayInventory();    // we display the inventory.

                customerSelection(vendingItems);

                endBalance = moneyProvided;
                Logger.log(event, startBalance, endBalance);

            }
            else if (userSelection.equals("3")) {

                startBalance = moneyProvided;
                event = "GIVE CHANGE: ";

                moneyProvided = dispenseChange(moneyProvided);

                endBalance = moneyProvided;
                Logger.log(event, startBalance, endBalance);

                break;
            }
        }
    }

    private void feedMoney(Scanner userInput) throws BaseException {
        boolean addMore = true;
        event = "FEED_MONEY: ";

        // Do while loop is here because we want the list to print out first and the user can put in money multiple times
        do {
            startBalance = moneyProvided;
            System.out.println("Acceptable bills: $1, $2, $5, $10");
            System.out.println("Please insert money: ");
            // variable for the total amount of money provided
            double temp = userInput.nextDouble();
            if (temp == 0 || temp == 1 || temp == 2 || temp == 5 || temp == 10) {
                moneyProvided += temp;

                endBalance = moneyProvided;
                Logger.log(event, startBalance, endBalance);

                // the same scanner asks for Y/N... if no, it goes back to the purchase menu while loop.
                System.out.println("Would you like to add more money? (Y/N)");
                if (userInput.next().equalsIgnoreCase("n")) {
                    addMore = false;    // if user selects 'N'... this will break the loop.
                }
            }
            else {
                throw new BaseException("The bill you enter is invalid\n");
            }
        }
        while (addMore);
    }

    public void customerSelection(VendingItems vendingItems) {
        Scanner code = new Scanner(System.in);  // new scanner for the code of the selected snack
        System.out.println("Enter code for selected snack");
        String productChoice = code.next(); // this will equal the vendingID... (A1, A2, B3... etc)

            for (VendingItems item : vendingItems.getListOfItems()) {
                // check if the ID is correct.
                if (productChoice.equalsIgnoreCase(item.getVendingID())) {
                    tempObj = item;
                }
            }

        try {
            if (tempObj.getItemInventory() == 0) {
                System.out.println("Sold out!");
            }
            else  if (tempObj.getPrice() > moneyProvided) {
                System.out.println("Insufficient funds");
            }
            else {
                tempObj.dispense();
                tempObj.displayItem();
                moneyProvided -= tempObj.getPrice();
            }
            event = tempObj.getName() + " " + tempObj.getVendingID();
        } catch (NullPointerException e) {
            System.out.println("Unknown selection try again\n");
        }
    }

    public double dispenseChange(double moneyProvided) {
        int divisible;
        moneyProvided *= 100;

        divisible =  (int)moneyProvided / Coins.QUARTER.VALUE;
        moneyProvided = divisible % Coins.QUARTER.VALUE;
        System.out.println("Change dispensed: " + divisible + " " + Coins.QUARTER.NAME);

        divisible =  (int)moneyProvided / Coins.DIME.VALUE;
        moneyProvided = divisible % Coins.DIME.VALUE;
        System.out.println("Change dispensed: " + divisible + " " + Coins.DIME.NAME);

        divisible =  (int)moneyProvided / Coins.DIME.VALUE;
        moneyProvided = divisible % Coins.DIME.VALUE;
        System.out.println("Change dispensed: " + divisible + " " + Coins.NICKEL.NAME);
        System.out.println(moneyProvided);

        return moneyProvided;
    }

    // this method dispenses the left-over money from TotalMoneyProvided (subtracting price of any selected products)
        // start with a string builder to tell the user how much change was dispensed.
        // a string builder outside the for loop is necessary because we want to add values to the string dynamically.
//        public double changeDispenser(double coin) {
//            totalMoneyProvided *= 100;
//            double divisible = totalMoneyProvided / coin;
//            divisible = Math.floor(divisible);
//            totalMoneyProvided -= divisible * coin;
//            System.out.println("");
//
//            return totalMoneyProvided;
//        }
}


/*
each time a button is pressed we need to log the time and date

per button we need to be specific
    FEED_ME_BUTTON_PRESSED - each time money is given it needs to be logged
    DISPENSE_BUTTON_PRESSED - each time an item is dispensed, it needs to log the name of the item,
                              the ID of the item, the current balance before and after
    CHANGE_GIVEN_BUTTON_PRESSED - Needs to log the current balance before and after

 */












package com.techelevator.view;

import Enum.Coins;
import com.techelevator.VendingItems;
import java.io.IOException;
import java.util.*;
import java.text.NumberFormat;

public class PurchaseMenu {
    private double moneyProvided = 0;
    private VendingItems tempObj;
    private String event = "";
    private double startBalance;
    private double endBalance;
    private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance();


    // for displaying the menu once the user selects the purchase option within the first display menu
    public void displayPurchaseMenu(VendingItems vendingItems) {

        while (true) {  // displays the new menu options in a while loop, in case the user wants to add more money
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("\nCurrent Money Provided: " + CURRENCY.format(moneyProvided));

            Scanner userInput = new Scanner(System.in);
            String userSelection = userInput.nextLine();

            if (userSelection.equals("1")) {

                feedMoney(userInput);
            } else if (userSelection.equals("2")) { // if user selects product...
                startBalance = moneyProvided;
                vendingItems.displayInventory();

                customerSelection(vendingItems);

                endBalance = moneyProvided;
                Logger.log(event, startBalance, endBalance);

            } else if (userSelection.equals("3")) {

                startBalance = moneyProvided;
                event = "GIVE CHANGE: ";

                moneyProvided = dispenseChange(moneyProvided);

                endBalance = moneyProvided;
                Logger.log(event, startBalance, endBalance);

                break;
            }
        }
    }

    private void feedMoney(Scanner userInput) {
        boolean addMore = true;
        event = "FEED_MONEY: ";

        do {
            startBalance = moneyProvided;
            System.out.println("Acceptable bills: $1, $2, $5, $10");
            System.out.println("Please insert money: ");
            try {
                double temp = userInput.nextDouble();
                if (temp == 0 || temp == 1 || temp == 2 || temp == 5 || temp == 10) {
                    moneyProvided += temp;

                    endBalance = moneyProvided;

                    // the same scanner asks for Y/N... if no, it goes back to the purchase menu while loop.
                    System.out.println("Would you like to add more money? (Y/N)");
                    if (userInput.next().equalsIgnoreCase("n")) {
                        addMore = false;
                    }
                }
            } catch (InputMismatchException e) {
                addMore = false;
                System.out.println("The bill you enter is invalid\n");
            }
            Logger.log(event, startBalance, endBalance);
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
            } else if (tempObj.getPrice() > moneyProvided) {
                System.out.println("Insufficient funds");
            } else {
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
        double divisible;
        moneyProvided *= 100;

        divisible = moneyProvided / Coins.QUARTER.VALUE;
        divisible = Math.floor(divisible);
        moneyProvided -= divisible * Coins.QUARTER.VALUE;
        System.out.println("Change dispensed: " + divisible + " " + Coins.QUARTER.NAME);

        divisible = moneyProvided / Coins.DIME.VALUE;
        divisible = Math.floor(divisible);
        moneyProvided -= divisible * Coins.DIME.VALUE;
        System.out.println("Change dispensed: " + divisible + " " + Coins.DIME.NAME);

        divisible = moneyProvided / Coins.NICKEL.VALUE;
        divisible = Math.floor(divisible);
        moneyProvided -= divisible * Coins.NICKEL.VALUE;
        System.out.println("Change dispensed: " + divisible + " " + Coins.NICKEL.NAME);
        System.out.println(CURRENCY.format(moneyProvided));

        return moneyProvided;
    }
}












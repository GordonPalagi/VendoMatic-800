package com.techelevator.view;

import Exceptions.BaseException;
import com.techelevator.VendingItems;

import java.util.Scanner;

public class PurchaseMenu {
    double totalMoneyProvided = 0;
    private VendingItems tempObj;

    // for displaying the menu once the user selects the purchase option within the first display menu
    public void displayPurchaseMenu (VendingItems vendingItems) {

        while (true) {  // displays the new menu options in a while loop, in case the user wants to add more money
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("\nCurrent Money Provided: " + totalMoneyProvided);

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
                vendingItems.displayInventory();    // we display the inventory.

                Scanner code = new Scanner(System.in);  // new scanner for the code of the selected snack
                System.out.println("Enter code for selected snack");
                String productChoice = code.next(); // this will equal the vendingID... (A1, A2, B3... etc)

                for (VendingItems item : vendingItems.getListOfItems()) {

                    // check if the ID is correct.
                    if (productChoice.equalsIgnoreCase(item.getVendingID())) {
                        tempObj = item;
                    }
                    if (!productChoice.equals(tempObj.getVendingID())) {
                        System.out.println("incorrect selection/item not found");
                    }
                    else if (tempObj.getItemInventory() == 0) {
                        System.out.println("Sold out!");
                    }
                    else {
                        tempObj.dispense();
                        tempObj.displayItem();
                        totalMoneyProvided -= tempObj.getPrice();
                    }
                }
                vendingItems.userSelection(productChoice);
            }
            else if (userSelection.equals("3")) {

                totalMoneyProvided = changeDispenser(totalMoneyProvided);

                System.out.println(totalMoneyProvided);
                break;

            }
        }
    }

    private void feedMoney(Scanner userInput) throws BaseException {
        boolean addMore = true;

        // Do while loop is here because we want the list to print out first and the user can put in money multiple times
        do {
            System.out.println("Acceptable bills: $1, $2, $5, $10");
            System.out.println("Please insert money: ");

            // variable for the total amount of money provided
            double temp = userInput.nextDouble();
            if (temp == 1 || temp == 2 || temp == 5 || temp == 10) {
                totalMoneyProvided += userInput.nextDouble();
            } else {
                throw new BaseException("The bill you enter is invalid\n");
            }

            // the same scanner asks for Y/N... if no, it goes back to the purchase menu while loop.
            System.out.println("Would you like to add more money? (Y/N)");
            if (userInput.next().equalsIgnoreCase("n")) {
                addMore = false;    // if user selects 'N'... this will break the loop.
            }
        }
        while (addMore);
    }


    public double changeDispenser(double excessMoney) {

        double quarter = 25;
        double dime = 10;
        double nickel =  5;

        excessMoney *= 100;
        double divisible = excessMoney / quarter;
        divisible = Math.floor(divisible);
        excessMoney -= divisible * quarter;

        System.out.println("Change dispensed: Quarters - " + quarter + " Dimes - " + dime + " Nickels " + nickel);

        return excessMoney;
    }
}

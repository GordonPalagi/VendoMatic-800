package com.techelevator.view;

import com.techelevator.VendingItems;

import java.util.Scanner;

public class PurchaseMenu {
    double currentMoneyProvided = 0;



    public void displayPurchaseMenu (VendingItems vendingItems) {

        while (true) {
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("\nCurrent Money Provided: " + currentMoneyProvided);

            Scanner userInput = new Scanner(System.in);
            String userSelection = userInput.nextLine();

            if (userSelection.equals("1")) {
                boolean addMore = true;
                do {
                    System.out.println("Please insert money");
                    System.out.println("Acceptable bills: $1, $2, $5, $10");

                    currentMoneyProvided += userInput.nextDouble();

                    System.out.println("Would you like to add more money? (Y/N)");
                    if (userInput.next().equalsIgnoreCase("n")) {
                        addMore = false;
                    }
                } while (addMore);
            } else if (userSelection.equals("2")) {
                vendingItems.displayInventory();

                Scanner code = new Scanner(System.in);
                System.out.println("Enter code for selected snack");
                String productChoice = code.next();

                vendingItems.userSelection(productChoice);

            } else if (userSelection.equals("3")) {

            }
        }
    }
}

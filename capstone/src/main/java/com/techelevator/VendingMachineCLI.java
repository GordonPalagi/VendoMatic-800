package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.MoneyInputException;
import com.techelevator.view.VendingItems;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String FEED_MONEY = "Feed Money";
	private static final String SELECT_PRODUCT = "Select Product";
	private static final String FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};
	private boolean vendingOn = true;
	VendingItems vendingItems = new VendingItems();
	private static int currentMoneyProvided = 0;
	private Menu menu;

	//constructor
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	// starts running the program.
	public void run() {
		// while vendingOn...
		while (vendingOn) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			VendingItems client = new VendingItems();
			client.assignItems();

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				client.displayInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if (purchaseChoice.equals(FEED_MONEY)){
					try(Scanner moneyInput = new Scanner(System.in)) {
						System.out.println("Please insert money");
						System.out.println("Acceptable bills: $1, $2, $5, $10");
						currentMoneyProvided = moneyInput.nextInt();
					} catch(MoneyInputException e) {
//						throw new MoneyInputException("Please enter an acceptable bill: $1, $2, $5, $10");
					} catch(InputMismatchException e) {
						System.out.println("Please enter an acceptable bill: $1, $2, $5, $10");
					}
				}
				else if (purchaseChoice.equals(SELECT_PRODUCT)) {
					client.displayInventory();
					Scanner code = new Scanner(System.in);
					System.out.println("Enter code for selected snack");
					String productChoice = code.next();
				}
				else if (purchaseChoice.equals(FINISH_TRANSACTION)) {

				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Hi your refrigerator is... not running. Congrats! GoodBye!");
				vendingOn = false;
			}
		}
	}

	private void displayCurrentMoney() {
		System.out.println("Current Money Provided: " + currentMoneyProvided);
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.VendingItems;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private boolean vendingOn = true;
	VendingItems vendingItems = new VendingItems();


	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (vendingOn) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				vendingItems.displayInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase

				// possible do...while loop. it would be checking if the user wants to continue
				// might want to be false AFTER purchase?
				// this is where we create the second menu
				//  >```
				//    >(1) Feed Money  // do we need a money class? money class extending from menu
				//    >(2) Select Product
				//    >(3) Finish Transaction // maybe vendingOn true again after finishing transaction
				//    >
				//    > Current Money Provided: $2.00
				//    >```
				//

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Hi your refrigerator is... not running. Congrats! GoodBye!");
				vendingOn = false;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

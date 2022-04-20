package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.PurchaseMenu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private Menu menu;
	private VendingItems items = new VendingItems();
	private PurchaseMenu purchase = new PurchaseMenu();

	//constructor
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		items.assignItems();
	}

	// starts running the program.
	public void run() {

		// while vendingOn...
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				items.displayInventory();
			}
			else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
					purchase.displayPurchaseMenu(items);
			}
			else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you for choosing Umbrella Corp");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

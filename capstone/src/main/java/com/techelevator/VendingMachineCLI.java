package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.VendingItems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private boolean vendingOn = true;
	VendingItems vendingItems = new VendingItems();


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
			System.out.println(client.getItemInventory());

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

//	public void assignItems() {
//		try(Scanner scanner = new Scanner(new File("/Users/Gordon/meritamerica/module-1-capstone/capstone/vendingmachine.csv"))) {
//			while(scanner.hasNextLine()) {
//				String line = scanner.nextLine();
//				Scanner parse = new Scanner(line);
//				parse.useDelimiter("//|");
//				VendingItems client = new VendingItems(parse.next(), parse.next(), parse.nextInt(), parse.next());
//				System.out.println(client);
////				sb = new StringBuilder(scanner.nextLine());
////				sb.append(" items remaining: ").append(vendingItems.getItemInventory());
//
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

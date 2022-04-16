package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.PurchaseMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private Menu menu;
	private VendingItems client = new VendingItems();
	private PurchaseMenu purchase = new PurchaseMenu();

	//constructor
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		client.assignItems();
	}

	// starts running the program.
	public void run() {
		// while vendingOn...
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				client.displayInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				purchase.displayPurchaseMenu(client);
//				if (purchaseChoice.equals(FEED_MONEY)){
//					try(Scanner moneyInput = new Scanner(System.in)) {
//						System.out.println("Please insert money");
//						System.out.println("Acceptable bills: $1, $2, $5, $10");
//						currentMoneyProvided = moneyInput.nextInt();
////					} catch(MoneyInputException e) {
////						throw new MoneyInputException("Please enter an acceptable bill: $1, $2, $5, $10");
//					} catch(InputMismatchException e) {
//						System.out.println("Please enter an acceptable bill: $1, $2, $5, $10");
//					}
//				}
//				else if (purchaseChoice.equals(SELECT_PRODUCT)) {
//					client.displayInventory();
//					Scanner code = new Scanner(System.in);
//					System.out.println("Enter code for selected snack");
//					String productChoice = code.next();
//
//					for(VendingItems item: client.limiter) {
//						if (productChoice.equals(item.getVendingID())) {
//							item.dispense();
//							item.displayItem();
//						}
//					}
//				}
//				else if (purchaseChoice.equals(FINISH_TRANSACTION)) {
//
//				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Hi your refrigerator is... not running. Congrats! GoodBye!");
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

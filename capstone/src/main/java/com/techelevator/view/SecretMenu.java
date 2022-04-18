package com.techelevator.view;

import com.techelevator.VendingItems;

import java.util.ArrayList;
import java.util.List;

public class SecretMenu {
    VendingItems x = new VendingItems();
    private List<VendingItems> secretList = new ArrayList<>();

    public void secretMenuUpdate() {
        x.assignItems();
        secretList = x.getListOfItems();

        for (VendingItems item: secretList) {
            System.out.println(item.getName() + "|");
        }

    }

}

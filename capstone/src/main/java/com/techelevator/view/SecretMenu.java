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

        Logger.timeAndDate();

        for (VendingItems item: secretList) {
            Logger.secretLog(item.getName() + "|" + item.getSecretCount());
        }
    }
}






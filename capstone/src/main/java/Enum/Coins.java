package Enum;

public enum Coins {
    QUARTER(25, "Quarter"),
    DIME(10, "Dime"),
    NICKEL(5, "Nickel");

    public final int VALUE;
    public final String NAME;

    Coins (int VALUE, String NAME) {
        this.VALUE = VALUE;
        this.NAME = NAME;
    }


}

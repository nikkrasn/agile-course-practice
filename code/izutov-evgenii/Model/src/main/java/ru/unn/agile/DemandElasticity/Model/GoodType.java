package ru.unn.agile.DemandElasticity.Model;

public enum GoodType {
    Undefined("Type of good is undefined"),
    Luxury("luxury item"),
    FirstNeed("Essential goods"),
    Inferior("Inferior good"),
    Neutral("Neutral benefit");

    private final String name;
    private GoodType(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

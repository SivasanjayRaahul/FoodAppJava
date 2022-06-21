package model;

public class Food {

    public String name;
    public int cost;
    public int foodId;
    public int quantity;

    public Food(int foodId, String name, int quantity, int cost) {
        this.name = name;
        this.cost = cost;
        this.foodId = foodId;
        this.quantity = quantity;
    }

}

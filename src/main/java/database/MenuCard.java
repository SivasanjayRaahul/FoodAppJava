package database;

import model.Food;

import java.util.ArrayList;
import java.util.List;

public class MenuCard {
    private static MenuCard menuCard;
    private final List<Food> items = new ArrayList<>();

    private MenuCard() {
        items.add(new Food(1, "Plain Dosa", 1, 10));
        items.add(new Food(2, "Onion Roast", 1, 70));
        items.add(new Food(3, "Ghee Roast", 1, 60));
        items.add(new Food(4, "Plain Roast", 1, 50));
        items.add(new Food(5, "Onion Uttapam", 1, 70));
        items.add(new Food(6, "Plain Uttapam", 1, 60));
        items.add(new Food(7, "Burger", 1, 90));
        items.add(new Food(8, "Non-veg Burger", 1, 100));
        items.add(new Food(9, "Noodles", 1, 60));
        items.add(new Food(10, "Boiled Egg", 1, 10));

    }

    public static MenuCard getInstance() {
        if (menuCard == null) {
            menuCard = new MenuCard();
        }
        return menuCard;
    }

    public List<Food> viewItems() {
        return items;
    }

    public Food getItem(int foodId) {
        return items.get(foodId - 1);
    }

}

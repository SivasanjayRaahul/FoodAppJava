package model;


import java.util.List;

public interface Menu {
    List<Food> viewItems();

    boolean addFoodItemToCart(int foodId, int quantity);

    List<Food> purchaseHistory();
}

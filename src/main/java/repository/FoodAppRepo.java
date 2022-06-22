package repository;

import model.Food;

import java.util.List;

public interface FoodAppRepo {
    boolean isLoginSuccessful(String mailId, String password);

    void registerUser(String name, String number, String mailId, String password);

    List<Food> viewCartItems(String mailId);

    boolean modifyCart(int foodId, int quantity, String mailId);

    boolean removeCartItem(int foodId, String mailId);

    List<Food> checkoutCart(String mailId);

    void addFoodItemToCart(String mailId, int foodId, int quantity);

    List<Food> purchaseHistory(String mailId);

}

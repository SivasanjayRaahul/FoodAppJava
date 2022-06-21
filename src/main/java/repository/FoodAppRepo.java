package repository;

import model.Food;

import java.sql.ResultSet;
import java.util.List;

public interface FoodAppRepo {
    ResultSet isLoginSuccessful(String mailId, String password);
    void registerUser(String name, String number, String mailId, String password);
    List<Food> viewCart(String mailId);
    boolean modifyCart(int foodId, int quantity,String mailId);
    boolean removeCartItem(int foodId,String mailId);
    ResultSet checkout(String mailId);
}

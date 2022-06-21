package model;

import repository.FoodAppRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartImpl implements Cart {
    private final String mailId;
    private final FoodAppRepo foodAppRepo;

    public CartImpl(String mailId, FoodAppRepo foodAppRepo) {
        this.mailId = mailId;
        this.foodAppRepo = foodAppRepo;
    }

    @Override
    public List<Food> viewCart()  {
        return foodAppRepo.viewCart(mailId);
    }

    @Override
    public boolean modifyCart(int foodId, int quantity) {
        return foodAppRepo.modifyCart(foodId, quantity, mailId);
    }

    @Override
    public boolean removeCartItem(int foodId) {
        return foodAppRepo.removeCartItem(foodId, mailId);
    }

    @Override
    public List<Food> checkout() throws SQLException {
        ResultSet result = foodAppRepo.checkout(mailId);
        List<Food> items = new ArrayList<>();
        if (result.next()) {
            while (result.next()) {
                items.add(new Food(result.getInt(1), result.getString(2), result.getInt(4), result.getInt(5)));
            }
            return items;
        }
        return new ArrayList<>();
    }
}

package model;

import repository.FoodAppRepo;

import java.util.List;

public class CartImpl implements Cart {
    private final String mailId;
    private final FoodAppRepo foodAppRepo;

    public CartImpl(String mailId, FoodAppRepo foodAppRepo) {
        this.mailId = mailId;
        this.foodAppRepo = foodAppRepo;
    }

    @Override
    public List<Food> view()  {
        return foodAppRepo.viewCartItems(mailId);
    }

    @Override
    public boolean modifyFoodQuantity(int foodId, int quantity) {
        return foodAppRepo.modifyCart(foodId, quantity, mailId);
    }

    @Override
    public boolean removeItem(int foodId) {
        return foodAppRepo.removeCartItem(foodId, mailId);
    }

    @Override
    public List<Food> checkout() {
        return foodAppRepo.checkoutCart(mailId);
    }
}

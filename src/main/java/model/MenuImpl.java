package model;

import database.MenuCard;
import repository.FoodAppRepo;

import java.util.List;

public class MenuImpl implements Menu {

    private final String mailId;
    private final FoodAppRepo foodAppRepo;
    private final MenuCard menuCard;

    public MenuImpl(String mailId, FoodAppRepo foodAppRepo) {
        this.mailId = mailId;
        this.foodAppRepo = foodAppRepo;
        this.menuCard = MenuCard.getInstance();
    }

    @Override
    public List<Food> viewItems() {
        return menuCard.viewItems();
    }

    @Override
    public boolean addFoodItemToCart(int foodId, int quantity) {
        if (foodId > 0 && foodId <= 10) {
            foodAppRepo.addFoodItemToCart(mailId, foodId, quantity);
            return true;
        }
        return false;
    }

    @Override
    public List<Food> purchaseHistory() {
        return foodAppRepo.purchaseHistory(mailId);
    }

}

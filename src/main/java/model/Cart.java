package model;

import java.util.List;

public interface Cart {
    List<Food> view();

    boolean modifyFoodQuantity(int foodId, int quantity);

    boolean removeItem(int foodId);

    List<Food> checkout();

}

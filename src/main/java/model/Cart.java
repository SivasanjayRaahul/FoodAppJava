package model;

import java.sql.SQLException;
import java.util.List;

public interface Cart {
    List<Food> viewCart() throws SQLException;

    boolean modifyCart(int foodId, int quantity);

    boolean removeCartItem(int foodId);

    List<Food> checkout() throws SQLException;

}

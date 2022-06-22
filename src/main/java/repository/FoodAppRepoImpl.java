package repository;

import database.Database;
import database.MenuCard;
import exceptions.CartItemsException;
import model.Food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FoodAppRepoImpl implements FoodAppRepo {
    Logger logger = Logger.getLogger("FoodAppRepoImpl");

    @Override
    public boolean isLoginSuccessful(String mail_Id, String password) {
        ResultSet result = null;
        boolean isLoginSuccessful = false;
        try {
            PreparedStatement selectCredentials = Database.getConnection().prepareStatement("select * from register where mail_id=? and password=?");
            selectCredentials.setString(1, mail_Id);
            selectCredentials.setString(2, password);
            result = selectCredentials.executeQuery();
            isLoginSuccessful = result.next();
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return isLoginSuccessful;
    }

    @Override
    public void registerUser(String name, String number, String mail_id, String password) {
        try {
            PreparedStatement createUser = Database.getConnection().prepareStatement("Insert into register values(?,?,?,?)");
            createUser.setString(1, name);
            createUser.setString(2, number);
            createUser.setString(3, mail_id);
            createUser.setString(4, password);
            createUser.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public List<Food> viewCartItems(String mailId) {

        ResultSet result;
        List<Food> items = new ArrayList<>();
        try {
            PreparedStatement viewCart = Database.getConnection().prepareStatement("Select * from Cart where mail_id=?");
            viewCart.setString(1, mailId);
            result = viewCart.executeQuery();
            while (result.next()) {
                items.add(new Food(result.getInt(1), result.getString(2), result.getInt(4), result.getInt(5)));
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        return items;
    }

    @Override
    public boolean modifyCart(int foodId, int quantity, String mailId) {
        try {
            PreparedStatement selectFood = Database.getConnection().prepareStatement("select * from cart where foodId= ? and mail_id=?");
            selectFood.setInt(1, foodId);
            selectFood.setString(2, mailId);
            ResultSet rs = selectFood.executeQuery();
            if (rs.next()) {
                PreparedStatement updateFood = Database.getConnection().prepareStatement("update cart set quantity =?, cost=?  where foodId= ? and mail_id=?");
                updateFood.setInt(1, quantity);
                updateFood.setInt(2, MenuCard.getInstance().getItem(foodId).cost * quantity);
                updateFood.setInt(3, foodId);
                updateFood.setString(4, mailId);
                updateFood.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeCartItem(int foodId, String mailId) {
        try {
            PreparedStatement selectFood = Database.getConnection().prepareStatement("select * from cart where foodId= ? and mail_id=?");
            selectFood.setInt(1, foodId);
            selectFood.setString(2, mailId);
            ResultSet rs = selectFood.executeQuery();
            if (rs.next()) {
                PreparedStatement deleteFood = Database.getConnection().prepareStatement("delete from cart where foodId= ? and mail_id=?");
                deleteFood.setInt(1, foodId);
                deleteFood.setString(2, mailId);
                deleteFood.executeUpdate();
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return false;
    }

    @Override
    public List<Food> checkoutCart(String mailId) {
        List<Food> items = new ArrayList<>();
        ResultSet result;
        try {
            PreparedStatement selectFoodItems = Database.getConnection().prepareStatement("select * from cart where mail_id=?");
            selectFoodItems.setString(1, mailId);
            if (selectFoodItems.executeQuery().next()) {
                result = selectFoodItems.executeQuery();
                while (result.next()) {
                    PreparedStatement buyFoods = Database.getConnection().prepareStatement("Insert into PurchasedHistory values(?,?,?,?,?)");
                    buyFoods.setInt(1, result.getInt(1));
                    buyFoods.setString(2, result.getString(2));
                    buyFoods.setString(3, result.getString(3));
                    buyFoods.setInt(4, result.getInt(4));
                    buyFoods.setInt(5, result.getInt(5));
                    buyFoods.executeUpdate();
                    items.add(new Food(result.getInt(1), result.getString(2), result.getInt(4), result.getInt(5)));
                }
                PreparedStatement deleteCart = Database.getConnection().prepareStatement("delete from cart where mail_id=?");
                deleteCart.setString(1, mailId);
                deleteCart.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return items;
    }

    @Override
    public void addFoodItemToCart(String mailId, int foodId, int quantity) {
        MenuCard menuCard = MenuCard.getInstance();
        Food food = menuCard.getItem(foodId);
        try {
            PreparedStatement selectFood = Database.getConnection().prepareStatement("select * from cart where mail_id =? and foodId= ?");
            selectFood.setString(1, mailId);
            selectFood.setInt(2, foodId);
            ResultSet result = selectFood.executeQuery();
            if (result.next()) {
                PreparedStatement updateFood = Database.getConnection().prepareStatement("update cart set quantity =?, cost=?  where foodId= ? and mail_id=?");
                updateFood.setInt(1, result.getInt(4) + quantity);
                updateFood.setInt(2, menuCard.getItem(foodId).cost * (result.getInt(4) + quantity));
                updateFood.setInt(3, foodId);
                updateFood.setString(4, mailId);
                updateFood.executeUpdate();
            } else {
                PreparedStatement addFood = Database.getConnection().prepareStatement("Insert into cart values(?,?,?,?,?)");
                addFood.setInt(1, foodId);
                addFood.setString(2, food.name);
                addFood.setString(3, mailId);
                addFood.setInt(4, quantity);
                addFood.setInt(5, food.cost * quantity);
                addFood.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public List<Food> purchaseHistory(String mailId) {
        List<Food> items = new ArrayList<>();
        try {
            PreparedStatement viewHistory = Database.getConnection().prepareStatement("Select * from PurchasedHistory where mail_id=?");
            viewHistory.setString(1, mailId);
            ResultSet result = viewHistory.executeQuery();
            while (result.next()) {
                items.add(new Food(result.getInt(1), result.getString(2), result.getInt(4), result.getInt(5)));
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return items;
    }
}

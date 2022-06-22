package modelTest;

import model.CartImpl;
import model.Food;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.FoodAppRepo;
import repository.FoodAppRepoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartImplTest {
    static CartImpl cartImpl;
    static FoodAppRepo foodAppRepo;

    @BeforeAll
    public static void setUp() {
        foodAppRepo = mock(FoodAppRepoImpl.class);
        cartImpl = new CartImpl("abc@abc.com", foodAppRepo);
    }

    @Test
    void TestViewCartWhenDBReturnsAnEmptyList() {

        when(foodAppRepo.viewCartItems("abc@abc.com")).thenReturn(Collections.EMPTY_LIST);

        assertEquals(0, cartImpl.view().size());

    }

    @Test
    void TestViewCartWhenDBReturnsAnListWithOneFoodItem() {
        List<Food> items = new ArrayList<>();
        items.add(new Food(1, "Dosa", 2, 60));

        when(foodAppRepo.viewCartItems(any())).thenReturn(items);

        assertEquals(1, cartImpl.view().size());

    }

    @Test
    void TestModifyCartWhenDBReturnsTrueForItemsInCart() {

        when(foodAppRepo.modifyCart(2, 3, "abc@abc.com")).thenReturn(true);

        assertTrue(cartImpl.modifyFoodQuantity(2, 3));

    }

    @Test
    void TestModifyCartWhenDBReturnsFalseForItemsNotInCart() {

        when(foodAppRepo.modifyCart(2, 3, "abc@abc.com")).thenReturn(false);

        assertFalse(cartImpl.modifyFoodQuantity(2, 3));

    }

    @Test
    void TestRemoveFromCartWhenDBReturnsTrueForItemsInCart() {

        when(foodAppRepo.removeCartItem(2, "abc@abc.com")).thenReturn(true);

        assertTrue(cartImpl.removeItem(2));

    }

    @Test
    void TestRemoveFromCartWhenDBReturnsFalseForItemsNotInCart() {

        when(foodAppRepo.removeCartItem(1, "abc@abc.com")).thenReturn(false);

        assertFalse(cartImpl.removeItem(1));

    }


    @Test
    void TestCheckoutCartWhenDBReturnsSingleFoodItem() {
        List<Food> items = new ArrayList<>();
        items.add(new Food(1, "Dosa", 2, 60));

        when(foodAppRepo.checkoutCart("abc@abc.com")).thenReturn(items);

        assertEquals(items, cartImpl.checkout());

    }

    @Test
    void TestCheckoutCartWhenDBReturnsEmptyListOfItems() {

        when(foodAppRepo.checkoutCart("abc@abc.com")).thenReturn(Collections.EMPTY_LIST);

        assertEquals(0, cartImpl.checkout().size());

    }


}
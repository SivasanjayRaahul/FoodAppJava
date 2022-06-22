package modelTest;

import model.Food;
import model.MenuImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.FoodAppRepo;
import repository.FoodAppRepoImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MenuImplTest {
    static MenuImpl menuImpl;
    static FoodAppRepo foodAppRepo;

    @BeforeAll
    public static void setUp() {
        foodAppRepo = mock(FoodAppRepoImpl.class);
        menuImpl = new MenuImpl("abc@abc.com", foodAppRepo);
    }

    @Test
    void shouldReturnTwoFoodItemsInTheMenuCard() {

        List<Food> items = menuImpl.viewItems();

        assertEquals(10, items.size());

    }

    @Test
    void shouldReturnTrueWhenFoodIDBetweenOneAndTenIsAddedToCart() {

        assertTrue(menuImpl.addFoodItemToCart(1, 3));
    }

    @Test
    void shouldReturnTrueWhenFoodIDNotBetweenOneAndTenIsAddedToCart() {

        assertFalse(menuImpl.addFoodItemToCart(11, 3));
    }

    @Test
    void shouldReturnTwoFoodItemsInPurchasedHistory(){
        List<Food> items = new ArrayList<>();
        items.add(new Food(1, "Dosa", 2, 60));
        items.add(new Food(2, "Parotta", 2, 30));


        when(foodAppRepo.purchaseHistory("abc@abc.com")).thenReturn(items);

        assertEquals(2, menuImpl.purchaseHistory().size());
    }


}
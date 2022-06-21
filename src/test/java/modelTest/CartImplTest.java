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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartImplTest {
    static CartImpl cartImpl;
    static FoodAppRepo foodAppRepo;

    //set up
    @BeforeAll
    public static void setUp() {
        foodAppRepo = mock(FoodAppRepoImpl.class);
        cartImpl = new CartImpl("abc@abc.com", foodAppRepo);
    }
    @Test
    void TestViewCartWhenDBReturnsAnEmptyList() {

        //conditions
        when(foodAppRepo.viewCart("abc@abc.com")).thenReturn(Collections.EMPTY_LIST);

        //check
        assertEquals(0, cartImpl.viewCart().size());

    }

    @Test
    void TestViewCartWhenDBReturnsAnListWithOneFoodItem() {
        List<Food> items = new ArrayList<>();
        items.add(new Food(1, "Doas", 2, 60));

        when(foodAppRepo.viewCart(any())).thenReturn(items);

        assertEquals(1, cartImpl.viewCart().size());

    }



}
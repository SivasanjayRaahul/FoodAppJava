package modelTest;

import model.UserImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.FoodAppRepo;
import repository.FoodAppRepoImpl;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserImplTest {

    static UserImpl userImpl;
    static FoodAppRepo foodAppRepo;

    @BeforeAll
    public static void setUp() {
        foodAppRepo = mock(FoodAppRepoImpl.class);
        userImpl = new UserImpl(foodAppRepo);
    }

    @Test
    void shouldReturnTrueWhenExistingUserLogIn() throws SQLException {

        when(foodAppRepo.isLoginSuccessful(any(), any())).thenReturn(true);

        assertTrue(userImpl.isLoginSuccessful(any(), any()));

    }

    @Test
    void shouldReturnFalseWhenNewUserLogIn() throws SQLException {

        when(foodAppRepo.isLoginSuccessful("abc@abc.com","ssrraja123")).thenReturn(false);

        assertFalse(userImpl.isLoginSuccessful("abc@abc.com","ssrraja123"));

    }

    @Test
    void shouldReturnTrueWhenNewUserRegistersSuccessfully() {
        assertTrue(userImpl.isRegistrationSuccessful("Shruthi", "1234567890", "ssr@gmail.com", "3t51Sa6s89"));
    }

    @Test
    void shouldReturnFalseWhenNewUserRegisterWithEmptyName() {
        assertFalse(userImpl.isRegistrationSuccessful("", "1234567890","abc@abc.com","ssrja"));
    }


}

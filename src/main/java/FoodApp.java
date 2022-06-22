import model.CartImpl;
import model.MenuImpl;
import model.UserImpl;
import repository.FoodAppRepo;
import repository.FoodAppRepoImpl;
import static constants.Constants.scanner;

public class FoodApp {

    static FoodAppRepo foodAppRepo = new FoodAppRepoImpl();

    public FoodApp() {

    }

    public static void main(String[] args) {
        UserImpl userImpl = new UserImpl(foodAppRepo);
        System.out.println("1] Login");
        System.out.println("2] Register");

        int option = scanner.nextInt();

        if (option == 1) {
            System.out.println("Enter the MailId");
            String mailId = scanner.next();
            System.out.println("Enter the Password");
            String password = scanner.next();

            if (userImpl.isLoginSuccessful(mailId, password)) {
                System.out.println("*Login Successful*");
                menu(mailId);
            } else {
                System.out.println("Login Failed!!");
                main(args);
            }

        } else {
            System.out.println("Enter the name");
            String name = scanner.next();
            System.out.println("Enter the Phone Number");
            String number = scanner.next();
            System.out.println("Enter the MailId");
            String mail_Id = scanner.next();
            System.out.println("Enter the Password");
            String password = scanner.next();

            if (userImpl.isRegistrationSuccessful(name, number, mail_Id, password)) {
                System.out.println("*Registration Successful*");

            } else {
                System.out.println("Registration Failed!!");
            }
            main(args);

        }
    }

    public static void menu(String mailId) {
        MenuImpl menuImpl = new MenuImpl(mailId, foodAppRepo);

        int option;
        System.out.println("\n1] View Items");
        System.out.println("2] Add To Cart");
        System.out.println("3] Go To Cart");
        System.out.println("4] Purchased Items");
        option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println(menuImpl.viewItems());
                menu(mailId);
                break;
            case 2:
                System.out.println("Enter FoodID");
                int foodId = scanner.nextInt();
                System.out.println("Enter Quantity");
                int quantity = scanner.nextInt();
                if (menuImpl.addFoodItemToCart(foodId, quantity))
                    System.out.println("*Added To Cart*");
                else
                    System.out.println("Failed To Add To Cart!!");
                menu(mailId);
                break;
            case 3:
                cart(mailId);
                break;
            case 4:
                System.out.println(menuImpl.purchaseHistory());
                menu(mailId);
                break;
        }
    }

    public static void cart(String mailId) {

        CartImpl cartImpl = new CartImpl(mailId, foodAppRepo);

        System.out.println("\n1] View Cart Items");
        System.out.println("2] Remove From Cart");
        System.out.println("3] Modify Cart");
        System.out.println("4] Checkout");
        System.out.println("5] Go Back");
        int cartOption = scanner.nextInt();
        int foodId;
        switch (cartOption) {
            case 1:
                System.out.println(cartImpl.view());
                cart(mailId);
                break;
            case 2:
                System.out.println("Enter the FoodID to be removed from Cart");
                foodId = scanner.nextInt();
                cartImpl.removeItem(foodId);
                cart(mailId);
                break;
            case 3:
                System.out.println("Enter FoodID to be modified in Cart");
                foodId = scanner.nextInt();
                System.out.println("Enter Quantity");
                int quantity = scanner.nextInt();
                cartImpl.modifyFoodQuantity(foodId, quantity);
                cart(mailId);
                break;
            case 4:
                cartImpl.checkout();
                cart(mailId);
                break;
            case 5:
                menu(mailId);
                break;
        }
    }

}
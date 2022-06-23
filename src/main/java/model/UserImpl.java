package model;

import repository.FoodAppRepo;

public class UserImpl implements User {

    private final FoodAppRepo foodAppRepo;

    public UserImpl(FoodAppRepo foodAppRepo) {
        this.foodAppRepo = foodAppRepo;
    }

    @Override
    public boolean isLoginSuccessful(String mailId, String password) {
        return foodAppRepo.isLoginSuccessful(mailId, password);
    }

    @Override
    public boolean isRegistrationSuccessful(String name, String number, String mailId, String password) {
        if (!name.equals("") && !number.equals("") && !mailId.equals("") && !password.equals("") && number.length() == 10) {
            foodAppRepo.registerUser(name, number, mailId, password);
            return true;
        }
        return false;
    }
}

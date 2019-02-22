package casebook.domain.models.binding;

import casebook.domain.entities.Gender;

import java.util.ArrayList;
import java.util.List;

public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private Gender gender;
    private List<UserRegisterBindingModel> friends;

    public UserRegisterBindingModel() {
        this.friends = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<UserRegisterBindingModel> getFriends() {
        return friends;
    }

    public void setFriends(List<UserRegisterBindingModel> friends) {
        this.friends = friends;
    }
}

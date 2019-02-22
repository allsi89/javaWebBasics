package casebook.service;

import casebook.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel createUser(UserServiceModel userServiceModel);

    UserServiceModel getUserByUsername(String username);

    List<UserServiceModel> findAllUsers();

    UserServiceModel getUserById(String id);

    boolean addFriend(UserServiceModel userServiceModel);

    boolean removeFriend(UserServiceModel user);
}

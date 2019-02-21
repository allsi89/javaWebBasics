package sboj.service;

import sboj.domain.models.service.UserServiceModel;

public interface UserService {
    UserServiceModel createUser(UserServiceModel userServiceModel);

    UserServiceModel getUserById(String id);

    UserServiceModel getUserByUsername(String username);
}

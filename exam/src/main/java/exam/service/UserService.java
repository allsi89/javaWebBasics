package exam.service;

import exam.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findById(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    List<UserServiceModel> findAll();



}

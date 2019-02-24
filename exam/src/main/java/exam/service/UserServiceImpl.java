package exam.service;

import exam.domain.entities.User;
import exam.domain.models.service.UserServiceModel;
import exam.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        return this.modelMapper.map(this.userRepository
                .save(this.modelMapper.map(userServiceModel, User.class)), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findById(UserServiceModel userServiceModel) {
//        return this.modelMapper.map(this.userRepository.findById(id), UserServiceModel.class);
        return null;
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.modelMapper.map(
                this.userRepository.findByUsername(username),
                UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAll() {
//        return this.userRepository
//                .findAll()
//                .stream()
//                .map(u->this.modelMapper.map(u, UserServiceModel.class))
//                .collect(Collectors.toList());
        return null;
    }


    //for update, delete
//    public boolean update(UserServiceModel serviceModel) {
////        User user = this.userRepository.update(this.modelMapper.map(UserServiceModel, User.class));
////        return user != null;
//
//    }
}

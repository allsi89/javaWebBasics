package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("userHomeBean")
@RequestScoped
public class UserHomeBean extends BaseBean {
    private List<UserViewModel> users;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserHomeBean() {
    }

    @Inject
    public UserHomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String username = (String) ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false))
                .getAttribute("userName");

        String id = (String) ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false))
                .getAttribute("userId");

        UserServiceModel loggedInUser = this.userService.getUserById(id);

        this.users = this.userService
                .findAllUsers()
                .stream()
                .filter(u -> !u.getUsername().equals(username) &&
                        !loggedInUser.getFriends()
                                .stream()
                                .map(UserServiceModel::getUsername)
                                .collect(Collectors.toList()).contains(u.getUsername()))
                .map(u -> this.modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserViewModel> users) {
        this.users = users;
    }

    public void addFriend(String id) {
        UserServiceModel loggedInUser = this.userService
                .getUserById(((HttpSession) FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .getSession(true))
                        .getAttribute("userId")
                        .toString());
        UserServiceModel userServiceModel = this.userService.getUserById(id);

        loggedInUser.getFriends().add(userServiceModel);

        if (!this.userService.addFriend(loggedInUser)) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        this.redirect("/home");
    }
}

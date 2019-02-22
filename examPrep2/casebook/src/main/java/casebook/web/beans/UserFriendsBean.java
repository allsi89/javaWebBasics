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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserFriendsBean extends BaseBean {
    private List<UserViewModel> friends;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserFriendsBean() {
    }

    @Inject
    public UserFriendsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String id = (String) (((HttpServletRequest)
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequest())
                .getSession())
                .getAttribute("userId");

        this.friends = this.userService
                .getUserById(id)
                .getFriends()
                .stream()
                .map(f -> this.modelMapper.map(f, UserViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserViewModel> getFriends() {
        return friends;
    }

    public void setFriends(List<UserViewModel> friends) {
        this.friends = friends;
    }

    public void removeFriend(String id) {
        UserServiceModel user = this.userService
                .getUserById(((HttpSession) FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .getSession(false))
                        .getAttribute("userId")
                        .toString());
        UserServiceModel friend = user.getFriends()
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
        this.userService.getUserById(id);
        if (friend != null) {
            user.getFriends().remove(friend);
        }
        if (!this.userService.removeFriend(user)) {
            throw new IllegalArgumentException("Something went wrong");
        }
        this.redirect("/friends");
    }
}

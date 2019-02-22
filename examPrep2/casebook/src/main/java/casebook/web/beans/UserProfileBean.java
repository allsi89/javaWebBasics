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

@Named
@RequestScoped
public class UserProfileBean extends BaseBean {
    private UserViewModel userViewModel;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("username");

        UserServiceModel userServiceModel = this.userService.getUserByUsername(username);
        if (userServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        this.userViewModel = this.modelMapper
                .map(userServiceModel, UserViewModel.class);
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }


}

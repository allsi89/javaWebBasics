package exam.web.beans;

import exam.domain.models.binding.UserLoginBindingModel;
import exam.domain.models.service.UserServiceModel;
import exam.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named("userLoginBean")
@RequestScoped
public class UserLoginBean extends BaseBean {
    private UserLoginBindingModel userLoginBindingModel;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void login() throws IOException {
        UserServiceModel userServiceModel = null;
        try {
            userServiceModel = this.userService.findByUsername(this.userLoginBindingModel.getUsername());
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        if (userServiceModel == null){
            return;
        }

        String currentPassword = DigestUtils.sha256Hex(this.userLoginBindingModel.getPassword());

        if (!userServiceModel.getPassword().equals(currentPassword)) {
            return;
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.setAttribute("userId", userServiceModel.getId());
        session.setAttribute("userName", userServiceModel.getUsername());

        this.redirect("/home");
    }
}

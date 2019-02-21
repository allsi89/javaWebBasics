package sboj.web.managedbeans;

import org.apache.commons.codec.digest.DigestUtils;
import sboj.domain.models.binding.UserLoginBindingModel;
import sboj.domain.models.service.UserServiceModel;
import sboj.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named("loginBean")
@RequestScoped
public class LoginBean extends BaseBean {
    private UserLoginBindingModel userLoginBindingModel;
    private UserService userService;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
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

    public void login() {
        UserServiceModel user = null;
        try {
            user = this.userService
                    .getUserByUsername(this.userLoginBindingModel.getUsername());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        String currentPassword = DigestUtils.sha256Hex(this.userLoginBindingModel.getPassword());

        if (user == null || !user.getPassword().equals(currentPassword)) {
            return;
        }
        Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("user-id", user.getId());
        sessionMap.put("username", user.getUsername());
        this.redirect("/home");
    }
}

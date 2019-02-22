package casebook.web.beans;

import casebook.domain.models.binding.UserLoginBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;

@Named("userLoginBean")
@RequestScoped
public class UserLoginBean extends BaseBean {
    private UserLoginBindingModel user;
    private UserService userService;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        this.user = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUser() {
        return user;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.user = userLoginBindingModel;
    }

    public void login() throws IOException {
        UserServiceModel user = null;
        try {
            user = this.userService
                    .getUserByUsername(this.user.getUsername());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        String currentPassword = DigestUtils.sha256Hex(this.user.getPassword());

        if (user == null || !user.getPassword().equals(currentPassword)) {
            return;
        }
        Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("userId", user.getId());
        sessionMap.put("userName", user.getUsername());
        this.redirect("/home");
    }
}

package net.smart4life.springuserplay.user;

import net.smart4life.springuserplay.UserFactory;
import net.smart4life.springuserplay.entity.User;
import net.smart4life.springuserplay.scope.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 16.02.2015.
 */
@Controller
@ViewScoped
public class UserDetailController implements Serializable {

    @Autowired
    private UserFactory userFactory;

    @PostConstruct
    private void init(){
        Map<String, String> paramMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idStr = paramMap.get("entityId");
        if(idStr != null && !idStr.isEmpty()) {
            Integer id = Integer.parseInt(idStr);
            entity = userFactory.getById(id);
        }
    }

   private User entity;

    public User getEntity() {
        return entity;
    }

    public void setEntity(User entity) {
        this.entity = entity;
    }

    public void save(){

    }

    public void toList(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String redirect = "userList.xhtml?faces-redirect=true";
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, redirect);
    }
}

package net.smart4life.springuserplay.user;

import net.smart4life.springuserplay.UserFactory;
import net.smart4life.springuserplay.entity.User;
import net.smart4life.springuserplay.scope.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by roman on 16.02.2015.
 */
@Component
@ViewScoped
public class UserListController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(UserListController.class);

    @Autowired
    private UserFactory userFactory;

    private User entity;

    @PostConstruct
    private void init(){
        logger.debug("ich bin in init()");
    }

    public List<User> getUsers(){
        return userFactory.getUsers();
    }

    public String getListPage() {
        return "userList.xhtml?faces-redirect=true";
    }

    public void onRowSelect(SelectEvent event) {
        User u = (User) event.getObject();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        String redirect = "userDetail.xhtml?faces-redirect=true&entityId="+u.getId();
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, redirect);
    }

    public User getEntity() {
        return entity;
    }

    public void setEntity(User entity) {
        this.entity = entity;
    }
}

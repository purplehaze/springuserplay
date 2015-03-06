package net.smart4life.springuserplay.user;

import net.smart4life.springuserplay.UserFactory;
import net.smart4life.springuserplay.controller.BaseController;
import net.smart4life.springuserplay.entity.User;
import net.smart4life.springuserplay.scope.ViewScoped;
import net.smart4life.springuserplay.scope.viewaccess.ViewAccessScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ViewAccessScoped
public class UserDetailController extends BaseController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailController.class);
    public static final String PAGE_LIST = "userList.xhtml";

    @Autowired
    private UserFactory userFactory;

    @PostConstruct
    private void init(){
        String idStr = getIdAsString();
        if(idStr != null && !idStr.isEmpty()) {
            Long id = Long.parseLong(idStr);
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
        logger.debug("do save()");
    }

    public void toList(){
        navigate(PAGE_LIST, true);
    }
}

package net.smart4life.springuserplay.user;

import net.smart4life.springuserplay.service.UserService;
import net.smart4life.springuserplay.controller.BaseController;
import net.smart4life.springuserplay.entity.User;
import net.smart4life.springuserplay.scope.viewaccess.ViewAccessScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * Created by roman on 16.02.2015.
 */
@Controller
@ViewAccessScoped
public class UserDetailController extends BaseController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailController.class);
    public static final String PAGE_LIST = "userList.xhtml";

    @Autowired
    private UserService userService;

	@Autowired
	private UserListController userListController;

	@Autowired
	private UserFilterController userFilterController;

    @PostConstruct
    private void init(){
        String idStr = getIdAsString();
        if(idStr != null && !idStr.isEmpty()) {
            Long id = Long.parseLong(idStr);
            entity = userService.getById(id);
        }

		User selElem = userListController.getSelectedElement();
		logger.debug("selectedElement={}", selElem);
		UserFilter uf = userFilterController.getFilter();
		logger.debug("userFilter.name={}", uf.getName());
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

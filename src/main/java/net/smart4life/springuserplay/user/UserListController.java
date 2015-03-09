package net.smart4life.springuserplay.user;

import net.smart4life.springuserplay.datamodel.BaseFilter;
import net.smart4life.springuserplay.datamodel.GenericLazyDataModel;
import net.smart4life.springuserplay.service.UserService;
import net.smart4life.springuserplay.controller.BaseController;
import net.smart4life.springuserplay.datamodel.GenericIdableListDataModel;
import net.smart4life.springuserplay.entity.User;
import net.smart4life.springuserplay.scope.viewaccess.ViewAccessScoped;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 16.02.2015.
 */
@Component
@ViewAccessScoped
public class UserListController extends BaseController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(UserListController.class);

    public static final String PAGE_DETAIL = "userDetail.xhtml";

    @Autowired
    private UserService userService;

    @Autowired
    private UserFilterController userFilterController;

    private User selectedElement;
    private DataModel<User> dataModel;

    @PostConstruct
    private void init(){
        createDataModel();
    }

	// create non lazy data model
//	private void createDataModel(){
//		List<User> users = userService.getUsers();
//		String name = userFilterController.getFilter().getName();
//		if(name != null && !name.isEmpty()){
//			List<User> tmp = new ArrayList<>();
//			for (User u : users){
//				if(u.getLoginname().toLowerCase().startsWith(name.toLowerCase())){
//					tmp.add(u);
//				}
//			}
//			users = tmp;
//		}
//		dataModel = new GenericIdableListDataModel<>(users);
//	}

	// create lazy data model
	private void createDataModel(){
		dataModel = new GenericLazyDataModel<User, UserFilter>(userService) {
			@Override
			public UserFilter getFilter() {
				return userFilterController.getFilter();
			}
		};
	}

    public void onRowSelect(SelectEvent event) {
        User u = (User) event.getObject();

        navigate(PAGE_DETAIL, ""+selectedElement.getId(), true);
    }

    public void search(){
        createDataModel();
    }

    public void reset(){
        userFilterController.reset();
        search();
    }

    public User getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(User selectedElement) {
        this.selectedElement = selectedElement;
    }

    public DataModel<User> getDataModel() {
        return dataModel;
    }
}

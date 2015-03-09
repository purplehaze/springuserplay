package net.smart4life.springuserplay.user;

import net.smart4life.springuserplay.scope.viewaccess.ViewAccessScoped;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * Created by roman on 06.03.2015.
 */
@Controller
@ViewAccessScoped
public class UserFilterController {

    private UserFilter filter;
	private String sortField;
	private String sortOrder = SortOrder.ASCENDING.name();

    @PostConstruct
    private void init(){
        filter = new UserFilter();
    }

    public void reset(){
        init();
    }

    public UserFilter getFilter() {
        return filter;
    }

    public void setFilter(UserFilter filter) {
        this.filter = filter;
    }

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}

package net.smart4life.springuserplay.service;

import net.smart4life.springuserplay.datamodel.BaseFilter;
import net.smart4life.springuserplay.datamodel.SortDirection;
import net.smart4life.springuserplay.entity.User;
import net.smart4life.springuserplay.user.UserFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by roman on 16.02.2015.
 */
@Component
public class UserService implements DataModelFindAble<User, UserFilter>, Serializable {

    private static String[] names =
            new String[]{"roman", "natascha", "erik", "terry", "maria", "rim", "tagir", "ponchik"}
    ;

    private List<User> users;

    @PostConstruct
    private void init(){
        users = new ArrayList<>();

        for(long i=0; i < names.length ; i++){
            User u = new User();
            u.setId(i+1);
            u.setLoginname(names[Integer.parseInt(""+i)]);
            u.setPassword("geheim");
            users.add(u);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public User getById(Long id)
    {
        User user = null;
        for(User u : users){
            if(u.getId().equals(id)){
                user = u;
                break;
            }
        }
        return user;
    }

	@Override
	public List<User> findByFilter(final UserFilter filter, int first, int pageSize) {
		String name = filter.getName();
		List<User> tmp = new ArrayList<>();
		if(name != null && !name.isEmpty()){
			for (User u : users){
				if(u.getLoginname().toLowerCase().startsWith(name.toLowerCase())){
					tmp.add(u);
				}
			}
		} else {
			tmp.addAll(users);
		}

		if(filter.getSortMeta() != null && filter.getSortMeta().getSortField() != null){
			final String sFieldVal = filter.getSortMeta().getSortField().trim();
			if(!sFieldVal.isEmpty()){
				Collections.sort(tmp, new Comparator<User>() {
					@Override
					public int compare(User o1, User o2) {
						User u1 = o1;
						User u2 = o2;
						if(filter.getSortMeta().getSortDirection().equals(SortDirection.DESCENDING)){
							u1 = o2;
							u2 = o1;
						}
						switch (sFieldVal){
							case "id":
								return u1.getId().compareTo(u2.getId());
							case "loginname":
								return u1.getLoginname().compareTo(u2.getLoginname());
							case "password":
								return u1.getPassword().compareTo(u2.getPassword());
							default:
								return 0;
						}
					}
				});
			}
		}

		if(tmp.size() > 0){
			if(first > 0 && tmp.size() > first){
				tmp = tmp.subList(first, tmp.size());
			}
			if(pageSize > 0 && tmp.size() > pageSize){
				tmp = tmp.subList(0, pageSize);
			}
		}
		return tmp;
	}

	@Override
	public Long countByFilter(UserFilter filter) {
		return new Long(findByFilter(filter, -1, -1).size());
	}
}

package net.smart4life.springuserplay;

import net.smart4life.springuserplay.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 16.02.2015.
 */
@Component
public class UserFactory implements Serializable {

    private static String[] names =
            new String[]{"roman", "natascha", "erik", "terry", "maria", "rim", "tagir", "ponchik"}
    ;

    private List<User> users;

    @PostConstruct
    private void init(){
        users = new ArrayList<>();

        for(int i=0; i < names.length ; i++){
            User u = new User();
            u.setId(i+1);
            u.setLoginname(names[i]);
            u.setPassword("geheim");
            users.add(u);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public User getById(Integer id)
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
}

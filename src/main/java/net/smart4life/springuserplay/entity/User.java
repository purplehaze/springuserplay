package net.smart4life.springuserplay.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by roman on 16.02.2015.
 */

public class User implements Idable<Long> {
    @NotNull
    private Long id;

    @NotNull
    @Size(max = 10, min = 2)
    private String loginname;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

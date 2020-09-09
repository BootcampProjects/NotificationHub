package org.kodluyoruz.trendyol.model;

import java.util.*;

public class PostGroup {
    private List<User> users;

    public PostGroup(){
        users = new ArrayList<User>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addUsers(User... users) {
        for (User user : users) {
            this.users.add(user);
        }
    }
}

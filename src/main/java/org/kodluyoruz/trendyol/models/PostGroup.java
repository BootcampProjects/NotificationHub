package org.kodluyoruz.trendyol.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class PostGroup {
    private List<User> users;

    public PostGroup() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addUsers(User... users) {
        Collections.addAll(this.users, users);
    }
}

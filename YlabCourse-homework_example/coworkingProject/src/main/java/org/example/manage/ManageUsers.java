package org.example.manage;

import org.example.User;

import java.util.*;

public class ManageUsers {
    Map<String, User> users = new HashMap<>(); // username -> user

    public void useradd(String username,String name,String password)
    {
        User user = users.get(username);
        if (user == null)
        {
            users.put(username,new User(name,username,password));
        } else {
            System.out.println("пользователь уже существует!");

        }
    }

    public boolean Login (String username,String password)
    {
        User user = users.get(username);
        if (user == null)
        {
            return false;
        }
        if (user.getpassword().equals(password))
        {
            return true;
        }
        return false;
    }
    public User getUser(String username)
    {
        return users.get(username);
    }
}

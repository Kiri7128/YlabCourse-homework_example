package org.example;

public class User {
    private String name;
    private String username;
    private String password;
    //isadmin
    public User(String name,String username, String password)
    {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {return name;}
    public String getusername() {return username;}
    public String getpassword() {return password;}
}

package app;

import java.sql.*;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private Timestamp update_time;

    public User(int id,String name, String email, String password, String phone, Timestamp update_time ,String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.update_time = update_time;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public String getRole() {
        return role;
    }


}


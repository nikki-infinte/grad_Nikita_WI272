package com.maintenance.model;

public class User {
    private int uid;
    private String name;
    private String phone;
    private String role;
    private String username;
    private String password;

    public User() {}

    public User(int uid, String name, String phone, String role) {
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public User(String name, String phone, String role) {
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public int getUid() { return uid; }
    public void setUid(int uid) { this.uid = uid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

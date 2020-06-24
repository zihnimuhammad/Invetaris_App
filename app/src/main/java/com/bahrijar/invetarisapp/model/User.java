package com.bahrijar.invetarisapp.model;


public class User {

    private String userId ;
    private String usernip;
    private String userpassword;
    private String username;
    private String userrole;

    public User() {
    }

    public User(String userId, String usernip, String userpassword, String username, String userrole) {
        this.userId = userId;
        this.usernip = usernip;
        this.userpassword = userpassword;
        this.username = username;
        this.userrole = userrole;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsernip() {
        return usernip;
    }

    public void setUsernip(String usernip) {
        this.usernip = usernip;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }
}

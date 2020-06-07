package com.MemberDomain.model;

public class User {

    private String id_user;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String id_role;
    private int is_active;

    public User() {
    }

    public User(String id_user, String name, String email, String phoneNumber, String password, String id_role, int is_active) {
        super();
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.phoneNumber = "+62"+phoneNumber;
        this.password = password;
        this.id_role = id_role;
        this.is_active = is_active;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("ID User = ").append(id_user).append(" - ");
        sb.append("User Name = ").append(name).append(" - ");
        sb.append("User Email = ").append(email).append(" - ");
        sb.append("User Phone Number = ").append(phoneNumber).append(" - ");
        sb.append("User Password = ").append(password).append(" - ");
        sb.append("ID Role = ").append(id_role).append(" - ");
        sb.append("User Active Status = ").append(is_active).append(" - ");

        return sb.toString();
    }

    public User(String name, String email, String phoneNumber, String password, String id_role, int is_active){
        this("", name, email, phoneNumber, password, id_role, is_active);
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId_role(String id_role) {
        this.id_role = id_role;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getId_role() {
        return id_role;
    }

    public int getIs_active() {
        return is_active;
    }

    public Boolean checkPasswordRegex(){
        String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!.,?_])(?=\\S+$).{8,20}";
        return password.matches(regex);
    }
    public Boolean checkEmailRegex(){
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return email.matches(regex);
    }
    public Boolean checkPhoneRegex(){
        String regex = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
        return phoneNumber.matches(regex);
    }

}
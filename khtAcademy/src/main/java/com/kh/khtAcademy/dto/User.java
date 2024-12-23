package com.kh.khtAcademy.dto;

public class User {
    private int userId;
    private String userName;
    private String email;
    private String birthday;
    private String account_balance;
    private String gender;
    private String hobbies;

    public User() {
    }

    public User(int userId, String userName, String email, String birthday, String account_balance, String gender, String hobbies) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.birthday = birthday;
        this.account_balance = account_balance;
        this.gender = gender;
        this.hobbies = hobbies;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", account_balance='" + account_balance + '\'' +
                ", gender='" + gender + '\'' +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }
}

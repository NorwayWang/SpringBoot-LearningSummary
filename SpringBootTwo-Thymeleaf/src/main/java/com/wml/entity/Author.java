package com.wml.entity;

public class Author {
    private String userName;

    private int age;

    private String email;

    private String realName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}

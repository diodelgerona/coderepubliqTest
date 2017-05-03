package com.testproj.coderepubliq.testprojcoderepubliq.Models;

/**
 * Created by diodelgerona on 03/05/2017.
 */

public class Entry {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    int id;
    String firstName;
    String lastName;
}

package com.example.a.testfinal.model;

import java.io.Serializable;

public class Department implements Serializable {
    int depID;
    String name, managerID;

    public Department() {
    }

    public Department(String name, String managerID) {
        this.name = name;
        this.managerID = managerID;
    }

    public Department(int depID, String name, String managerID) {
        this.depID = depID;
        this.name = name;
        this.managerID = managerID;
    }

    public int getDepID() {
        return depID;
    }

    public void setDepID(int depID) {
        this.depID = depID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }
}

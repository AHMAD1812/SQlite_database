package com.example.sqlite;

public class userModel{

    String Name,Section,Address;
    int id;

    public userModel(String name, String section, String address) {
        this.Name = name;
        this.Section = section;
        this.Address = address;
    }

    public userModel(int id,String name, String section, String address) {
        this.id=id;
        this.Name = name;
        this.Section = section;
        this.Address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}

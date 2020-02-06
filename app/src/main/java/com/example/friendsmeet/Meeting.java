package com.example.friendsmeet;

public class Meeting {

    private String name;
    private String[] members;
    private double longitude;
    private double latitude;

    public Meeting() {

    }

    public Meeting(String name, String[] members, double longitude, double latitude) {
        this.name = name;
        this.members = members;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public String[] getMembers() {
        return members;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

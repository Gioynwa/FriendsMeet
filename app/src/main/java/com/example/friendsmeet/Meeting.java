package com.example.friendsmeet;

public class Meeting {

    private String id;
    private String name;
    private String members;
    private double longitude;
    private double latitude;
    private String dest;

    public Meeting() {

    }

    public Meeting(String id, String name, String members, double longitude, double latitude, String dest) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dest = dest;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMembers() {
        return members;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDest() {
        return dest;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }
}

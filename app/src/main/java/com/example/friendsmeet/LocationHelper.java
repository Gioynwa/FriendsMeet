package com.example.friendsmeet;

public class LocationHelper {

    private double Longitude;
    private double Latitude;
    private String Email;
    private String ID;

    public LocationHelper() {

    }

    public LocationHelper(String id, double longitude, double latitude, String email) {
        ID = id;
        Longitude = longitude;
        Latitude = latitude;
        Email = email;
    }

    public String getID() {
        return ID;
    }

    public double getLongitude() {
        return Longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public String getEmail() {
        return Email;
    }

    public void setID(String id) {
        ID = id;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public void setLatitude(double latitude) {
        Longitude = latitude;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

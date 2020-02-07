package com.example.friendsmeet;

public class LocationHelper {

    private String ID;
    private double Longitude;
    private double Latitude;
    private String Email;

    public LocationHelper() {

    }

    public LocationHelper(String id, double longitude, double latitude, String email) {
        this.ID = id;
        this.Longitude = longitude;
        this.Latitude = latitude;
        this.Email = email;
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

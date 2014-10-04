package com.pp2.starlords.pp2;

/**
 * Created by Birk on 04-10-2014.
 */
public class Position {

    private double longitude;
    private double lattitude;

    public Position(double longi, double latti) {
        longitude = longi;
        lattitude = latti;
    }

    public void setLongitude(double d) {
        longitude = d;
    }

    public void setLattitude(double d) {
        lattitude = d;
    }

    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

}

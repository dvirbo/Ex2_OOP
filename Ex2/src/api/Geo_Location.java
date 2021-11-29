package src.api;

import src.interfaces.GeoLocation;

public class Geo_Location implements GeoLocation {
    private double x,y,z;

    /**
     * constructor by variables.
     * @param x1
     * @param y1
     * @param z1
     */
    public Geo_Location(double x1 , double y1, double z1){
        this.x = x1;
        this.y = y1;
        this.z = z1;
    }

    /**
     * Deep copy constructor.
     * @param other - geo_location
     */
    public Geo_Location(GeoLocation other){
        this.x = other.x();
        this.y = other.y();
        this.z = other.z();

    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    /**
     * Returns the distance between two geoLocations points.
     * @param g - geo_location
     * @return the distance between this and g.
     */
    @Override
    public double distance(GeoLocation g) {
        double nx = Math.pow(this.x-g.x(),2);
        double ny = Math.pow(this.y-g.y(),2);
        double nz = Math.pow(this.z-g.z(),2);
        double distance = Math.sqrt(nx+ny+nz);
        return distance;
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y + ", " + this.z;
    }
}

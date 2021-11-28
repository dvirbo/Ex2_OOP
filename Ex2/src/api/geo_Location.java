package src.api;

public class geo_Location implements GeoLocation{
    private double x,y,z;

    /**
     * constructor by variables.
     * @param x
     * @param y
     * @param z
     */
    public geo_Location(double x , double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Deep copy constructor.
     * @param other - geo_location
     */
    public geo_Location(GeoLocation other){
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

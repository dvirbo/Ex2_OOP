package src.api;

import java.util.Objects;

public class Node implements NodeData,Comparable<NodeData> {
    /**
     * Each node contains few fields:
     * location: An object that represent the location of the node by 3d point.
     * weight: A variable that is used in later functions, by default Initialized to Integer.MAX_VALUE(infinite).
     * info: A variable that is used in later functions, by default Initialized to "White".
     * tag: A variable that is used in later functions, by default Initialized to -1.
     * key: A unique key that is used as each node's ID.
     */

    private static int id = 0;
    private int key;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;


    /**
     * Constructor.
     * @param l - geo_Location.
     */

    public Node(GeoLocation l){
        this.key = id++;
        this.location = l;
        this.weight = Double.MAX_VALUE;
        this.info = "White";
        this.tag = -1;
    }

    /**
     * Deep copy constructor.
     * @param other - node_gata.
     */

    public Node(NodeData other) {
        this.key = other.getKey();
        this.location = new Geo_Location(other.getLocation());
        this.weight = other.getWeight();
        this.info = other.getInfo();
        this.tag = other.getTag();
    }

    /**
     * Returns the key (id) associated with this node.
     * @return key
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /** Returns the location of this node, if none return null.
     * @return the location of this node.
     */
    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    /** Allows changing this node's location.
     * @param p - new new location  (position) of this node.
     */
    @Override
    public void setLocation(GeoLocation p) {
        this.location = new Geo_Location(p.x(),p.y(),p.z());
    }

    /**
     * Returns the weight associated with this node.
     * @return weight.
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * Allows changing this node's weight.
     * @param w - the new weight.
     */
    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    /**
     * Returns the remark (meta data) associated with this node.
     * @return this node info.
     */
    @Override
    public String getInfo() {
        return this.info;
    }
    /**
     * Allows changing the remark (meta data) associated with this node.
     * @param s - the new info.
     */
     @Override
     public void setInfo(String s) {
         this.info = s;
     }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    /**
     * This method override compareTo in order to compare two nodes only by their weight.
     */
    @Override
    public int compareTo(NodeData o) {
        Node p = this;
        return Double.compare(p.getWeight(), o.getWeight());
    }

    /**
     *
     * @param o - an Object.
     * @return true if the arguments are equal to each other and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return key == node.key &&
                Double.compare(node.weight, weight) == 0 &&
                tag == node.tag &&
                Objects.equals(location, node.location) &&
                Objects.equals(info, node.info);
    }

    /**
     * Override hashcode
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(key, location, weight, info, tag);
    }
}

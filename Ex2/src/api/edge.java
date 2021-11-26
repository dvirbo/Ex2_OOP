package src.api;

import java.util.Objects;

/**
 *  This class is an implementation of EdgeData interface.
 * edge class implement a set of operations applicable on a
 * directional edge(src,dest) in a (directional) weighted graph
 * @author

 */

public class edge implements EdgeData{

    private int src;
    private int dest;
    private double w;
    private String info;
    private int tag;

    /**
     * Constructor:
     * @param s - source.
     * @param d - destination.
     * @param w - weight.
     */
    public edge(int s, int d, double w) {
        this.src = s;
        this.dest = d;
        this.w = w;
        this.info = "White";
        this.tag = -1;
    }

    /**
     * Deep copy constructor.
     * @param other - EdgeData.
     */
    public edge(EdgeData other){
        edge e = (edge)other;
        this.src = e.src;
        this.dest = e.dest;
        this.w = other.getWeight();
        this.info = other.getInfo();
        this.tag = other.getTag();
    }

    /**
     * The id of the source node of this edge.
     *
     * @return
     */
    public int getSrc() {
        return this.getSrc();
    }

    /**
     * The id of the destination node of this edge
     *
     * @return
     */
    public int getDest() {
        return this.getDest();
    }

    /**
     * @return the weight of this edge (positive value).
     */
    public double getWeight() {
        return this.getWeight();
    }

    /**
     * Returns the remark (meta data) associated with this edge.
     *
     * @return
     */
    public String getInfo() {
        return  this.info;
    }

    /**
     * Allows changing the remark (meta data) associated with this edge.
     *
     * @param s
     */
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     *
     * @return
     */
    public int getTag() {
        return this.tag;
    }

    /**
     * This method allows setting the "tag" value for temporal marking an edge - common
     * practice for marking by algorithms.
     *
     * @param t - the new value of the tag
     */
    public void setTag(int t) {
       this.tag = t;
    }

    /**
     * Override toString method.
     * @return edge as String.
     */
    @Override
    public String toString() {
        return  this.src + "--" + this.w + "-->" + this.dest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        edge edge = (edge) o;
        return src == edge.src &&
                dest == edge.dest &&
                Double.compare(edge.w, w) == 0 &&
                tag == edge.tag &&
                Objects.equals(info, edge.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dest, w, info, tag);
    }
}

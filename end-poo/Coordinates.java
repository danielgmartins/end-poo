/**
 * Class Coordinates is a 2D point system to map every object location and their moves through space.
 * @author Elisio Fernandes A55617 Daniel Martins A73175 Nuno Silva A78879
 * @version 13/4/2017
 */

import java.lang.StringBuilder;
import java.lang.Math;
import java.io.Serializable;

public  class Coordinates implements Serializable{
    private int x;
    private int y;

    /**
     * Constructors of class Coordinates (including empty and copy Constructors).
     *
     */

    public Coordinates(int nx,int ny){
        this.x = nx;
        this.y = ny;
    }

    public Coordinates(){
        this.x = 0;
        this.y = 0;
    }

    public Coordinates(Coordinates ref){
        this.x = ref.getX();
        this.y = ref.getY();
    }


    //Clone

    /**
     * Clone creates an equal object and returns it in order to protect the original reference (Encapsulation)
     * @return A new object with the same instance variables
     *
     */

    public Coordinates clone (){
        Coordinates res = new Coordinates(this);
        return res;
    }

    /**
     * Equals tests if two objects are equal to each other through their instance variables.
     * @param ref    the object to which the object who is executing this method will compare himself to.
     * @return       boolean true or false, if objects are equal or not.
     *
     */

    public boolean equals (Object ref){
        if (ref==this) return true;
        if ((ref==null)|| (ref.getClass()!=this.getClass())) return false;
        Coordinates l = (Coordinates) ref;
        return ((l.getX() == this.x) && (l.getY() == this.y));
    }

    /**
     * toString returns a string cointaining the instance variables of a Coordinates object.
     * @param obj    the Coordinates object we want to apply the ToString method;
     * @return       String containing the objects instance variables
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" X = ");
        sb.append(this.x);
        sb.append(" Y = ");
        sb.append(this.y);
        return sb.toString();
    }

    //Getters e setters

    /**
     * getX returns the x coordinate of the Coordinates object.
     *
     * @return   the x coordinate of the object
     */

    public int getX (){
        return this.x;
    }

    /**
     * getY returns the y coordinate of the Coordinates object.
     *
     * @return   the y coordinate of the object
     */

    public int getY() {
        return this.y;
    }

    /**
     * setx changes the x value of the Coordinates object to the value it receives.
     * @param nx    the new x
     *
     */

    public void setX (int nx){
        this.x = nx;
    }

    /**
     * sety changes the y value of the Coordinates object to the value it receives.
     * @param ny     the new y
     *
     */

    public void setY (int ny){
        this.y = ny;
    }
    /**
     *setCoordinates agreggates setX and setY methods.
     * @param x  the value for the x coordinate
     * @param y  the value for the y coordinate
     *
     */
    public void setCoordinates(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    //Other methods ...

    /**
     * distance calculates the Euclidean distance between two coordinate objects.
     * @param p1     the coordinate object to which we will calculate distance
     * @return       double containing the distance between the two objects.
     *
     */

    public double distance (Coordinates p1){
        double squaredistance = Math.pow(  Math.abs(this.x - p1.getX()) ,2) + Math.pow(Math.abs(this.y - p1.getY()),2);
        return Math.sqrt(squaredistance);
    }
    /**
     * getCoordinates returns an object with the same instance variables.
     *@return    the Coordinates object that has the same instance variables.
     *
     */

    public Coordinates getCoordinates() {
        return this.clone();
    }


}

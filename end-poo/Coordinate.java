/**
* Class Coordinate is a 2D point system to map every object location and their moves through space.
*@author Elisio Fernandes A55617 Daniel Martins A73175 Nuno Silva A78879
*@version 13/4/2017
*/

import java.lang.StringBuilder;
import java.lang.Math;

public  class Coordinate {
    private int x;
    private int y;

    /**
    *Constructors of class Coordinate (including empty and copy Constructors).
    *
    */

    public Coordinate(int nx,int ny){
        this.x = nx;
        this.y = ny;
    }

    public Coordinate(){
        this.x = 0;
        this.y = 0;
    }

    public Coordinate(Coordinate ref){
        this.x = ref.getX();
        this.y = ref.getY();
    }


    //Clone

    /**
    * Clone creates an equal object and returns it in order to protect the original reference (Encapsulation)
    * @return A new object with the same instance variables
    *
    */

    public Coordinate clone (){
        Coordinate res = new Coordinate(this);
        return res;
    }

    /**
    * Equals tests if two objects are equal to each other through their instance variables.
    * @param ref the object to which the object who is executing this method will compare himself to.
    * @return boolean true or false, if objects are equal or not.
    *
    */

    public boolean equals (Object ref){
        if (ref==this) return true;
        if ((ref==null)|| (ref.getClass()!=this.getClass())) return false;
        Coordinate l = (Coordinate) ref;
        return ((l.getX() == this.x) && (l.getY() == this.y));
    }

    /**
    * toString returns a string cointaining the instance variables of a Coordinate object.
    * @param obj the Coordinate object we want to apply the ToString method;
    * @return String containing the objects instance variables
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
     * getx returns the x coordenate of the Coordinate object.
     *
     * @return    the x coordenate of the object
     */

        public int getX (){
            return this.x;
        }

    /**
     * gety returns the y coordenate of the Coordinate object.
     *
     * @return  y  the y coordenate of the object
     */

        public int getY() {
            return this.y;
        }

    /**
     * setx changes the x value of the Coordinate object to the value it receives.
     * @param nx the new x
     *
     */

        public void setX (int nx){
            this.x = nx;
        }

    /**
     * sety changes the y value of the Coordinate object to the value it receives.
     * @param ny the new y
     *
     */

        public void setY (int ny){
            this.y = ny;
        }

        //Other methods ...

        /**
        *
        *
        */

        public double distance (Coordinate p1){
            double squaredistance = Math.pow(this.x + p1.getX(),2) + Math.pow(this.y + p1.getY(),2);
            return Math.sqrt(squaredistance);
        }


}

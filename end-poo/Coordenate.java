/**
* Class Coordenate is a 2D point system to map every object location and their moves through space.
*@author Elisio Fernandes A55617 Daniel Martins A73175 Nuno Silva A78879
*@version 13/4/2017
*/

import java.lang.StringBuilder;

public  class Coordenate {
    private int x;
    private int y;

    /**
    *Constructors of class Coordenate (including empty and copy Constructors).
    *
    */
    public Coordenate(int nx,int ny){
        this.x = nx;
        this.y = ny;
    }

    public Coordenate(){
        this.x = 0;
        this.y = 0;
    }

    public Coordenate(Coordenate ref){
        this.x = ref.getx();
        this.y = ref.gety();
    }


    //Clone

    /**
    * Clone creates an equal object and returns it in order to protect the original reference (Encapsulation)
    * @return A new object with the same instance variables
    *
    */

    public Coordenate clone (){
        Coordenate res = new Coordenate(this);
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
        Coordenate l = (Coordenate) ref;
        return ((l.getx() == this.x) && (l.gety() == this.y));
    }

    /**
    * toString returns a string cointaining the instance variables of a Coordenate object.
    * @param obj the Coordenate object we want to apply the ToString method;
    * @return String containing the objects instance variables
    */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" x = ");
        sb.append(this.x);
        sb.append(" y = ");
        sb.append(this.y);
        return sb.toString();
    }

    //Getters e setters

    /**
     * getx returns the x coordenate of the Coordenate object.
     *
     * @return    the x coordenate of the object
     */

        public int getx (){
            return this.x;
        }

    /**
     * gety returns the y coordenate of the Coordenate object.
     *
     * @return  y  the y coordenate of the object
     */

        public int gety() {
            return this.y;
        }

    /**
     * setx changes the x value of the Coordenate object to the value it receives.
     * @param nx the new x
     *
     */

        public void setx (int nx){
            this.x = nx;
        }

    /**
     * sety changes the y value of the Coordenate object to the value it receives.
     * @param ny the new y
     *
     */

        public void sety (int ny){
            this.y = ny;
        }

}


/**
 * Abstract class Vehicle - PArent class for vehicles with associated methods
 *
 * @author  a55617 Elisio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version 12/04/2017
 */

import java.util.Random;
import java.lang.StringBuilder;

public class Car extends Vehicle{
    private boolean queue;

    /**
     * Empty constructor
     */
    private Car (){
      super();
      this.queue = false;
    }

    /**
     * Constructor with indiviual parameters
     * @param double    Average speed per km
     * @param double    Fare per km
     * @param double    Reliability
     * @param boolean   Availability
     */
    private Car (double averageSpeed, double fare, double reliability, boolean availability){
        super (averageSpeed, fare, reliability, availability);
        this.queue = false;
    }

    /**
     * Constructor with Vehicle parameter
     * @param Vehicle   Vehicle
     */
    private Car (Car c){
        this.averageSpeed = c.getAverageSpeed();
        this.fare = c.getFare();
        this.reliability = c.getReliability();
        this.available = c.getAvailability();
        this.queue = c.getQueue();
    }

    /**
     * Makes copy of the vehicle
     * @return Vehicle  Vehicle
     */
    public Car clone ();

    /**
     * Creates a string of every parameter
     * @return String   Description of car
     */
    public String toString (){
        StringBuilder res = new StringBuilder();

        res.append(super.toString());
        res.append("Queue" + this.queue);

        return res.toString();
    }

    /**
     * Compares vehicles
     * @return boolean   True if equal; False if Different
     */
    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Vehicle v = (Vehicle) o;

        return this.averageSpeed == v.getAverageSpeed()    &&
            this.fare == v.getFare()                       &&
            this.reliability == v.getReliability()         &&
            this.available == v.getAvailability();
    }

    /**
     * Gets vehicle's average speed in km
     * @return double average speed
     */
    public double getAverageSpeed (){
        return this.averageSpeed;
    }

    /**
     * Gets vehicle's fare per km
     * @return double vehicle's fare per km
     */
    public double getFare (){
        return this.fare;
    }

    /**
     * Gets vehicle's reliability
     * @return double vehicle's reliability
     */
    public double getReliability (){
        return this.reliability;
    }

    /**
     * Gets vehicle's availability
     * @return boolean vehicle's availability
     */
    public boolean getAvailability (){
        return this.available;
    }

    /**
     * Changes vehicle's average speed in km
     * @param double average speed
     */
    public void setAverageSpeed (double averageSpeed){
        this.averageSpeed = averageSpeed;
    }

    /**
     * Changes vehicle's fare per km
     * @param double vehicle's fare per km
     */
    public void setFare (double fare){
        this.fare = fare;
    }

    /**
     * Changes vehicle's reliability
     * @param double vehicle's reliability
     */
    public void setReliability (double reliability){
        this.reliability = reliability;
    }

    /**
     * Changes vehicle's availability
     * @param boolean vehicle's availability
     */
    public void setAvailability (boolean availability){
        this.available = availability;
    }
}

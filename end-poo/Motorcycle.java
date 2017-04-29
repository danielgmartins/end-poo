
/**
 * Abstract class Vehicle - PArent class for vehicles with associated methods
 *
 * @author  a55617 Elisio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version 12/04/2017
 */

import java.util.Random;
import java.lang.StringBuilder;

public abstract class Vehicle{
    private double averageSpeed;
    private double fare;
    private double reliability;
    //private boolean queue;
    private boolean available;
    //private Coordinates location;

    /**
     * Empty constructor
     */
    private Vehicle (){
      this.averageSpeed = -1;
      this.fare = -1;
      this.reliability = -1;
      this.available = false;
    }

    /**
     * Constructor with indiviual parameters
     * @param double    Average speed per km
     * @param double    Fare per km
     * @param double    Reliability
     * @param boolean   Availability
     */
    private Vehicle (double averageSpeed, double fare, double reliability, boolean availability){
        this.setAverageSpeed(averageSpeed);
        this.setFare(fare);
        this.setReliability(reliability);
        this.setAvailability(availability);
    }

    /**
     * Constructor with Vehicle parameter
     * @param Vehicle   Vehicle
     */
    private Vehicle (Vehicle v){
        this.averageSpeed = v.getAverageSpeed();
        this.fare = v.getFare();
        this.reliability = v.getReliability();
        this.available = v.getAvailability();
    }

    /**
     * Makes copy of the vehicle
     * @return Vehicle  Vehicle
     */
    public abstract Vehicle clone ();

    /**
     * Creates a string of every parameter
     * @return String   Description of vehicle
     */
    public String toString (){
        StringBuilder res = new StringBuilder("Vehicle: Average speed");

        res.append(this.averageSpeed);
        res.append(" Fare: ");
        res.append(this.fare);
        res.append(" Reliability: ");
        res.append(this.reliability);
        res.append(" Available: ");
        res.append(this.available);

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

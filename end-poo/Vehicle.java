
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
    private boolean available;
    private boolean queue;
    private Coordinates location;

    /**
     * Empty constructor
     */
    public Vehicle (){
      this.averageSpeed = -1;
      this.fare = -1;
      this.reliability = -1;
      this.available = false;
      this.queue = false;
      location = new Coordinates();
    }

    /**
     * Constructor with indiviual parameters
     * @param averageSpeed      Average speed per km
     * @param fare              Fare per km
     * @param reliability       Reliability
     * @param availability      Availability from 0 to 1
     * @param queue             Queue setting
     * @param x                 x coordinate
     * @param y                 y coordinate
     */
    public Vehicle (double averageSpeed, double fare, double reliability, boolean availability, boolean queue, double x, double y){
        this.averageSpeed = averageSpeed;
        this.setFare(fare);
        this.reliability = reliability;
        this.setAvailability(availability);
        this.setQueueValue(queue);
        this.coordinates = new Coordinates(x, y);
    }

    /**
     * Constructor with Vehicle parameter
     * @param Vehicle   Vehicle
     */
    public Vehicle (Vehicle v){
        this.averageSpeed = v.getAverageSpeed();
        this.fare = v.getFare();
        this.reliability = v.getReliability();
        this.available = v.getAvailability();
        this.queue = v.getQueueValue();
        this.coordinates = v.getCoordinates();
    }

    /**
     * Makes copy of the vehicle
     * @return  Vehicle  Vehicle
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
        res.append(" Queue: ");
        res.append(this.queue);
        res.append(this.coordinates.toString());

        return res.toString();
    }

    /**
     * Compares vehicles
     * @return boolean   True if equal. False if Different
     */
    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Vehicle v = (Vehicle) o;

        return this.averageSpeed == v.getAverageSpeed()    &&
            this.fare == v.getFare()                       &&
            this.reliability == v.getReliability()         &&
            this.available == v.getAvailability()          &&
            this.queue == v.getQueueValue();
            this.location.getCoordinates().equals(v.getCoordinates());
    }

    /**
     * Gets vehicle's average speed in km
     * @return double   Average speed
     */
    public double getAverageSpeed (){
        return this.averageSpeed;
    }

    /**
     * Gets vehicle's fare per km
     * @return double   Vehicle's fare per km
     */
    public double getFare (){
        return this.fare;
    }

    /**
     * Gets vehicle's reliability
     * @return double   Vehicle's reliability
     */
    public double getReliability (){
        return this.reliability;
    }

    /**
     * Gets vehicle's availability
     * @return boolean  Vehicle's availability. True if available; False if unavailable
     */
    public boolean getAvailability (){
        return this.available;
    }

    /**
     * Gets vehicle's queue setting
     * @return boolean  Vehicle's queue value. True if supports queue; False if does not support queue
     */
    public boolean getQueueValue (){
        return this.queue;
    }

    /**
     * Gets vehicle's location
     * @return Coordinates  Vehicle's queue value. True if supports queue; False if does not support queue
     */
    public Coordinates getLocation (){
        return this.location.getCoordinates();
    }

    /**
     * Changes vehicle's average speed in km
     * @param averageSpeed    Average speed
     */
    public void setAverageSpeed (double averageSpeed){
        this.averageSpeed = averageSpeed;
    }

    /**
     * Changes vehicle's fare per km
     * @param fare    Vehicle's fare per km
     */
    public void setFare (double fare){
        this.fare = fare;
    }

    /**
     * Updates vehicle's reliability
     * @param reliability    Vehicle's reliability
     */
    public void updateReliability (double reliability){
        Random rand = new Random();
        this.reliability = rand.nextDouble();
    }

    /**
     * Changes vehicle's availability
     * @param availability   Vehicle's availability. True if available; False if not
     */
    public void setAvailability (boolean availability){
        this.available = availability;
    }

    /**
     * Changes vehicle's queue value
     * @param queueValue   Vehicle's queue boolean. True if supports queue. False it does not support queue
     */
    public void setQueueValue (boolean queueValue){
        this.queue = queueValue;
    }

    /**
     * Changes vehicle's coordinates
     * @param coordinates   Vehicle's queue boolean. True if supports queue. False it does not support queue
     */
    public void updateLocation (Coordinates coordinates){
        this.location.setCoordinates(coordinates);
    }
}

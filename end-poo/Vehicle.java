/**
 * Abstract class Vehicle - Parent class for vehicles with associated methods
 *
 * @author  a55617 Elisio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version 12/04/2017
 */

import java.util.Random;
import java.util.Queue;
import java.lang.StringBuilder;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.*;
// import java.util.stream.Collectors.toList;
import java.util.stream.Collectors;
import java.io.Serializable;

public abstract class Vehicle extends Exception implements Comparable<Vehicle>, Serializable {
    private String licensePlate;
    private double averageSpeed;
    private double kmsTotal;
    private double fare;
    private double reliability;
    private boolean available;
    private int seats;
    private Coordinates location;


    /**
     * Empty constructor
     */
    private Vehicle (){
        this("N/A", 0);
    }

    /**
     * Constructor with only licensePlate and seats as parameter.
     * Fare is set to 3 by default. Reliability is set to 1 by default
     * @param licensePlate  lincese plate of the vehicle
     * @param seats         number of seats available in the vehicle
     */
    public Vehicle(String licensePlate, int seats){
        this(licensePlate, 0.0, 0.0, 3.0, 1.0, true, seats, 0, 0);
    }

    /**
     * Constructor with indiviual parameters
     * @param licensePlate      License plate
     * @param averageSpeed      Average speed per km
     * @param kmsTotal          Total of kms
     * @param fare              Fare per kms
     * @param reliability       Reliability
     * @param availability      Availability from 0 to 1
     * @param coordinates       location
     */
    public Vehicle (String licensePlate, double averageSpeed, double kmsTotal, double fare, double reliability, boolean availability, int seats, Coordinates location){
        this(licensePlate, averageSpeed, kmsTotal, fare, reliability, availability, seats, location.getX(), location.getY());
    }


    /**
     * Constructor with indiviual parameters
     * @param licensePlate      License plate
     * @param averageSpeed      Average speed per km
     * @param kmsTotal          Total of kms
     * @param fare              Fare per kms
     * @param reliability       Reliability
     * @param availability      Availability from 0 to 1
     * @param x                 x coordinate
     * @param y                 y coordinate
     */
    public Vehicle (String licensePlate, double averageSpeed, double kmsTotal, double fare, double reliability, boolean availability, int seats, int x, int y){
        this.setLicensePlate(licensePlate);
        this.setAverageSpeed(averageSpeed);
        this.setKmsTotal(kmsTotal);
        this.setFare(fare);
        this.setReliability(reliability);
        this.setAvailability(availability);
        this.setSeats(seats);
        this.setLocation(x, y);
    }

    /**
     * Constructor with Vehicle parameter
     * @param Vehicle   Vehicle
     */
    public Vehicle (Vehicle v){
        this(v.getLicensePlate(),
             v.getAverageSpeed(),
             v.getKmsTotal(),
             v.getFare(),
             v.getReliability(),
             v.getAvailability(),
             v.getSeats(),
             v.getLocation()     );
    }

    /**
     * Makes copy of the vehicle
     * @return  Vehicle copy
     */
    public abstract Vehicle clone ();

    /**
     * Creates a string of every parameter
     * @return   Description of vehicle
     */
    public String toString (){
        StringBuilder res = new StringBuilder("License Plate: ");

        res.append(this.licensePlate);
        res.append("\nAverage speed: ");
        res.append(this.averageSpeed);
        res.append("\nTotal Kms:");
        res.append(this.kmsTotal);
        res.append("\nFare: ");
        res.append(this.fare);
        res.append("\nReliability: ");
        res.append(this.reliability);
        res.append("\nAvailable: ");
        res.append(this.available);
        res.append("\nSeats: ");
        res.append(this.seats);
        res.append(this.location.toString());

        return res.toString();
    }

    /**
     * Compares vehicles
     * @return   True if equal. False if Different
     */
    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Vehicle v = (Vehicle) o;

        return this.licensePlate.equals(v.getLicensePlate())                &&
               this.averageSpeed == v.getAverageSpeed()                     &&
               this.kmsTotal == v.getKmsTotal()                             &&
               this.fare == v.getFare()                                     &&
               this.reliability == v.getReliability()                       &&
               this.available == v.getAvailability()                        &&
               this.seats == v.getSeats()                                   &&
               this.location.equals(v.getLocation())                        ;
    }

    // ----------   GETTERS    ---------- //

    /**
     * Gets vehicle's license plate
     * @return   Average speed
     */
    public String getLicensePlate (){
        return this.licensePlate;
    }

    /**
     * Gets vehicle's average speed in km
     * @return   Average speed
     */
    public double getAverageSpeed (){
        return this.averageSpeed;
    }

    /**
     * Gets total number of kilometers made by the car
     * @return returns total number of kilometeres
     */
    public double getKmsTotal (){
        return this.kmsTotal;
    }
    /**
     * Gets vehicle's fare per km
     * @return   Vehicle's fare per km
     */
    public double getFare (){
        return this.fare;
    }

    /**
     * Gets vehicle's reliability
     * @return   Vehicle's reliability
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
     * Gets vehicle's seat number
     * @return int  Vehicle's number of seats
     */
    public int getSeats (){
        return this.seats;
    }

    /**
     * Gets vehicle's location
     * @return  Vehicle's queue value. True if supports queue. False if does not support queue
     */
    public Coordinates getLocation (){
        return this.location.clone();
    }


    //  ----------  SETTERS   ---------- //

    /**
     * Gets vehicle's license plate
     * @param licensePlate  License plate
     */
    public void setLicensePlate (String licensePlate){
        this.licensePlate = licensePlate;
    }

    /**
     * Changes vehicle's average speed in km
     * @param averageSpeed  Average speed
     */
    public void setAverageSpeed (double averageSpeed){
        this.averageSpeed = averageSpeed;
    }

    public void setKmsTotal (double kmsTotal){
        this.kmsTotal = kmsTotal;
    }
    /**
     * Changes vehicle's fare per km
     * @param fare  Vehicle's fare per km
     */
    public void setFare (double fare){
        this.fare = fare;
    }

    /**
     * Sets car's reliability manually
     * @param reliability   Vehicle's reliability
     */
    public void setReliability (double reliability) {
        this.reliability = reliability;
    }



    /**
     * Sets car's reliability bearing in mind the randomability of weather, traffic conditions and the fact that a car is pretty unreliable itself
     */
    public abstract void setReliability ();

    /**
     * Changes vehicle's availability
     * @param availability   Vehicle's availability. True if available; False if not
     */
    public void setAvailability (boolean availability){
        this.available = availability;
    }

    /**
     * Changes vehicle's seat number
     * @param availability   Vehicle's availability. True if available; False if not
     */
    public void setSeats (int seats){
        this.seats = seats;
    }

    /**
     * Changes vehicle's location
     * @param x   New x coordinate
     * @param y   New y coordinate
     */
    public void setLocation (int x, int y){
        this.location = new Coordinates(x, y);
    }

    /**
     * Changes vehicle's location with object Coordinate
     * @param location   New coordinates
     */
    public void setLocation (Coordinates location){
        this.location = location.clone();
    }

    //  ----------  OTHER METHODS   ---------- //

    /**
     * Compares vehicles with natural order
     * @param V Vehicle used for comparison
     * @return  Returns -1 if lower, 0 if equal and 1 if higher
     */
    public int compareTo (Vehicle v){
        return this.licensePlate.compareTo(v.getLicensePlate());
    }

}

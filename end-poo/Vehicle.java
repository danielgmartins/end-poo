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
import java.util.stream. Stream;
import java.util.*;
// import java.util.stream.Collectors.toList;
import java.util.stream.Collectors;
import java.io.Serializable;

public abstract class Vehicle extends Exception implements Comparable<Vehicle> {
    private String licensePlate;
    private double averageSpeed;
    private double kmsTotal;
    private double fare;
    private double reliability;
    private boolean available;
    private int seats;
    private Coordinates location;
    private boolean queue;
    private Queue<Trip> queueList;


    /**
     * Empty constructor
     */
    private Vehicle (){
        this.licensePlate = "na";
        this.averageSpeed = -1;
        this.kmsTotal = -1;
        this.fare = -1;
        this.reliability = -1;
        this.available = false;
        this.seats = -1;
        this.location = new Coordinates();
        this.queue = false;
        this.queueList = new LinkedList<Trip>();
    }

    /**
     * Constructor with indiviual parameters
     * @param licensePlate      License plate
     * @param averageSpeed      Average speed per km
     * @param kmsTotal          Total of kms
     * @param fare              Fare per kms
     * @param reliability       Reliability
     * @param availability      Availability from 0 to 1
     * @param queue             Queue setting
     * @param x                 x coordinate
     * @param y                 y coordinate
     */
    public Vehicle (String licensePlate, double averageSpeed, double kmsTotal, double fare, double reliability, boolean availability, int seats, int x, int y, boolean queue, List<Trip> queueList){
        this.setLicensePlate(licensePlate);
        this.setAverageSpeed(averageSpeed);
        this.setKmsTotal(kmsTotal);
        this.setFare(fare);
        this.reliability = reliability;
        this.setAvailability(availability);
        this.setSeats(seats);
        this.setLocation(x, y);
        this.setQueueValue(queue);
        if (this.queue){
            this.setQueueList(queueList);
        }
        else this.queueList = new LinkedList<Trip>();

    }

    /**
     * Constructor with indiviual parameters
     * @param licensePlate      License plate
     * @param averageSpeed      Average speed per km
     * @param kmsTotal          Total of kms
     * @param fare              Fare per kms
     * @param availability      Availability from 0 to 1
     * @param queue             Queue setting
     * @param x                 x coordinate
     * @param y                 y coordinate
     */
    public Vehicle (String licensePlate, double averageSpeed, double kmsTotal, double fare, boolean availability, int seats, int x, int y, boolean queue, List<Trip> queueList){
        this.setLicensePlate(licensePlate);
        this.setAverageSpeed(averageSpeed);
        this.setKmsTotal(kmsTotal);
        this.setFare(fare);
        this.setReliability(1.0);
        this.setAvailability(availability);
        this.setSeats(seats);
        this.setLocation(x, y);
        this.setQueueValue(queue);
        if (this.queue){
            this.setQueueList(queueList);
        }
        else this.queueList = new LinkedList<Trip>();

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
             v.location.getX(),
             v.location.getY(),
             v.getQueueValue(),
             v.getQueueList () );
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
        res.append("\nQueue: ");
        res.append(this.queue);
        for(Trip t: this.queueList)
            res.append(t.toString()).append("; ");

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

        return this.licensePlate == v.getLicensePlate()                     &&
               this.averageSpeed == v.getAverageSpeed()                     &&
               this.kmsTotal == v.getKmsTotal()                             &&
               this.fare == v.getFare()                                     &&
               this.reliability == v.getReliability()                       &&
               this.available == v.getAvailability()                        &&
               this.seats == v.getSeats()                                   &&
               this.location.equals(v.getLocation())                        &&
               this.queue == v.getQueueValue()                              &&
               this.getQueueList().equals(v.getQueueList());

               // && this.getQueueList().equals(c.getQueueList()
    }

    /**
     * Compares vehicles with natural order
     * @param V Vehicle used for comparison
     * @return  Returns -1 if lower, 0 if equal and 1 if higher
     */
    public int compareTo (Vehicle v){
        return this.licensePlate.compareTo(v.getLicensePlate());
    }

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

    /**
     * Gets vehicle's queue setting
     * @return boolean  Vehicle's queue value. True if supports queue; False if does not support queue
     */
    public boolean getQueueValue (){
        return this.queue;
    }

    /**
     * Gets vehicle's queue list's copy
     * @return   List with queue copy
     */
    public List<Trip> getQueueList (){
        if (this.getQueueValue())
            return (List<Trip>) this.queueList.stream().map(Trip::clone)
                                 .collect(Collectors.toList());
        return new LinkedList<Trip>();
    }

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
     * Sets vehicle's reliability
     * @param reliability   Vehicle's reliability
     */
    public void setReliability(double reliability){
        this.reliability = reliability;
    }

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
     * @param Coordinates   New coordinates
     */
    public void setLocation (int x, int y){
        this.location = new Coordinates(x, y);
    }

    /**
     * Changes vehicle's queue value
     * @param queueValue   Vehicle's queue boolean. True if supports queue. False it does not support queue
     */
    public void setQueueValue (boolean queueValue){
        this.queue = queueValue;
    }

    /**
     * Changes vehicle's queue value
     * @param queueValue   Vehicle's queue boolean. True if supports queue. False it does not support queue
     */
    public void setQueueList (List<Trip> queueListIn){
        this.queueList = (Queue<Trip>) queueListIn.stream()
                                    .map(Trip::clone).collect(Collectors.toList() );
    }


// nao e necesssario pois tenho o metodo da propria classe Coordinates
    // /**
    //  * Changes vehicle's coordinates
    //  * @param coordinates   Vehicle's queue boolean. True if supports queue. False it does not support queue
    //  */
    // public void setLocation (Coordinates coordinates){
    //     this.location.setCoordinates(coordinates);
    // }
}

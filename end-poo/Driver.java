/**
 * Class Driver - Inherits class User
 *
 * @author a55617 Elísio Fernandes, a73175 Daniel Martins, aXXXXX Nuno Silva
 * @version 14/04/2017
 */

import java.util.List;
import java.util.LinkedList;
import java.util.StringBuilder;

public class Driver extends User {

    private int performance;
    private int classification;
    private List<Trip> tripHistory;
    private int kmsDriven;
    private boolean availability;
    
    /**
     * Empty constructor
     */
    private Driver (){
        this(0,"N/A",null,null,"N/A","N/A");
    }

    /**
     * Constructor with every parameter for super class User
     * @param id        User id
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     */
    public Driver (int id, String name, Address address, LocalDate birthday, String email, String password){
        this(id, name, address, birthday, email, password, 100, 100, new LinkedList<Trip>(), 0, true);
    }

    /**
     * Constructor with every parameter for class Driver
     * @param id             User id
     * @param name           User name
     * @param Address        User address
     * @param birthday       User birthday
     * @param email          User email
     * @param password       User password
     * @param performance    Driver performance
     * @param classification Driver classification
     * @param tripHistory    Driver trip history
     * @param kmsDriven      Driver kms driven
     * @param availability   Driver availability
     */
    public Driver (int id, String name, Address address, LocalDate birthday, String email, String password, int performance, int classification, LinkedList<Trip> tripHistory, int kmsDriven, boolean availability){
        super(id, name, address, birthday, email, password);
        this.setPerformance(performance);
        this.setClassification(classification);
        this.setTripHistory(tripHistory);
        this.setKmsDriven(kmsDriven);
        this.setAvailability(availability);
    }

    /**
     * Constructor that constructs form another instance of Driver
     * @param driver Instance driver to build another instance from
     */
    public Driver (Driver driver){
        super((User) driver);
        this.setPerformance(driver.getPerformance());
        this.setClassification(driver.getClassification());
        this.setTripHistory(driver.getTripHistory());
        this.setKmsDriven(driver.getKmsDriven());
        this.setAvailability(driver.getAvailability());
    }

    /**
     * Method that clones this instace of Driver
     * @return Returns copy of this instance of Driver
     */
    public Driver clone(){
        return new Driver(this);
    }

    /**
     * toString method that returns Driver info
     * @return Returns string with Driver info
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("Driver(");
        sb.append(super.toString());
        sb.append(", performance: ");
        sb.append(this.performance);
        sb.append(", classification: ");
        sb.append(this.classification);
        sb.append(", tripHistory( \n");
        // Uses forEach iterator to add each trip in tripHistory to the string builder 
        this.tripHistory.forEach( (Trip trip) -> {sb.append(trip.toString());} );
        sb.append(")\n");
        sb.append(", kmsDriven: ");
        sb.append(this.kmsDriven);
        sb.append(", availability: ");
        sb.append(this.availability);
        sb.append(")\n");

        return sb.toString();
    }

    /**
     * Method that clones this instace Driver
     * @return Returns new instance of Driver equal to this instance of Driver
     */
    public boolean equals(Object o){
        if(this == o) return true;
        if(o != null && o.getClass() != this.getClass()) return false;

        Driver aux = (Driver) o;
        return super.equals((User) aux)                         &&
               this.performance == aux.getPerformance()         &&
               this.classification == aux.getClassification()   &&
               this.tripHistory.equals(aux.getTripHistory())    &&
               this.kmsDriven == aux.getKmsDriven()             &&
               this.availability == aux.getAvailability()       ;
    }

    // Getters

    /**
     * Gets Driver performance
     * @return Returns int with this instance Driver performance
     */
    public int getPerformance(){
        return this.performance;
    }

    /**
     * Gets Driver classification
     * @return Rrturns int with this instance of Driver's classification
     */
    public int getClassification(){
        return this.classification;
    }

    /**
     * Gets Driver trip history
     * @return Returns List with this intance Driver's Trip History
     */
    public LinkedList<Trip> getTripHistory(){
        return this.tripHistory;
    }

    /**
     * Gets copy of this instance Driver's trip history
     * @return Returns List that's a copy of this instance of Driver's Trip History
     */
    public LinkedList<Trip> getTripHistoryCopy(){
        List<Trip> aux = new LinkedList<Trip>();
        
        this.tripHistory.forEach((Trip) trip -> {aux.add(trip.clone());});

        return aux;
    }

    /**
     * Gets kms driven by this instance of Driver
     * @return Returns int with kms driven by this instance of Driver
     */
    public int getKmsDriven(){
        return this.kmsDriven;
    }

    /**
     * Gets Diver availability
     * @returns Boolean with driver's availability. True if available, False otherwise
     */
    public boolean getAvailability(){
        return this.availability;
    }

    // Setters

    /**
     * Sets this Driver perfomance
     * @param perfomance Int with new performance for this Drivers
     */
    public void setPerformance(int performance){
        this.performance = performance;
    }

    /**
     * Sets this Driver classification
     * @param perfomance Int with new classification for this Drivers
     */
    public void setClassification(int classification){
        this.classification = classification;
    }

    /**
     * Sets new trip history, by replacing it
     * @param history New trip hisotory to update to
     */
    public void setTripHistory(LinkedList<Trip> tripHistory){
        List<Trip> this.location = new LinkedList<List>();

        history.forEach( (Trip) trip -> {this.location.add(trip.clone());} );
    }

    /**
     * Sets kms driven this Driver
     * @param kms Int with new amount of kms driven by this Driver
     */
    public void setKmsDriven(int kms){
        this.kmsDriven = kms;
    }

    /**
     * Sets availability for this Driver
     * @param availability Boolean with new availability for this instance of Driver
     */
    public void setAvailability(boolean availability){
        this.availability = availability;
    }

    // Other class methods

    /**
     * Adds new trip to trip history
     * @param trip Trip to be added to trip history
     */
    public void addTripToHistory (Trip trip){
        this.location.addFirst(trip.clone());
    }

    /**
     * Adds kms to kms driven by this Driver
     * @param kms Int with number of kms to add to kms driven by this instance of Driver
     */
    public void addKmsDriven(int kms){
        this.kmsDriven += kms;
    }

    /**
     * Switches availability for this instance of Driver.
     * If availability is True, then it switches to false and vice-versa
     */
    public void switchAvailability(){
        this.availability = !this.availability;
    }



}
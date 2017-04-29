/**
 * Class Driver - Inherits class User
 *
 * @author a55617 Elísio Fernandes, a73175 Daniel Martins, aXXXXX Nuno Silva
 * @version 14/04/2017
 */

import java.util.List;
import java.util.LinkedList;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.io.Serializable;

public class Driver extends User implements Serializable {

    private int performance;
    private int classification;
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
        this(id, name, address, birthday, email, password, new LinkedList<Trip>(), 100, 100, 0, true);
    }

    /**
     * Constructor with every parameter for class Driver
     * @param id             User id
     * @param name           User name
     * @param Address        User address
     * @param birthday       User birthday
     * @param email          User email
     * @param password       User password
     * @param tripHistory    User trip history
     * @param performance    Driver performance
     * @param classification Driver classification
     * @param kmsDriven      Driver kms driven
     * @param availability   Driver availability
     */
    public Driver (int id, String name, Address address, LocalDate birthday, String email, String password, LinkedList<Trip> tripHistory, int performance, int classification, int kmsDriven, boolean availability){
        super(id, name, address, birthday, email, password, tripHistory);
        this.setPerformance(performance);
        this.setClassification(classification);
        this.setKmsDriven(kmsDriven);
        this.setAvailability(availability);
    }

    /**
     * Constructor that constructs form another instance of Driver
     * @param driver Instance driver to build another instance from
     */
    public Driver (Driver driver){
        super(driver);
        this.setPerformance(driver.getPerformance());
        this.setClassification(driver.getClassification());
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
        StringBuilder sb = new StringBuilder("Driver ---\n");
        sb.append(super.toString());
        sb.append("\nPerformance: ");
        sb.append(this.performance);
        sb.append("\n Classification: ");
        sb.append(this.classification);
        sb.append("\n Kms Driven: ");
        sb.append(this.kmsDriven);
        sb.append("\n Availability: ");
        sb.append(this.availability);
        sb.append("\n---\n");

        return sb.toString();
    }

    /**
     * Equals method that compares this intance of driver to another object
     * @param o Object to compare with this instance of client
     * @return Returns true if both objects are equal, false otherwise
     */
    public boolean equals(Object o){
        if(this == o) return true;
        if(o != null && o.getClass() != this.getClass()) return false;

        Driver aux = (Driver) o;
        return super.equals(aux)                                &&
               this.performance == aux.getPerformance()         &&
               this.classification == aux.getClassification()   &&
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

    //    ----------    Instance Methods    ----------    //

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
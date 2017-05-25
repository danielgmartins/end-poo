/**
 * Class Client - Inherits class User
 *
 * @author a55617 Elísio Fernandes, a73175 Daniel Martins, aXXXXX Nuno Silva
 * @version 13/04/2017
 */

import java.util.List;
import java.util.LinkedList;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.io.Serializable;

public class Client extends User implements Serializable {

    private Coordinates location;

    /**
     * Empty Contructor
     */
    private Client (){
        this("N/A",null,null,"N/A","N/A");
    }

    /**
     * Contructor with every variable for Client User except for location and history of trips.
     * Starts Client with empty trip history, and location is set to a default location
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     */
    public Client (String name, Address address, LocalDate birthday, String email, String password){
        this(name, address, birthday, email, password, new LinkedList<Integer>(), 0, new Coordinates());
    }

    /**
     * Contructor with every variable for Client User except for location and history of trips. And Client birthday is set with day, month, year with independant parameters parameters.
     * Starts Client with empty trip history, and location is set to a default location
     * @param name      User name
     * @param Address   User address
     * @param day       User birthday day
     * @param month     User birthday month
     * @param year      User birthday year
     * @param email     User email
     * @param password  User password
     */
    public Client (String name, Address address, int day, int month, int year, String email, String password){
        this(name, address, LocalDate.of(year, month, day), email, password, new LinkedList<Integer>(), 0, new Coordinates());
    }

    /**
     * Contructor with every variable for Client except for trip history.
     * Starts Client with empty trip history
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     * @param location  Client location
     */
    public Client (String name, Address address, LocalDate birthday, String email, String password, Coordinates location){
        this(name, address, birthday, email, password, new LinkedList<Integer>(), 0, location);
    }

    /**
     * Contructor with every variable for Client User except for history of trips. And Client birthday is set with day, month, year with independant parameters parameters.
     * Starts Client with empty trip history, and location is set to a default location
     * @param name      User name
     * @param Address   User address
     * @param day       User birthday day
     * @param month     User birthday month
     * @param year      User birthday year
     * @param email     User email
     * @param password  User password
     * @param location  Client location
     */
    public Client (String name, Address address, int day, int month, int year, String email, String password, Coordinates location){
        this(name, address, LocalDate.of(year, month, day), email, password, new LinkedList<Integer>(), 0, location);
    }

    /**
     * Contructor with every variable for Client.
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     * @param history   User trip history
     * @param location  Client location
     */
    public Client (String name, Address address, LocalDate birthday, String email, String password, LinkedList<Integer> history, double totalTripCost, Coordinates location){
        super(name, address, birthday, email, password, history, totalTripCost);
        this.setLocation(location);
    }

    /**
     * Contructor with every variable for Client.
     * @param Client Client instance to be used for creating new instance of Client
     */
    public Client (Client client){
        super(client);
        this.setLocation(client.getLocation());
    }

    /**
     * Method that clones this instace Client
     * @return Returns new instance of Client equal to this instance of Client
     */
    public Client clone (){
        return new Client(this);
    }

    /**
     * toString method that returns string of this client instance information
     * @return String with this client instance information
     */
    public String toString (){
        StringBuilder sb = new StringBuilder("\nClient ---\n");
        sb.append(super.toString());
        sb.append("\n");
        sb.append("Location: ");
        sb.append(this.location.getX());
        sb.append(", ");
        sb.append(this.location.getY());
        sb.append("\n---\n");

        return sb.toString();
    }

    /**
     * Equals method that compares this intance of client to another object
     * @param o Object to compare with this instance of client
     * @return Returns true if both objects are equal, false otherwise
     */
    public boolean equals (Object o){
        if(this == o) return true;
        if(o != null && o.getClass() != this.getClass()) return false;

        Client aux = (Client) o;
        return super.equals(aux)                            &&
               this.location.equals(aux.getLocation())      ;
    }

    // Getters

    /**
     * Gets client's location Coordinates
     * @return Coordinates with Client location
     */
    public Coordinates getLocation (){
        return this.location.clone();
    }

    // Setters

    /**
     * Sets new location to this instance of Client
     * @param location New location to update to
     */
    public void setLocation (Coordinates location){
        this.location = new Coordinates(location);
    }

    /**
     * Sets new location to this instance of Client using x, y
     * @param x Int with x coordinate
     * @param y Int with y coordinate
     */
    public void setLocation (int x, int y){
        this.location = new Coordinates(x,y);
    }

    //    ----------    Other Methods    ----------    //

    /**
     * Adds new trip to trip history
     * @param trip Trip to be added to trip history
     */
    public void addTripToHistory (Trip trip){
        super.addTripToHistory(trip);

        this.setLocation(trip.getDestination());
    }
}
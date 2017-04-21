/**
 * Class Client - Inherits class User
 *
 * @author a55617 Elísio Fernandes, a73175 Daniel Martins, aXXXXX Nuno Silva
 * @version 13/04/2017
 */

import java.util.List;
import java.util.LinkedList;
import java.util.StringBuilder;

public class Client extends User {

    private Coordinate location;

    /**
     * Empty Contructor
     */
    private Client (){
        this(0,"N/A",null,null,"N/A","N/A");
    }

    /**
     * Contructor with every variable for Cliente User except for location and history of trips.
     * Starts Client with empty trip history, and location is set to a default location
     * @param id        User id
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     */
    public Cliente (int id, String name, Address address, LocalDate birthday, String email, String password){
        this(id, name, address, birthday, email, password, new LinkedList<Trip>(), new Coordinate());
    }

    /**
     * Contructor with every variable for Cliente except for trip history.
     * Starts Cliente with empty trip history
     * @param id        User id
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     * @param location  Client location
     */
    public Cliente (int id, String name, Address address, LocalDate birthday, String email, String password, Coordinate location){
        super(id, name, address, birthday, email, password, new LinkedList<Trip>(), location);
    }

    /**
     * Contructor with every variable for Cliente.
     * @param id        User id
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     * @param history   User trip history
     * @param location  Client location
     */
    public Cliente (int id, String name, Address address, LocalDate birthday, String email, String password, LinkedList<Viagem>() history, Coordinate location){
        super(id, name, address, birthday, email, password, history);
        this.setLocation(location);
    }

    /**
     * Contructor with every variable for Cliente.
     * @param Client Client instance to be used for creating new instance of Client
     */
    public Cliente (Client client){
        super(client);
        this.setLocation(client.getLocation());
    }

    /**
     * Method that clones this instace Client
     * @return Returns new instance of Client equal to this instance of Client
     */
    public Client clone (){
        return new Client(this)
    }

    /**
     * toString method that returns string of this client instance information
     * @return String with this client instance information
     */
    public String toString (){
        StringBuilder sb = new StringBuilder("Client (");
        sb.append(super.toString());
        sb.append(", ");
        sb.append("location: ");
        sb.append(this.location.toString());
        sb.append("))\n");

        return sb.toString();
    }

    /**
     * Equals method that compares this intance of client to another object
     * @param o Object to compare with this instance of client
     * @return Returns true if both objects are equal, false otherwise
     */
    public boolean equals (Object o){
        if(this == o) return True
        if(o != null && o.getClass() != this.getClass()) return false

        Client aux = (Client) o;
        return super.equals(aux)                            &&
               this.location.equals(aux.getLocation())      ;
    }

    // Getters

    /**
     * Gets client's location Coordinate
     * @return Coordinate with Client location
     */
    public Coordinate getLocation (){
        return this.location;
    }

    // Setters

    /**
     * Sets new location to this instance of Client
     * @param location New location to update to
     */
    public void setLocation (Coordinate location){
        this.location = new Coordinate(location);
    }

    //    ----------    Instance Methods    ----------    //

}
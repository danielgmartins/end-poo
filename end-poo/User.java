
/**
 * Abstract class User - Parent class for users.
 *
 * @author a55617 Elísio Fernandes, a73175 Daniel Martins, aXXXXX Nuno Silva
 * @version 12/04/2017
 */

import java.util.List;
import java.util.LinkedList;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.io.Serializable;

public abstract class User implements Serializable {
    private int id;
    private String name;
    private Address address;
    private LocalDate birthday;
    private String email;
    private String password;
    private List<Trip> tripHistory;

    /**
     * Empty contructor for User
     */
    private User (){
        this(0,"N/A",null,null,"N/A","N/A");
    }

    /**
     * Contructor with all the arguments except trip history
     * @param id        User id
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     */
    public User (int id, String name, Address address, LocalDate birthday, String email, String password){
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setBirthday(birthday);
        this.setEmail(email);
        this.setPassword(password);
        this.setTripHistory(new LinkedList<Trip>());
    }

    /**
     * Contructor with all the arguments
     * @param id        User id
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     * @param tripHistory    User trip history
     */
    public User (int id, String name, Address address, LocalDate birthday, String email, String password, List<Trip> history){
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setBirthday(birthday);
        this.setEmail(email);
        this.setPassword(password);
        this.setTripHistory(history);
    }

    /**
     * Constructor using object User as reference
     * @param user  User object to use as reference
     */
    public User (User user){
        this(user.getId(),
             user.getName(), 
             user.getAddress(), 
             user.getBirthday(), 
             user.getEmail(), 
             user.getPassword(),
             user.getTripHistory()
             );
    }

    /**
     * Abstract method for cloning objects with super class User.
     */
    public abstract User clone ();

    /**
     * Writes object as String
     * @return String of this User instance
     */
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ");
        sb.append(id);
        sb.append("\n");
        sb.append("Name: ");
        sb.append(name);
        sb.append("\n");
        sb.append("Address: ");
        sb.append(address.getCity());
        sb.append(", ");
        sb.append(address.getCountry());
        sb.append("\n");
        sb.append("Birthday: ");
        sb.append(birthday.toString());
        sb.append("\n");
        sb.append("Email: ");
        sb.append(email);
        sb.append("\n");
        // sb.append("password: ");
        // sb.append(password);
        sb.append("\n TripHistory: \n");
        // Uses forEach iterator to add each trip in tripHistory to the string builder 
        this.tripHistory.forEach( (Trip trip) -> {sb.append(trip.toString());} );
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Comprares an object to this instance of User
     * @param o Object being compared against the instance 
     * @retrun  True if both objects are equal in all parameters, false otherwise
     */
    public boolean equals (Object o){
        if(this == o) return true;
        if(o != null && o.getClass() != this.getClass()) return false;

        User user = (User) o;
        return this.id == user.getId()                          &&
               this.name.equals(user.getName())                 &&
               this.address.equals(user.getAddress())           &&
               this.birthday.equals(user.getBirthday())         &&
               this.email.equals(user.getEmail())               &&
               this.password.equals(user.getPassword())         &&
               this.tripHistory.equals(user.getTripHistory())   ;
    }

    // Getters

    /**
     * Gets user's id number
     * @return Int with user's id number
     */
    public int getId (){
        return this.id;
    }

    /**
     * Gets user's name
     * @return String with user's name
     */
    public String getName (){
         return this.name;
    }

    /**
     * Gets user's address
     * @return Address Object with user's address
     */
    public Address getAddress (){
        return this.address;
    } 

    /**
     * Gets user's birthday
     * @return LocalDate with user's birthday
     */
    public LocalDate getBirthday (){
        return this.birthday;
    } 

    /**
     * Gets user's email
     * @return String with user's email
     */
    public String getEmail (){
        return this.email;
    }

    /**
     * Gets user's password
     * @return String with user's password (hashed)
     */
    public String getPassword (){
        return this.password;
    }

    // Setters

    /**
     * Changes user's id number
     * @param id Id number
     */
    public void setId (int id){
        this.id = id;
    }

    /**
     * Changes user's name
     * @param name User's name
     */
    public void setName (String name){
        this.name = new String(name);
    }

    /**
     * Gets User trip history
     * @return Returns LinkedList with this intance User's Trip History
     */
    public List<Trip> getTripHistory(){
        return this.tripHistory;
    }

    /**
     * Gets copy of this instance User's trip history
     * @return Returns LinkedList that's a copy of this instance of User's Trip History
     */
    public List<Trip> getTripHistoryCopy(){
        List<Trip> aux = new LinkedList<Trip>();
        
        this.tripHistory.forEach(trip -> {aux.add(trip.clone());});

        return aux;
    }

    /**
     * Changes user's address
     * @param address User's address
     */
    public void setAddress (Address address){
        this.address = new Address(address);
    }

    /**
     * Changes user's birthday
     * @param date User's birthdate
     */
    public void setBirthday (LocalDate date){
        this.birthday = LocalDate.of(date.getYear(),
                                     date.getMonth(),
                                     date.getDayOfMonth()
                                     );
    }

    /**
     * Changes user's birthday
     * @param date User's birthdate
     */
    public void setBirthday (int year, int month, int day){
        this.birthday = LocalDate.of(year,month,day);
    }

    /**
     * Changes user's email
     * @param email User's email
     */
    public void setEmail (String email){
        this.email = new String(email);
    }

    /**
     * Changes user's password
     * @param passwors User's password
     */
    public void setPassword (String password){
        this.password = new String(password);
    }

    /**
     * Sets new trip history, by replacing it
     * @param history New trip hisotory to update to
     */
    private void setTripHistory(List<Trip> tripHistory){
        //List<Trip> history = new LinkedList<Trip>(tripHistory);
        
        this.tripHistory = new LinkedList<Trip>();
        tripHistory.forEach(trip -> {this.tripHistory.add(trip);} );
    }
    
    //    ----------    Instance Methods    ----------    //

    /**
     * Adds new trip to trip history
     * @param trip Trip to be added to trip history
     */
    public void addTripToHistory (Trip trip){
        ( (LinkedList<Trip>) this.tripHistory).addFirst(trip);
    }
    
}

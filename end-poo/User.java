
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
import java.util.Comparator;

public abstract class User implements Serializable {
    private String name;
    private Address address;
    private LocalDate birthday;
    private String email;
    private String password;
    private List<Integer> tripHistory;
    private double totalTripCost;
    
    /**
     * Empty contructor for User
     */
    private User (){
        this("N/A",null,null,"N/A","N/A");
    }

    /**
     * Contructor with all the arguments except trip history and total trip cost
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     */
    public User (String name, Address address, LocalDate birthday, String email, String password){
        this(name, address, birthday, email, password, new LinkedList<Integer>(),0);
    }

    /**
     * Contructor with all the arguments
     * @param name      User name
     * @param Address   User address
     * @param birthday  User birthday
     * @param email     User email
     * @param password  User password
     * @param tripHistory    User trip history
     */
    public User (String name, Address address, LocalDate birthday, String email, String password, List<Integer> history, double totalTripCost){
        this.setName(name);
        this.setAddress(address);
        this.setBirthday(birthday);
        this.setEmail(email);
        this.setPassword(password);
        this.setTripHistory(history);
        this.setTotalTripCost(totalTripCost);
    }

    /**
     * Constructor using object User as reference
     * @param user  User object to use as reference
     */
    public User (User user){
        this(user.getName(), 
             user.getAddress(), 
             user.getBirthday(), 
             user.getEmail(), 
             user.getPassword(),
             user.getTripHistory(),
             user.getTotalTripCost()
             );
    }

    /**
     * Abstract method for cloning objects with super class User. Needs to be implemented by subclasses
     * @return Returns a User object
     */
    public abstract User clone ();

    /**
     * Writes object as String
     * @return String of this User instance
     */
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(this.name);
        sb.append("\n");
        sb.append("Address: ");
        sb.append(this.address.getCity());
        sb.append(", ");
        sb.append(this.address.getCountry());
        sb.append("\n");
        sb.append("Birthday: ");
        sb.append(this.birthday.toString());
        sb.append("\n");
        sb.append("Email: ");
        sb.append(this.email);
        sb.append("\nTotal Trip Cost: ");
        sb.append(this.totalTripCost);
        sb.append("\n");
        // sb.append("password: ");
        // sb.append(password);
        sb.append("TripHistory: \n");
        // Uses forEach iterator to add each trip in tripHistory to the string builder 
        this.tripHistory.forEach( (Integer trip) -> {sb.append(trip);} );
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
        return this.name.equals(user.getName())                 &&
               this.address.equals(user.getAddress())           &&
               this.birthday.equals(user.getBirthday())         &&
               this.email.equals(user.getEmail())               &&
               this.password.equals(user.getPassword())         &&
               this.tripHistory.equals(user.getTripHistory())   ;
    }

    // Getters

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

    /**
     * 
     */
    public double getTotalTripCost (){
        return this.totalTripCost;
    }

    // Setters

    /**
     * Changes user's name
     * @param name User's name
     */
    public void setName (String name){
        this.name = new String(name);
    }

    /**
     * Gets copy of this instance User's trip history
     * @return Returns LinkedList that's a copy of this instance of User's Trip History
     */
    public List<Integer> getTripHistory(){
        List<Integer> aux = new LinkedList<Integer>();
        
        ((LinkedList<Integer>) this.tripHistory).forEach(trip -> {aux.add(trip);});

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
     * @param history New trip history to update to
     */
    private void setTripHistory(List<Integer> tripHistory){
        //List<Trip> history = new LinkedList<Integer>(tripHistory);
        
        this.tripHistory = new LinkedList<Integer>();
        List<Integer> aux = new LinkedList<Integer>(tripHistory);

        aux.forEach(trip -> {this.tripHistory.add(trip);} );
    }
    
    /**
     * Set's new cost to the total of trip cost
     * @param cost double with total cost of trips in history
     */
    public void setTotalTripCost(double cost){
        this.totalTripCost = cost;
    }

    //    ----------    Instance Methods    ----------    //

    /**
     * Adds new trip to trip history
     * @param trip Trip to be added to trip history
     */
    public void addTripToHistory (Trip trip){
        ( (LinkedList<Integer>) this.tripHistory).addFirst(trip.getId());

        this.totalTripCost += trip.getRealTripCost();
    }

    /**
     * Confirm if the given password is equal to the one stored
     * @param pass
     */
    public boolean confirmPass (String password) throws NullPointerException, IllegalStateException {
        if (password == null)
            throw new NullPointerException("Password can't be null!");
        if (password.trim() == "")
            throw new IllegalStateException("Password can't be empty!");
        
        if (this.getPassword().equals(password)) return true;
        else return false;
    }

    /**
     * 
     */
    public int hashCode(){
        return this.email.hashCode();
    }

    /** 
     * Counts number of trips done.
     * @return int with number of trips done
     */
    public long countTripsDone(){
        return this.tripHistory.stream().count();
    }
}


/**
 * Abstract class User - Parent class for users.
 *
 * @author a55617 Elísio Fernandes, a73175 Daniel Martins, aXXXXX Nuno Silva
 * @version 12/04/2017
 */

import java.lang.StringBuilder;
import java.time.LocalDate;

public abstract class User {
    private int id;
    private String name;
    private Address address;
    private LocalDate birthday;
    private String email;
    private String password;

    /**
     * Empty contructor for User
     */
    private User (){
        this(0,"N/A",null,null,"N/A","N/A");
    }

    /**
     * Contructor with all the arguments
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
             user.getPassword()
             );
    }

    /**
     * Clone method for class User
     * @return Copy of this User instance
     */
    public abstract User clone ();

    /**
     * Writes object as String
     * @return String of this User instance
     */
    public String toString (){
        StringBuilder sb = new StringBuilder("User (");
        sb.append("id: ");
        sb.append(id);
        sb.append(", ");
        sb.append("name: ");
        sb.append(name);
        sb.append(", ");
        sb.append("address: ");
        sb.append(address.toString());
        sb.append(", ");
        sb.append("birthday: ");
        sb.append(birthday.toString());
        sb.append(", ");
        sb.append("email: ");
        sb.append(email);
        sb.append(", ");
        sb.append("password: ");
        sb.append(password);
        sb.append(")\n");

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
        return this.id == user.getId()                  &&
               this.name.equals(user.getName())         &&
               this.address.equals(user.getAddress())   &&
               this.birthday.equals(user.getBirthday()) &&
               this.email.equals(user.getEmail())       &&
               this.password.equals(user.getPassword()) ;
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

    
    //    ----------    Instance Methods    ----------    //

    
}

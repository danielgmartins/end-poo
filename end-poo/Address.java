/**
 * Class Address - Class to represent an Address with a country and a city
 *
 * @author a55617 Elísio Fernandes, a73175 Daniel Martins, aXXXXX Nuno Silva
 * @version 12/04/2017
 */

import java.lang.StringBuilder;
import java.io.Serializable;

public class Address implements Serializable {
    private String city;
    private String country;

    /**
     * Empty Constructor
     */
    public Address (){
        this.country = "N/A";
        this.city = "N/A";
    }

    /**
     * Constructor with arguments
     * @param city City name
     * @param country Country name
     */
    public Address (String city, String country){
        this.setCity(city);
        this.setCountry(country);
    }

    /**
     * Copy Constructor
     */
    public Address (Address a){
        this.country = a.country;
        this.city = a.city;
    }

    /**
     * Create a clone of this object
     * @return returns new object Address, clone of this instance of Address
     */
    public Address clone (){
        return new Address(this.city, this.country);
    }

    /**
     * Return Address info as String
     * @param String of this Address instance
     */
    public String toString (){
        StringBuilder sb = new StringBuilder("Address: ");
        sb.append("City: ");
        sb.append(city);
        sb.append(", ");
        sb.append("Country: ");
        sb.append(country);
        sb.append(")\n");

        return sb.toString();
    }

    /**
     * Compare this Address to another to check if they are equal
     * @param o Object to use for comparison to this instace of Address
     * @return True if both objects are equal in all parameters, false otherwise
     */
    public boolean equals (Object o){
        if(this == o) return true;
        if(o != null && this.getClass() != o.getClass()) return false;

        Address aux = (Address) o;
        return aux.getCity().equals(this.getCity())         &&
               aux.getCountry().equals(this.getCountry())   ;
    }    

    // Getters

    /**
     * Gets city of this Address.
     * @return City City in the address
     */
    public String getCity (){
        return this.city;
    }

    /**
     * Gets Country of this Address.
     * @return Country Country in the address
     */
    public String getCountry (){
        return this.country;
    }

    // Setters

    /**
     * Change city in the address
     * @param city City for the address
     */
    public void setCity (String city){
        if (city == null)
            throw new NullPointerException("City can't be null");
        if (city.trim() == "")
            throw new IllegalStateException("City must have content");

        this.city = city;
    }

    /**
     * Change country in the address
     * @param country Country for the adress
     */
    public void setCountry (String country){
        if (country == null)
            throw new NullPointerException("Country can't be null");
        if (country.trim() == "")
            throw new IllegalStateException("Country must have content");

        this.country = country;
    }

}
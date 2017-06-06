/**
 * 
 * Class Address - Class to represent an Address with a country and a city
 * @author  a55617 Elísio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version 12/04/2017
 */

import java.lang.StringBuilder;
import java.io.Serializable;
import Exceptions.*;

public class Address implements Comparable<Address>, Serializable {
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
     * @return Returns new object Address, clone of this instance of Address
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
    public void setCity (String city) throws NullPointerException, IllegalStateException {
        if (city == null)
            throw new NullPointerException("City can't be null");
        if (city.trim().equals(""))
            throw new IllegalStateException("City must have content");

        this.city = city;
    }

    /**
     * Change country in the address
     * @param country Country for the adress
     */
    public void setCountry (String country) throws NullPointerException, IllegalStateException {
        if (country == null)
            throw new NullPointerException("Country can't be null");
        if (country.trim().equals(""))
            throw new IllegalStateException("Country must have content");

        this.country = country;
    }

    /**
     * Compares address to another address
     * Compares by city then by country
     * @param ad    Address to be compared against
     * @return  Returns -1 if lower, 0 if equal and 1 if higher
     */
    public int compareTo(Address ad){
        int aux = this.city.compareTo(ad.getCity());
        if(aux != 0) return aux;

        aux = this.country.compareTo(ad.getCountry());
        return aux;
    }

    /**
     * Hash code
     * @return int of code
     */
    public int hashCode(){
        return this.city.hashCode() * 1000000000 + this.country.hashCode();
    }

}

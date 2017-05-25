
/**
 * Class Van - Subclass of Vehicle with associated methods
 *
 * @author  a55617 Elisio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version 12/04/2017
 */

import java.util.Random;
import java.lang.StringBuilder;
import java.util.List;
import java.util.LinkedList;
import java.lang.Math;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.Queue;

public class Van extends Vehicle implements Serializable {


    /**
     * Empty constructor
     */
    private Van (){
        super("N/A", 4);
    }

    /**
     * Van constructor just receiveng a licence plate. Sets 6 seats as default
     * @param licensePlate  String with license plate
     */
    private Van (String licensePlate){
        super(licensePlate, 6);
    }

    /**
     * Constructor with indiviual parameters
     * Sets Reliablity to 1 as default
     * @param licensePlate    License plate
     * @param averageSpeed    Average speed per km
     * @param kmsTotal        Total of kms
     * @param fare            Fare per km
     * @param availability    Availability
     * @param seats           Number of seats
     * @param x               x coordinate
     * @param y               y coordinate
     */
    public Van (String licensePlate, double averageSpeed, double kmsTotal, double fare, boolean availability, int seats, int x, int y){
        super(licensePlate,
              averageSpeed,
              kmsTotal,
              fare,
              1.0,
              availability,
              seats,
              x,
              y);
    }
    
    /**
     * Constructor with Van parameter
     * @param Van   Van object
     */
    public Van (Van c){
        super(c.getLicensePlate(),
              c.getAverageSpeed(),
              c.getKmsTotal(),
              c.getFare(),
              c.getReliability(),
              c.getAvailability(),
              c.getSeats(),
              c.getLocation());
    }

    /**
     * Makes copy of the Van
     * @return  Van copy
     */
    public Van clone (){
        return new Van(this);
    };

    /**
     * Creates a string of every parameter
     * @return  Description of Van
     */
    public String toString (){
        StringBuilder res = new StringBuilder("---\nVan\n");

        res.append(super.toString());

        return res.toString();
    }

    /**
     * Compares Vans
     * @return  True if equal; False if Different
     */
    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Van c = (Van) o;

        return super.equals(c);
    }

    /**
     * Sets Van's reliability bearing in mind the randomability of weather, traffic conditions and the fact that a Van is pretty unreliable itself
     */
    public void setReliability (){
        Random rand = new Random();
        Random randWeather = new Random();
        Random randTraffic = new Random();

        double reliable = Math.abs(rand.nextDouble() * randWeather.nextDouble() * randTraffic.nextDouble() - 0.005);

        if (reliable > 1) super.setReliability(1.0);
        else super.setReliability(reliable);
    }


}

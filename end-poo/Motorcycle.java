
/**
 * Class Motorcycle - Subclass of Vehicle with associated methods
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

public class Motorcycle extends Vehicle implements Serializable {


    /**
     * Empty constructor
     */
    private Motorcycle (){
        super("N/A", 4);
    }

    /**
     * Motorcycle constructor just receiveng a licence plate. Sets 1 seats as default
     * @param licensePlate  String with license plate
     */
    public Motorcycle (String licensePlate){
        super(licensePlate, 1);
        this.setAverageSpeed(40);
    }

    /**
     * Constructor with indiviual parameters
     * Sets Reliablity to 1 as default
     * @param licensePlate    License plate
     * @param averageSpeed    Average speed per km
     * @param kmsTotal        Total of kms
     * @param fare            Fare per km
     * @param seats           Number of seats
     * @param x               x coordinate
     * @param y               y coordinate
     */
    public Motorcycle (String licensePlate, double averageSpeed, double kmsTotal, double fare, int seats, int x, int y){
        super(licensePlate,
              averageSpeed,
              kmsTotal,
              fare,
              1.0,
              seats,
              x,
              y);
    }
    
    /**
     * Constructor with Motorcycle parameter
     * @param Motorcycle   Motorcycle object
     */
    public Motorcycle (Motorcycle c){
        super(c);
    }

    /**
     * Makes copy of the Motorcycle
     * @return  Motorcycle copy
     */
    public Motorcycle clone (){
        return new Motorcycle(this);
    };

    /**
     * Creates a string of every parameter
     * @return  Description of Motorcycle
     */
    public String toString (){
        StringBuilder res = new StringBuilder("---\nMotorcycle\n");

        res.append(super.toString());

        return res.toString();
    }

    /**
     * Compares Motorcycles
     * @return  True if equal; False if Different
     */
    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Motorcycle c = (Motorcycle) o;

        return super.equals(c);
    }

    /**
     * Sets Motorcycle's reliability bearing in mind the randomability of weather, traffic conditions and the fact that a Motorcycle is pretty unreliable itself
     */
    public void setReliability (){
        Random rand = new Random();
        Random randWeather = new Random();
        Random randTraffic = new Random();

        double reliable = -1;
        
        while(reliable < 0.5 || reliable > 1.75) {
            reliable = ( rand.nextGaussian() * randWeather.nextGaussian() * randTraffic.nextGaussian())*2 + 1;
            System.out.println(reliable);
        }
        
        super.setReliability(reliable);
    }


}

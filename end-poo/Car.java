
/**
 * Class Car - Subclass of Vehicle with associated methods
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

public class Car extends Vehicle implements Serializable {


    /**
     * Empty constructor
     */
    private Car (){
        super("N/A", 4);
    }

    /**
     * Car constructor just receiveng a licence plate. Sets 4 seats as default
     * @param licensePlate  String with license plate
     */
    public Car (String licensePlate){
        super(licensePlate, 4);
        this.setAverageSpeed(60.0);
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
    public Car (String licensePlate, double averageSpeed, double kmsTotal, double fare, int seats, int x, int y){
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
     * Constructor with Car parameter
     * @param Car   Car object
     */
    public Car (Car c){
        super(c);
    }

    /**
     * Makes copy of the car
     * @return  Car copy
     */
    public Car clone (){
        return new Car(this);
    };

    /**
     * Creates a string of every parameter
     * @return  Description of car
     */
    public String toString (){
        StringBuilder res = new StringBuilder("---\nCar\n");

        res.append(super.toString());

        return res.toString();
    }

    /**
     * Compares cars
     * @return  True if equal; False if Different
     */
    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Car c = (Car) o;

        return super.equals(c);
    }

    /**
     * Sets car's reliability bearing in mind the randomability of weather, traffic conditions and the fact that a car is pretty unreliable itself
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

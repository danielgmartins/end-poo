
/**
 * Class Car - Subclass of Vehicle with associated methods
 *
 * @author  a55617 Elisio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version 12/04/2017
 */

import java.lang.StringBuilder;
import java.util.List;

import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.Queue;

public class Car extends Vehicle implements Serializable {


    /**
     * Empty constructor
     */
    private Car (){
        super("na", -1.0, -1.0, -1.0, false, -1, -1, -1, false, (List<Trip>) null);
    }

    /**
     * Constructor with indiviual parameters
     * @param licensePlate    License plate
     * @param averageSpeed    Average speed per km
     * @param fare            Fare per km
     * @param availability    Availability
     * @param seats           Number of seats
     * @param x               x coordinate
     * @param y               y coordinate
     * @param queue           Queue setting
     * @param queueListIn     Queue list
     */
    public Car (String licensePlate, double averageSpeed, double fare, boolean availability, int seats, int x, int y, boolean queue, List<Trip> queueListIn){
        super(licensePlate,
              averageSpeed,
              fare,
              availability,
              seats,
              x,
              y,
              queue,
              queueListIn);
    }

    /**
     * Constructor with indiviual parameters
     * @param licensePlate    License plate
     * @param averageSpeed    Average speed per km
     * @param fare            Fare per km
     * @param availability    Availability
     * @param seats           Number of seats
     * @param x               x coordinate
     * @param y               y coordinate
     * @param queueListIn     Queue list
     */
    public Car (String licensePlate, double averageSpeed, double fare, boolean availability, int seats, int x, int y, List<Trip> queueListIn){
        super(licensePlate,
              averageSpeed,
              fare,
              availability,
              seats,
              x,
              y,
              true,
              queueListIn);
    }

    /**
     * Constructor with indiviual parameters
     * @param licensePlate    License plate
     * @param averageSpeed    Average speed per km
     * @param fare            Fare per km
     * @param availability    Availability
     * @param seats           Number of seats
     * @param x               x coordinate
     * @param y               y coordinate
     */
    public Car (String licensePlate, double averageSpeed, double fare, boolean availability, int seats, int x, int y){
        super(licensePlate,
              averageSpeed,
              fare,
              availability,
              seats,
              x,
              y,
              false,
              (List<Trip>) null);
    }
    /**
     * Constructor with Car parameter
     * @param Car   Car object
     */
    public Car (Car c){
        super(c.getLicensePlate(),
              c.getAverageSpeed(),
              c.getFare(),
              c.getReliability(),
              c.getAvailability(),
              c.getSeats(),
              c.getLocation().getX(),
              c.getLocation().getY(),
              c.getQueueValue(),
              c.getQueueList());

        // if (this.getQueueValue()){
        //     this.queueList = new LinkedList<Trip>();
        //     this.queueList = c.getQueueList();
        // }
        // else this.queueList = null;
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



}

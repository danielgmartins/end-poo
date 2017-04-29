
/**
 * Class Car - Subclas of Vehicle with associated methods
 *
 * @author  a55617 Elisio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version 12/04/2017
 */

import java.lang.StringBuilder;
import java.util.LinkedList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.Queue;

public class Car extends Vehicle implements Serializable {


    /**
     * Empty constructor
     */
    private Car (){
      super(-1.0, -1.0, -1.0, false, -1, -1, false, (Queue<Trip>)null);
    }

    /**
     * Constructor with indiviual parameters
     * @param averageSpeed    Average speed per km
     * @param fare            Fare per km
     * @param reliability     Reliability
     * @param availability    Availability
     * @param queue           Queue setting
     * @param x               x coordinate
     * @param y               y coordinate
     */
    public Car (double averageSpeed, double fare, double reliability, boolean availability, int seats, double x, double y, boolean queue, Queue<Trip> queueListIn){
        super(averageSpeed, fare, reliability, availability, seats, x, y, queue, queueListIn);
    }

    /**
     * Constructor with Car parameter
     * @param Car   Car object
     */
    public Car (Car c){
        super(c.getAverageSpeed(), c.getFare(), c.getReliability(), c.getAvailability(), c.getSeats(), c.getX(), c.getY(), c.getQueueValue(), c.getQueueList());

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
        StringBuilder res = new StringBuilder("---\nCar: ");

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

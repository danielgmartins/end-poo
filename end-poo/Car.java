
/**
 * Class Car - Subclas of Vehicle with associated methods
 *
 * @author  a55617 Elisio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version 12/04/2017
 */

import java.lang.StringBuilder;
import java.util.Queue;
import java.util.LinkedList;
import java.util.stream. Stream;;
import java.util.stream.Collectors;

public class Car extends Vehicle{
    private Queue<Trip> queueList;

    /**
     * Empty constructor
     */
    private Car (){
      super();
      this.queueList = new LinkedList<Trip>();
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
    public Car (double averageSpeed, double fare, double reliability, boolean availability, boolean queue, double x, double y){
        super(averageSpeed, fare, reliability, availability, queue, x, y);
    }

    /**
     * Constructor with Car parameter
     * @param Car   Car object
     */
    public Car (Car c){
        super(c.getAverageSpeed(), c.getFare(), c.getReliability(), c.getAvailability(), c.getQueueValue(), c.getX(), c.getY());

        if (this.getQueueValue()){
            this.queueList = new LinkedList<Trip>();
            this.queueList = c.getQueueList();
        }
        else this.queueList = null;
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
        StringBuilder res = new StringBuilder();

        res.append(super.toString());
        res.append("Queue" + this.queueList.stream()
                             .map(Trip::toString).map(append("; ")));

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

        return super.equals(c) && this.getQueueList().equals(c.getQueueList());
    }

    /**
     * Gets car's queue list's copy
     * @return   Average speed
     */
    public Queue<Trip> getQueueList (){
        if (this.getQueueValue())
            return this.queueList.stream().map(Trip::clone)
                .collect(Collectors.toList());
    }

}

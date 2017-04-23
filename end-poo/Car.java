
/**
 * Abstract class Vehicle - PArent class for vehicles with associated methods
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
    public Car (){
      super();
      this.queueList = null;
    }

    /**
     * Constructor with indiviual parameters
     * @param double    Average speed per km
     * @param double    Fare per km
     * @param double    Reliability
     * @param boolean   Availability
     */
    public Car (double averageSpeed, double fare, double reliability, boolean availability, boolean queue){
        super(averageSpeed, fare, reliability, availability, queue);
    }

    /**
     * Constructor with Car parameter
     * @param Car   Car object
     */
    public Car (Car c){
        super(c.getAverageSpeed(), c.getFare(), c.getReliability(), c.getAvailability(), c.getQueueValue());

        if (this.getQueueValue()){
            this.queueList = new LinkedList<Integer>();
            this.queueList = c.getQueueList();
        }
    }

    /**
     * Makes copy of the car
     * @return Vehicle  Car
     */
    public Car clone (){
        return new Car(this);
    };

    /**
     * Creates a string of every parameter
     * @return String   Description of car
     */
    public String toString (){
        StringBuilder res = new StringBuilder();

        res.append(super.toString());
        //res.append("Queue" + this.queue);

        return res.toString();
    }

    /**
     * Compares vehicles
     * @return boolean   True if equal; False if Different
     */
    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Car c = (Car) o;

        return super.equals(c) && this.getQueueList().equals(c.getQueueList());
    }

    /**
     * Gets car's queue list's copy
     * @return double   Average speed
     */
    public Queue<Trip> getQueueList (){
        if (this.getQueueValue())
            return this.queueList.stream().map(Trip::clone)
                .collect(Collectors.toList());
    }

}

/**
* Class Trip is a class that keeps track of the trips that every cab and driver/client have made, including time,fare and other aspects.
*@author Elisio Fernandes A55617 Daniel Martins A73175 Nuno Silva A78879
*@version 13/4/2017
*/

import java.lang.StringBuilder;
import java.time.LocalDateTime;

public class Trip {
	private Client requester;
	private Driver driver;
	private LocalDateTime date;
	private Car taxi;
	private Coordinate taxiLocation;
	private Coordinate clientLocation;
	private Coordinate destination;
	private int realTripTime;
	private int tripCost;

	/**
	*Constructors of class Trip (including empty and copy Constructors).
	* @param c1 Client object who requests the trip
	* @param d1 Driver object who performs the trip
	* @param date1 LocalDateTime object with date and time of trip
	* @param tax1 Car object who performs the trip
	* @param taxiloc Coordinate object with taxi's location
	* @param clientloc Coordinate object with client's location
	* @param dest Coordinate object with the trip's destination
	* @param realtript time of the trip
	* @param tripCost1 cost of trip
	*/

	public Trip(Client c1, Driver d1, LocalDateTime date1, Car tax1, Coordinate taxiloc, Coordinate clientloc, Coordinate dest,int realtript,int cost1){
        this.requester = c1;
        this.driver = d1;
		this.date = date1;
		this.taxi = tax1;
		this.taxiLocation = taxiloc;
		this.clientLocation = clientloc;
		this.destination = dest;
		this.realTripTime = realtript;
		this.tripCost = cost1;
    }

	public Trip(){
		this.requester = null;
		this.driver = null;
		this.date = null;
		this.taxi = null;
		this.taxiLocation = null;
		this.clientLocation = null;
		this.destination = null;
		this.realTripTime = -1;
		this.tripCost = -1;
	}

	public Trip(Trip ref){
		this.requester = ref.getClient();
		this.driver = ref.getDriver();
		this.date = ref.getDate();
		this.taxi = ref.getTaxi();
		this.taxiLocation = ref.getTaxiLocation();
		this.clientLocation = ref.getClientLocation();
		this.destination = ref.getDestination();
		this.realTripTime = ref.getRealTripTime();
		this.tripCost = ref.getTripCost();
	}

    /**
    * Clone creates an equal object and returns it in order to protect it's original reference/pointer (Encapsulation)
    * @return A new object with the same instance variables
    *
    */

	public Trip clone (){
        Trip res = new Trip(this);
        return res;
    }

	/**
    * Equals tests if two objects are equal to each other through their instance variables.
    * @param ref the object to which the object who is executing this method will compare himself to.
    * @return boolean true or false, if objects are equal or not.
    *
    */

    public boolean equals (Object ref){
        if (ref == this) return true;
        if ((ref == null)|| (ref.getClass()!= this.getClass())) return false;
        Trip l = (Trip) ref;
        return (this.requester.equals(ref.getRequester())
														&& (this.driver.equals(ref.getDriver()))
														&& (this.date.equals(ref.getDate()))
														&& (this.taxi.equals(ref.getTaxi()))
														&& (this.taxiLocation == ref.getTaxiLocation())
														&& (this.clientLocation == ref.getClientLocation())
														&& (this.destination == ref.getDestination())
														&& (this.realTripTime == ref.getRealTripTime())
														&& (this.tripCost == ref.getTripCost()));
    }

	/**
    * toString returns a string cointaining the instance variables of a Trip object.
    * @return String containing the objects instance variables
    */

    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append(" Client : ");
        sb.append(this.requester.toString());
        sb.append(" Driver : ");
        sb.append(this.driver.toString());
		sb.append(" date : ");
        sb.append(this.date.toString());
		sb.append(" Taxi : ");
        sb.append(this.taxi.toString());
		sb.append(" Taxi's initial position : ");
        sb.append(this.taxiLocation.toString());
		sb.append(" Client's initial location : ");
        sb.append(this.clientLocation.toString());
		sb.append(" Destination : ");
		sb.append(this.destination.toString());
		sb.append(" Real trip time : ");
        sb.append(this.realTripTime);
		sb.append(" Cost : ");
		sb.append(this.tripCost);
        return sb.toString();
    }

	//Getters and setters

	/**
     * getclient returns the client who requested the Trip.
     *
     * @return    the client object who requested the Trip.
     */

	 public Client getClient(){
		 Client res = this.requester.clone();
		 return res;
	 }

	 /**
	   * getDriver returns the driver who performed the Trip.
	   *
	   * @return    the driver object who performed the Trip.
	   */

	   public Driver getDriver(){
		   Driver res = this.driver.clone();
		   return res;
	   }

	   /**
  	   * getDriver returns the LocalDateTime object containing temporal data of the Trip.
  	   *
  	   * @return    LocalDateTime object with date and time of Trip
  	   */

  	   public LocalDateTime getDate(){
  		   LocalDateTime res = this.date.clone();
  		   return res;
  	   }

	   /**
  	   * getTaxi returns the Car in which the Trip was made.
  	   *
  	   * @return    the Car object who performed the Trip.
  	   */

  	   public Car getTaxi(){
  		   Car res = this.taxi.clone();
  		   return res;
  	   }

	   /**
  	   * getTaxiLocation returns the Coordinate object containing taxi location.
  	   *
  	   * @return Coordinate object with taxi location.
  	   */

  	   public Coordinate getTaxiLocation(){
  		   Coordinate res = this.taxiLocation.clone();
		   return res;
  	   }

	    /**
   	   * getClientLocation returns the Coordinate object containing the client's location.
   	   *
   	   * @return Coordinate object with client's location.
   	   */

   	   public Coordinate getClientLocation(){
   		   Coordinate res = this.clientLocation.clone();
 		   return res;
   	   }

	   /**
	  * getDestination returns the Coordinate object containing the Trip's destination.
	  *
	  * @return Coordinate object with Trip's destination.
	  */

	  public Coordinate getDestination(){
		  Coordinate res = this.destination.clone();
		  return res;
	  }

	  /**
	  * getRealTripTime returns the Trip's time.
	  *
	  * @return int with duration of the Trip.
	  */

	  public int getRealTripTime(){
		  return this.realTripTime;
	  }

	  /**
	  * getTripCost returns the Trip's tripCost.
	  *
	  * @return int with tripCost of the Trip.
	  */

	  public int getTripCost(){
		  return this.tripCost;
	  }
}

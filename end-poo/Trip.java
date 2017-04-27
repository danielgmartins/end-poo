/**
* Class Trip is a class that keeps track of the trips that every cab and driver/client have made, including time,fare and other aspects.
*@author Elisio Fernandes A55617 Daniel Martins A73175 Nuno Silva A78879
*@version 13/4/2017
*/

import java.lang.StringBuilder;
import java.time.LocalDateTime;

public class Trip {
	private Client client;
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
	* @param client			Client object who requests the trip
	* @param driver			Driver object who performs the trip
	* @param date			LocalDateTime object with date and time of trip
	* @param taxi 			Car object who performs the trip
	* @param taxiLocation 	Coordinate object with taxi's location
	* @param clientLocation Coordinate object with client's location
	* @param destination 	Coordinate object with the trip's destination
	* @param realTripTime 	time of the trip
	* @param tripCost 		cost of trip
	*/
	public Trip(Client client, Driver driver, LocalDateTime date, Car taxi, Coordinate taxiLocation, Coordinate clientLocation, Coordinate destination,int realTripTime,int tripCost){
        this.setClient(client);
        this.setDriver(driver);
		this.setDate(date);
		this.setTaxi(taxi);
		this.setTaxiLocation(taxiLocation);
		this.setClientLocation(clientLocation);
		this.setDestination(destination);
		this.setRealTripTime(realTripTime);
		this.setTripCost(tripCost);
    }

	private Trip(){
		this.client = null;
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
		this.client = ref.getClient();
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
        return this.client.equals(ref.getRequester())  			&&
			this.driver.equals(ref.getDriver())         		&&
			this.date.equals(ref.getDate())             		&&
			this.taxi.equals(ref.getTaxi())             		&&
			this.taxiLocation.equals(ref.getTaxiLocation()) 	&&
			this.clientLocation.equals(ref.getClientLocation()) &&
			this.destination.equals(ref.getDestination())  		&&
			this.realTripTime == ref.getRealTripTime() 			&&
			this.tripCost == ref.getTripCost();
    }

	/**
    * toString returns a string cointaining the instance variables of a Trip object.
    * @return String containing the objects instance variablesaxiLocation = taxiloc;
    */
	public String toString () {
		StringBuilder sb = new StringBuilder();
        sb.append(" Client : ");
        sb.append(this.client.toString());
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

	//Getters
	/**
    * getclient returns the client who requested the Trip.
	*
	* @return    the client object who requested the Trip.
	*/
	public Client getClient(){
		Client res = this.client.clone();
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

	//Setters

	/**
	* setClient changes the client's instance variable object to the object passed as a variable.
	*@param client	the client object to atribute as the client's instance variable on Trip object.
	*/
	public void setClient( Client client){
		this.client = client.clone();
	}

	/**
	* setDriver changes the driver's instance variable object to the object passed as a variable.
	*@param driver	the driver object to atribute as the driver's instance variable on Trip object.
	*/
	public void setDriver (Driver driver){
		this.driver = driver.clone();
	}

	/**
	* setDate changes the date's instance variable object to the object passed as a variable.
	*@param date	the date object to atribute as the date's instance variable on Trip object.
	*/
	public void setDate( LocalDateTime date){
		this.date = date.clone();
	}

	/**
	* setTaxi changes the taxi's instance variable object to the object passed as a variable.
	*@param taxi	the vehicle object to atribute as the taxi's instance variable on Trip object.
	*/
	public Car setTaxi( Car taxi){
		this.taxi = taxi.clone();
	}

	/**
	* setTaxiLocation changes the taxiLocation's instance variable object to the object passed as a variable.
	*@param taxiLocation		the Coordinate object to atribute as the taxiLocation's instance variable on Trip object.
	*/
	public void setTaxiLocation( Coordinate taxiLocation){
		this.taxiLocation = taxiLocation.clone();
	}

	/**
	* setClientLocation changes the clientLocation's instance variable object to the object passed as a variable.
	*@param clientLocation		the Coordinate object to atribute as the clientLocation's instance variable on Trip object.
	*/
	public void setClientLocation( Coordinate clientLocation){
		this.clientLocation = clientLocation.clone();
	}

	/**
	* setDestination changes the destination's instance variable object to the object passed as a variable.
	*@param destination		the Coordinate object to atribute as the destination's instance variable on Trip object.
	*/
	public void setDestination( Coordinate destination){
		this.destination = destination.clone();
	}

	/**
	* setRealTripTime changes the realTripTime's instance variable to the value passed as a variable.
	*@param realTripTime		the value (int) to atribute as the realTripTime's instance variable on Trip object.
	*/
	public void setRealTripTime( int realTripTime){
		this.realTripTime = realTripTime;
	}

	/**
	* setTripCost changes the tripCost's instance variable to the value passed as a variable.
	*@param tripCost		the value (int) to atribute as the tripCost's instance variable on Trip object.
	*/
	public void setTripCost( int tripCost){
		this.tripCost = tripCost;
	}





}

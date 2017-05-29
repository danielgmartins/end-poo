/**
 * Class Trip is a class that keeps track of the trips that every cab and driver/client have made, including time,fare and other aspects.
 * @author Elisio Fernandes A55617 Daniel Martins A73175 Nuno Silva A78879
 * @version 13/4/2017
 */

import java.lang.StringBuilder;
import java.time.LocalDateTime;
import java.io.Serializable;

public class Trip implements Comparable<Trip>, Serializable {
	private int id;
	private String client;
	private String driver;
	private LocalDateTime date;
	private String taxi;
	private Coordinates taxiLocation;
	private Coordinates clientLocation;
	private Coordinates destination;
	private int estimatedTripTime;
	private int realTripTime;
	private double expectedTripCost;
	private double realTripCost;

	/**
	 *Constructors of class Trip (including empty and copy Constructors).
	 * @param id					Integer that identifies the Trip
	 * @param client				String with email of client who requests the trip
	 * @param driver				String with email of Driver who performs the trip
	 * @param date					LocalDateTime object with date and time of trip
	 * @param taxi 					String of vehicle licensePlate who performs the trip
	 * @param taxiLocation 			Coordinates object with taxi's location
	 * @param clientLocation 		Coordinates object with client's location
	 * @param destination 			Coordinates object with the trip's destination
	 * @param estimatedTripTime 	estimated time of the trip
	 * @param realTripTime 			time of the trip
	 * @param expectedTripCost 		cost of trip
	 */
	public Trip(int id, String client, String driver, LocalDateTime date, String taxi, Coordinates taxiLocation, Coordinates clientLocation, Coordinates destination, int estimatedTripTime,int realTripTime,double expectedTripCost, double realTripCost){
		this.setId(id);
		this.setClient(client);
        this.setDriver(driver);
		this.setDate(date);
		this.setTaxi(taxi);
		this.setTaxiLocation(taxiLocation);
		this.setClientLocation(clientLocation);
		this.setDestination(destination);
		this.setEstimatedTripTime(estimatedTripTime);
		this.setRealTripTime(realTripTime);
		this.setExpectedTripCost(expectedTripCost);
		this.setRealTripCost(realTripCost);
    }

	/** Empty constructor for Trip object
	 *
	 */
	private Trip(){
		this(-1,null,null,null,null,null,null,null,-1,-1,-1.0,-1.0);
	}

	/** Copy constructor for Trip object.
	 * @param ref	the Trip object that will be used as a reference for the creation of a new Trip object that is equal to ref object.
	 */
	public Trip(Trip ref){
		this(ref.getId(),
			 ref.getClient(),
			 ref.getDriver(),
			 ref.getDate(),
			 ref.getTaxi(),
			 ref.getTaxiLocation(),
			 ref.getClientLocation(),
			 ref.getDestination(),
			 ref.getEstimatedTripTime(),
			 ref.getRealTripTime(),
			 ref.getExpectedTripCost(),
			 ref.getRealTripCost());
	}

	/**
	 * A more useful constructor for Trip objects, should be called before the trip and updated with expectedTripCost and realTripTime.
	 * Initializes expectedTripCost to -1 and realTripTime to -1.
	 * @param id 					Integer that identifies the Trip
	 * @param client				String with email of client who requests the trip
	 * @param driver				String with email of Driver who performs the trip
	 * @param date					LocalDateTime object with date and time of trip
	 * @param taxi 					String of vehicle licensePlate who performs the trip
	 * @param taxiLocation 			Coordinates object with taxi's location
	 * @param clientLocation 		Coordinates object with client's location
	 * @param x						x coordinate of trip's destination
	 * @param y 					y coordinate of trip's destination
	 * @param estimatedTripTime		estimated time of trip
	 */
	public Trip (int id,String client, String driver, LocalDateTime date, String taxi, Coordinates taxiLocation, Coordinates clientLocation, int x,int y, int estimatedTripTime){
		this(id,client,driver,date,taxi,taxiLocation,clientLocation,new Coordinates(x,y), estimatedTripTime,0,0.0,0.0);
	}

    /**
     * Clone creates an equal object and returns it in order to protect it's original reference/pointer (Encapsulation)
     * @return 	A new object with the same instance variables
     *
     */
	public Trip clone (){
        return new Trip(this);
    }

	/**
     * Equals tests if two objects are equal to each other through their instance variables.
     * @param ref the object to which the object who is executing this method will compare himself to.
     * @return boolean true or false, if objects are equal or not.
     *
     */
    public boolean equals (Object l){
        if (l == this) return true;
        if ((l == null)|| (l.getClass()!= this.getClass())) return false;

        Trip ref = (Trip) l;
        return this.id == ref.getId()                               &&
			this.client.equals(ref.getClient())  				    &&
			this.driver.equals(ref.getDriver())         			&&
			this.date.equals(ref.getDate())             			&&
			this.taxi.equals(ref.getTaxi())             			&&
			this.taxiLocation.equals(ref.getTaxiLocation()) 		&&
			this.clientLocation.equals(ref.getClientLocation())		&&
			this.destination.equals(ref.getDestination())  			&&
			this.estimatedTripTime == ref.getEstimatedTripTime()	&&
			this.realTripTime == ref.getRealTripTime() 				&&
			this.expectedTripCost == ref.getExpectedTripCost()		&&
			this.realTripCost == ref.getRealTripCost()				;
    }

	/**
     * toString returns a string cointaining the instance variables of a Trip object.
     * @return 	String containing the objects instance variablesaxiLocation = taxiloc;
     */
	public String toString () {
		StringBuilder sb = new StringBuilder("Trip");
		sb.append("\nTrip id : ");
		sb.append(this.id);
		sb.append("\nClient : ");
        sb.append(this.client);
        sb.append("\nDriver : ");
        sb.append(this.driver);
		sb.append("\nDate : ");
        sb.append(this.date.toString());
		sb.append("\nTaxi : ");
		sb.append(this.taxi);
		sb.append("\nTaxi's initial position : ");
        sb.append(this.taxiLocation.toString());
		sb.append("\nClient's initial location : ");
        sb.append(this.clientLocation.toString());
		sb.append("\nDestination : ");
		sb.append(this.destination.toString());
		sb.append("\nEstimated Trip Time : ");
		sb.append(this.estimatedTripTime);
		sb.append("\nReal trip time : ");
        sb.append(this.realTripTime);
		sb.append("\nExpected Cost : ");
		sb.append(this.expectedTripCost);
		sb.append("\nReal Cost : ");
		sb.append(this.realTripCost);
		sb.append("\n");
        return sb.toString();
	}

	//Getters
	/**
	 * getId returns the id field of the Trip
	 * @return    int with the identification number of the trip
	 */
	public int getId(){
		return this.id;
	}

	/**
     * getclient returns the client who requested the Trip.
	 *
	 * @return    the client object who requested the Trip.
	 */
	public String getClient(){
		return new String(this.client);
	}

	/**
	 * getDriver returns the driver who performed the Trip.
	 *
	 * @return    the driver object who performed the Trip.
	 */
	public String getDriver(){
		return new String(this.driver);
	}

	/**
	 * getDate  returns the LocalDateTime object containing temporal data of the Trip.
	 *
	 * @return    LocalDateTime object with date and time of Trip
	 */
	public LocalDateTime getDate(){
		return this.date;
	}

	/**
	 * getTaxi returns the Car in which the Trip was made.
	 *
	 * @return	String of LicensePlate of vehicle who performed the Trip.
	 */
	public String getTaxi(){
		return new String(this.taxi);
	}

	/**
	 * getTaxiLocation returns the Coordinates object containing taxi location.
	 *
	 * @return 	Coordinates object with taxi location.
	 */
	public Coordinates getTaxiLocation(){
		return this.taxiLocation.clone();
	}

	/**
	 * getClientLocation returns the Coordinates object containing the client's location.
	 *
	 * @return 	Coordinates object with client's location.
	 */
	public Coordinates getClientLocation(){
		return this.clientLocation.clone();
	}

	/**
	 * getDestination returns the Coordinates object containing the Trip's destination.
	 *
	 * @return 	Coordinates object with Trip's destination.
	 */
	public Coordinates getDestination(){
		return this.destination.clone();
	}

	/**
	 * getEstimatedTripTime returns the Trip's estimated time.
	 *
	 * @return 	int with estimated duration of the Trip.
	 */
	public int getEstimatedTripTime(){
		return this.estimatedTripTime;
	}

	/**
	 * getRealTripTime returns the Trip's time.
	 *
	 * @return 	int with duration of the Trip.
	 */
	public int getRealTripTime(){
		return this.realTripTime;
	}

	/**
	 * getExpectedTripCost returns the Trip's expectedTripCost.
	 *
	 * @return 	double with expectedTripCost of the Trip.
	 */
	public double getExpectedTripCost(){
		return this.expectedTripCost;
	}

	/**
	 * getExpectedTripCost returns the Trip's real trip cost.
	 * 
	 * @return double with real trip cost
	 */
	public double getRealTripCost(){
		return this.realTripCost;
	}

	//Setters

	/**
	 * setId changes the Trip's id to the value it receives as a parameter
	 * @param id		the new id to attribute to the Trip
	 */
	public void setId(int id){
		this.id=id;
	}

	/**
	 * setClient changes the client's instance variable object to the email passed as a variable.
	 * @param client	the client email to atribute as the client's instance variable on Trip object.
	 */
	public void setClient( String client){
		this.client = new String(client);
	}

	/**
	 * setDriver changes the driver's instance variable object to the email passed as a variable.
	 * @param driver	the driver oemailbject to atribute as the driver's instance variable on Trip object.
	 */
	public void setDriver (String driver){
		this.driver = new String(driver);
	}

	/**
	 * setDate changes the date's instance variable object to the object passed as a variable.
	 * @param date	the date object to atribute as the date's instance variable on Trip object.
	 */
	public void setDate( LocalDateTime date){
		this.date=date;
	}

	/**
	 * setTaxi changes the taxi's instance variable object to the object passed as a variable.
	 * @param taxi	the vehicle object to atribute as the taxi's instance variable on Trip object.
	 */
	public void setTaxi( String taxi){
		this.taxi = new String(taxi);
	}

	/**
	 * setTaxiLocation changes the taxiLocation's instance variable object to the object passed as a variable.
	 * @param taxiLocation	the Coordinates object to atribute as the taxiLocation's instance variable on Trip object.
	 */
	public void setTaxiLocation( Coordinates taxiLocation){
		this.taxiLocation = taxiLocation.clone();
	}

	/**
	 * setClientLocation changes the clientLocation's instance variable object to the object passed as a variable.
	 * @param clientLocation		the Coordinates object to atribute as the clientLocation's instance variable on Trip object.
	 */
	public void setClientLocation( Coordinates clientLocation){
		this.clientLocation = clientLocation.clone();
	}

	/**
	 * setDestination changes the destination's instance variable object to the object passed as a variable.
	 * @param destination		the Coordinates object to atribute as the destination's instance variable on Trip object.
	 */
	public void setDestination( Coordinates destination){
		this.destination = destination.clone();
	}

	/**
	 * setEstimatedTripTime changes the estimatedTripTime's instance variable to the value passed as a variable.
	 * @param estimatedTripTime		the value (int) to atribute as the realTripTime's instance variable on Trip object.
	 */
	public void setEstimatedTripTime( int estimatedTripTime){
		this.estimatedTripTime = estimatedTripTime;
	}

	/**
	 * setRealTripTime changes the realTripTime's instance variable to the value passed as a variable.
	 * @param realTripTime	the value (int) to atribute as the realTripTime's instance variable on Trip object.
	 */
	public void setRealTripTime( int realTripTime){
		this.realTripTime = realTripTime;
	}

	/**
	 * setExpectedTripCost changes the expectedTripCost's instance variable to the value passed as a variable.
	 * @param expectedTripCost		the value (int) to atribute as the expectedTripCost's instance variable on Trip object.
	 */
	public void setExpectedTripCost( double expectedTripCost){
		this.expectedTripCost = expectedTripCost;
	}

	/**
	 * setRealTripCost changes the realTripCost's instance variable to the value passed as a variable.
	 * @param realTripCost		the value (int) to atribute as the realTripCost's instance variable on Trip object.
	 */
	public void setRealTripCost( double realTripCost){
		this.realTripCost = realTripCost;
	}

	/**
	 * compareTo implements the natural order between two Trip objects
	 * @param	t		Trip object to compare with the object that receives the message
	 * @return      	int value containing -1 if our object is "lower" than t, 1 if it is "bigger" and 0 if equal.
	 */
	public int compareTo(Trip t) {
		if (this.id < t.getId()) return -1;
		if( this.id > t.getId() ) return 1;
		else return 0;
   }

   /**
    * 
    */
    public int hashCode(){
        return ((Integer) this.id).hashCode();
    }


}

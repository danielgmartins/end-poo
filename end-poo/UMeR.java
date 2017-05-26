
/**
 * Write a description of class UMeR here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.io.Serializable;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import Exceptions.*;

public class UMeR implements Serializable
{
    private static boolean isLogged;
    private static String loggedUserEmail;
    private static HashMap<String, User> userList;
    private static HashMap<String, Vehicle> vehicleList;
    private static TreeMap<Integer, Trip> tripList;
    private static HashMap<String, String> driverVehicle;
    private static int tripNumber;

    /** 
     * Initializes UMeR
     */
    public UMeR (){
        this.tripNumber = 0;
        this.isLogged = false;
        this.loggedUserEmail = null;
        this.setUserList(new HashMap<String, User>());
        this.setVehicleList(new HashMap<String, Vehicle>());
        this.setTripList(new TreeMap<Integer, Trip>());
        this.setDriverVehicle(new HashMap<String,String>());
    }

    /**
     * 
     */
    public void printString(){
        StringBuilder sb = new StringBuilder();

        sb.append("\nisLogged: ");
        sb.append(this.isLogged);
        sb.append("\nloggedUserEmail: ");
        sb.append(this.loggedUserEmail);
        sb.append("\nuserList: ");
        sb.append(this.userList.toString());
        sb.append("\nvehiclList: ");
        sb.append(this.vehicleList.toString());
        sb.append("\ntripList: ");
        sb.append(this.tripList.toString());
        sb.append("\ndriverVehicle: ");
        sb.append(this.driverVehicle.toString());
        sb.append("\ntripNumber: ");
        sb.append(this.tripNumber);

        System.out.println(sb.toString());
    }

    //  ----------  GETTERS  -----------  //

    /**
     * 
     */
    public String getUserLogged(){
        return new String(this.loggedUserEmail);
    }

    /**
     * 
     */
    public User getLoggedUserObject (String email) throws UserNonExistent{
        return this.userList.get(email).clone();
    }

    /**
     * 
     */
    public Vehicle getVehicleObject (String licensePlate) throws VehicleNonExistent{
        if(!this.tripList.containsKey(licensePlate))
            throw new VehicleNonExistent("Vehicle with this license plate does not exist.");
        return this.vehicleList.get(licensePlate).clone();
    } 

    /**
     * 
     */
    public Trip getTripObject (int id) throws TripNonExistent {
        if (!this.tripList.containsKey(id))
            throw new TripNonExistent("No trip available with this id");
        else
            return this.tripList.get(id);
    }

    /**
     * 
     */
    public String getUserType() throws NoUserLoggedException {
        if(this.loggedUserEmail != null)
            return this.userList.get(loggedUserEmail).getClass().getSimpleName();
        else
            throw new NoUserLoggedException();
    }


    //  ----------  SETTERS  -----------  //

    /** Sets logged variable to true or false
     * @param logged boolean value to set Logged state
     */
    public void setLogged(boolean logged){
        this.isLogged = logged;
    }

    /** 
     * 
     */
    public void setUserList(HashMap<String, User> list){
        this.userList = list;
    }

    /**
     * 
     */
    public void setVehicleList(HashMap<String, Vehicle> list){
        this.vehicleList = list;
    }

    /**
     * 
     */
    public void setTripList(TreeMap<Integer, Trip> list){
        this.tripList = list;
    }

    /**
     * 
     */
    public void setDriverVehicle(HashMap<String, String> list){
        this.driverVehicle = list;
    }

    //  ----------  QUESTIONING  ----------  //

    /**
     * Says if someone is logged in
     * @return true if someone is logged in, false otherwise
     */
    public boolean isLogged(){
        return this.isLogged;
    }

    /**
     * Checks if a user with a given email and password (hashed) exist
     */
    public boolean isValidUser (String email, String password){
        if (!this.userList.containsKey(email))
            return false;

        if (this.userList.get(email).confirmPass(password))
            return true;
        return false;
    }

    /**
     * 
     */
    public boolean isEmailAvailable (String email){
        if (this.userList.containsKey(email))
            return false;
        return true;
    }

    /**
     * 
     */
    public boolean isLicensePlateAvailable (String license) {
        if (this.vehicleList.containsKey(license))
            return false;

        return true;
    }

    /**
     * 
     */
    public boolean isVehicleAvailable (String licensePlate) throws LicensePlateUnavailable{
        if(!this.isLicensePlateAvailable(licensePlate))
            throw new LicensePlateUnavailable();
        
        return this.vehicleList.get(licensePlate).getAvailability();
    }

    /**
     * 
     */
    public boolean isClient(){
        return this.userList.get(this.loggedUserEmail) instanceof Client;
    }


    /**
     * 
     */
    public boolean isDriver(){
        return this.userList.get(this.loggedUserEmail) instanceof Driver;
    }

    //  ----------  ADD DATA  -----------  //

    /**
     * 
     * @throws 
     */
    public void addUser (User user) throws EmailUnavailable {
        if ( !this.isEmailAvailable(user.getEmail()) )
            throw new EmailUnavailable("Email already in use");
        else
            this.userList.put(user.getEmail(), user.clone());
    }

    /**
     * Adds a new Vehicle to the database
     * @param vehicle   New Vechicle to be added
     * @throws licensePlateUnavailable  if a vehicle with this license plate already exists
     */
    public void addVehicle (Vehicle vehicle) throws LicensePlateUnavailable {
        if( !this.isLicensePlateAvailable(vehicle.getLicensePlate()) )
            throw new LicensePlateUnavailable("License plate already in use");
        
        this.vehicleList.put(vehicle.getLicensePlate(), vehicle.clone() );
        this.driverVehicle.put(this.loggedUserEmail, vehicle.getLicensePlate());
    }

    /**
     * 
     */
    public void addTrip (Trip trip){
        this.tripList.put(trip.getId(), trip.clone());
        this.userList.get(trip.getDriver().getEmail()).addTripToHistory(trip);
        this.userList.get(trip.getClient().getEmail()).addTripToHistory(trip);
        this.vehicleList.get(trip.getTaxi().getLicensePlate()).setLocation(trip.getDestination());
    }

    //  ----------  SESSION METHODS  ---------- //

    /**
     * Logs in a user given it's email and password
     * @param email                 User email
     * @param password              Hashed password of user
     * @throws WrongEmailOrPassword if a user with that email does not exist, or if the password for that email is wrong
     */
    public void logIn(String email, String password) throws WrongEmailOrPassword{
        if (!this.userList.containsKey(email) || !this.isValidUser(email,password))
            throw new WrongEmailOrPassword("Wrong email or password.");
        
        this.loggedUserEmail = new String(email);
        this.setLogged(true);
    }

    /** 
     * Logs out user.
     * By changing it to not logged state, and eliminating logged user info from current state
     */
    public void logOut(){
        this.setLogged(false);
        this.loggedUserEmail = null;
    }

    /**
     * Changes password of currently logged user
     * @param password  String with new (hashed) password
     */
    public void changePassword (String password) {
        this.userList.get(loggedUserEmail).setPassword(password);
    }

    /**
     * Changes address of corrently logged user
     * @param address   new Address for user
     */
    public void changeAddress (Address address){
        this.userList.get(loggedUserEmail).setAddress(address);
    }
    
    //  ----------  QUERY  ----------  //

    /**
     * 
     */
    public String getThisClientProfileString () throws NoUserLoggedException, UserIsNotClientException {
        if (this.loggedUserEmail == null)
            throw new NoUserLoggedException();      // Este caso nunca acontece
        if (!isClient() )
            throw new UserIsNotClientException();   // Este caso nunca acontece
        
        Client user = (Client) this.userList.get(loggedUserEmail);
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nName: ");
        sb.append(user.getName());
        sb.append("\nEmail: ");
        sb.append(user.getEmail());
        sb.append("\nAddress: ");
        sb.append(user.getAddress().getCity());
        sb.append(", ");
        sb.append(user.getAddress().getCountry());
        sb.append("\nBirthday: ");
        sb.append(user.getBirthday().toString());
        sb.append("\n");

        return sb.toString();
    }

    /**
     * 
     */
    public String getThisDriverProfileString () throws NoUserLoggedException, UserIsNotClientException{
        if (this.loggedUserEmail == null)
            throw new NoUserLoggedException();      // Este caso nunca acontece
        if (! isDriver() )
            throw new UserIsNotClientException();   // Este caso nunca acontece
        
        Driver user = (Driver) this.userList.get(loggedUserEmail);
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nName: ");
        sb.append(user.getName());
        sb.append("\nEmail: ");
        sb.append(user.getEmail());
        sb.append("\nAddress: ");
        sb.append(user.getAddress().getCity());
        sb.append(" ,");
        sb.append(user.getAddress().getCountry());
        sb.append("\nBirthday: ");
        sb.append(user.getBirthday().toString());
        sb.append("\nTotal revenue:");
        sb.append(user.getTotalTripCost());
        sb.append("\n");

        return sb.toString();
    }    

    /**
     * 
     */
    public String getTripHistoryString(LocalDate firstDate, LocalDate lastDate){
        Trip trip;
        StringBuilder sb = new StringBuilder("Trip History:\n");

        for(int id: this.userList.get(loggedUserEmail).getTripHistory()){
            trip = this.tripList.get(id);
            if(trip.getDate().toLocalDate().isBefore(lastDate)){
                break;
            }
            if( trip.getDate().toLocalDate().isAfter(firstDate) ){
                //newList.add(trip.clone());
                sb.append("\nDate:\n");
                sb.append(trip.getDate().toString());
                sb.append("\nDriver: ");
                sb.append(trip.getDriver().getName());
                sb.append("\nDestination: ");
                sb.append(trip.getDestination());
                sb.append("\nTrip duration: ");
                sb.append(trip.getRealTripTime());
                sb.append("\nCost: ");
                sb.append(trip.getRealTripCost());
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * 
     */
    public List<User> get10MostSpendingClients (){
        Comparator<User> byTotalTripCost = (u1, u2) -> Double.compare(u2.getTotalTripCost(), 
                                                                      u1.getTotalTripCost()
                                                                      );

        return this.userList.values()
                            .stream()
                            .filter( u -> u instanceof Client)
                            .sorted(new ComparatorUserTotalTripCost())
                            .limit(10)
                            .map(User::clone)
                            .collect(Collectors.toList());
        
    }

    /**
     * 
     */
    public Vehicle getNearestVehicle(){
        if(this.isDriver()) return null;
        Coordinates userLocation = ( (Client) this.userList.get(this.loggedUserEmail) ).getLocation();
        
        TreeMap<Double,Vehicle> treeVehicles = new TreeMap<Double, Vehicle>();
        this.vehicleList.values().forEach( v -> treeVehicles.put( userLocation.distance(v.getLocation()), v ) );
        
        return treeVehicles.get(treeVehicles.firstKey());
    }

    /**
     * Makes a trip With nearest vehicle available
     */
    public void makeTrip(Coordinates clientCoordinates){

    }

    /**
     * Makes a trip using a vehicle requeste by a given license plate
     * @param clientCoordinates     Client's coordinates
     * @param licensePlate          Requested vehicles's license plate
     * 
     * @throws VehicleNotAvailableException if no vehicle exists with that license plate
     * @throws NoSuchVehicleException       if
     */
    public void requestTrip(Coordinates clientCoordinates, String licensePlate) throws VehicleNotAvailable, NoSuchVehicleException, VehicleNotAvailable, LicensePlateUnavailable{
        if(!this.isLicensePlateAvailable(licensePlate))
            throw new NoSuchVehicleException();
        
        if(!this.isVehicleAvailable(licensePlate))
            throw new VehicleNotAvailable();
    }

    /**
     * Top 5 driveres with the most deviation of Real Trip time to Estimated Trip Time
     * @return string with a list of names an emails of the drivers and number of deviations
     */
    public String worstPreformingDrivers() {
        
        // Gets trip where trip time was >25% longer than expected
        List<Trip> tripsBadPerformance 
                    = new LinkedList<Trip>( this.tripList.values()
                                                .stream()
                                                .filter( trip -> trip.getRealTripTime() * 1.25 > trip.getEstimatedTripTime() )
                                                .collect(Collectors.toList())
                                                );
        
        // Gets a map of drivers in the last list, and how many bad trips they had
        Map<Driver,Integer> driverPerformance = new HashMap<Driver,Integer>();

        tripsBadPerformance.forEach( 
            trip -> 
            { if (driverPerformance.containsKey(trip.getDriver())){
                driverPerformance.put(trip.getDriver(), driverPerformance.get(trip.getDriver() ) +1);
                }else {
                driverPerformance.put(trip.getDriver(), 1);
                }
            } );
        
        // Gets a list of the top five worst perfromers
        List<Driver> topFive = new LinkedList<Driver>( driverPerformance.entrySet()
                                                                        .stream()
                                                                        .sorted((e1, e2) -> e1.getValue()
                                                                                              .compareTo(e1.getValue()))
                                                                        .limit(5)
                                                                        .map(Map.Entry :: getKey)
                                                                        .collect(Collectors.toList())
                                                     );
        // Gets a String of the previous list
        StringBuilder sb = new StringBuilder();
        for(Driver d : topFive){
            sb.append("\nName: ");
            sb.append(d.getName());
            sb.append("\nEmail: ");
            sb.append(d.getEmail());
            sb.append("Number of deviations: ");
            sb.append(driverPerformance.get(d));
            sb.append("\n");
        }
        
        return sb.toString();
    }

}

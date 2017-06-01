
/**
 * Write a description of class UMeR here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import Exceptions.*;

public class UMeR implements Serializable
{
    private boolean isLogged;                   /* Says if someone is logged in */
    private String loggedUserEmail;             /* String with currently logged user's email. Null if no one is logged in */
    private Map<String, User> userList;         /* Map with users, user's email (string) is the key to it's user */
    private Map<String, Vehicle> vehicleList;   /* Map with vehicles, vehicles's license plate (String) is the key to it's vehicle */
    private Map<Integer, Trip> tripList;        /* Map with trips, trip's id (int) is the key to it's trip */
    private Map<String, String> driverVehicle;  /* Map that associates a driver (email) with a vehicle (license plate) */
    private int tripNumber;                     /* Numeber og trips in tripList, used for setting trip id */

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
     * Writes UMeR to string form
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("\nIS_LOGGED: ");
        sb.append(this.isLogged);
        sb.append("\nLOGGED_USER_EMAIL: ");
        sb.append(this.loggedUserEmail);
        sb.append("\n\nUSER_LIST: ");
        sb.append(this.userList.toString());
        sb.append("\n\nVEHICLE_LIST: ");
        sb.append(this.vehicleList.toString());
        sb.append("\n\nTRIP_LIST: ");
        sb.append(this.tripList.toString());
        sb.append("\n\nDRIVER_VEHICLE: ");
        sb.append(this.driverVehicle.toString());
        sb.append("\n\nTRIP_NUMBER: ");
        sb.append(this.tripNumber);

        return sb.toString();
    }

    /**
     * Prints UMeR string to comandline
     */
    public void printString(){
        System.out.println(this.toString());
    }

    //  ----------  GETTERS  -----------  //

    /**
     * Gets email of currently logged user
     * @return String with currently logged's user email
     */
    public String getUserLogged(){
        return new String(this.loggedUserEmail);
    }

    /**
     * Gets user object of a a user with a given email
     * @param email String with email of the user being requested
     * @return User object clone of currently requested user
     * @throws UserNonExistent  if no User exists with the given email
     */
    public User getUserObject (String email) throws UserNonExistent{
        return this.userList.get(email).clone();
    }

    /**
     * Gets vehicle object of a vehicle with a given license plate
     * @param licensePlate  String with vehicle license plate you are requesting
     * @return Vehicle object with the given license plate
     * @throws VehicleNonExistent   if no vehicle exists with this license plate
     */
    public Vehicle getVehicleObject (String licensePlate) throws VehicleNonExistent{
        if(!this.tripList.containsKey(licensePlate))
            throw new VehicleNonExistent("Vehicle with this license plate does not exist.");
        return this.vehicleList.get(licensePlate).clone();
    } 

    /**
     * Gets trip object of a trip with a given id
     * @param id    int with trip id of the trip you are requesting
     * @return  Trip object of requested trip
     * @throws TripNonExistent  if no trip exists with the given id
     */
    public Trip getTripObject (int id) throws TripNonExistent {
        if (!this.tripList.containsKey(id))
            throw new TripNonExistent("No trip available with this id");
        else
            return this.tripList.get(id);
    }

    /**
     * Gets simple name of class of the currently logged user
     * @return String with currently logged user's class simple name
     * @throws NoUserLoggedException if no user is logged
     */
    public String getUserType() throws NoUserLoggedException {
        if(this.loggedUserEmail != null)
            return this.userList.get(loggedUserEmail).getClass().getSimpleName();
        else
            throw new NoUserLoggedException();
    }

    /**
     * Gets the vehicle of a given driver
     * @param driverEmail   String with email of the driver who's vehicle you are requesting
     * @return  Vehicle object of the vehicle belonging to the driver email passed as parameter
     * @throws  UserNonExistent             if no user exists with the given email
     * @throws  DriverHasNoVehicleException if the user with given email has no vehicle associated with them
     * @throws  UserIsNotDriverException    if thw user with given email is not a driver
     */
    public Vehicle getDriversVehicle(String driverEmail) throws UserNonExistent, DriverHasNoVehicleException, UserIsNotDriverException {
        if(!this.userList.containsKey(driverEmail))
            throw new UserNonExistent();
        if(! (this.userList.get(driverEmail) instanceof Driver))
            throw new UserIsNotDriverException();
        if(!this.driverVehicle.containsKey(driverEmail))
            throw new DriverHasNoVehicleException();
        
        return this.vehicleList.get( this.driverVehicle.get(driverEmail) ).clone();
    }


    //  ----------  SETTERS  -----------  //

    /**
     * Changes the average speed of a vehicle with a given license plate to a new given speed
     * @param licensePlate  String of the vehicle to be updated
     * @param newSpeed      double with new average speed to be set
     * @throws VehicleNonExistent   if no vehicle exists with the given license plate
     * @throws DriverNotAuthorized  if the vehicle with given license plate does not belong to the currently logged driver
     */
    public void setAverageSpeed(String licensePlate, double newSpeed) throws VehicleNonExistent, DriverNotAuthorized{
        if(! this.vehicleList.containsKey(licensePlate))
            throw new VehicleNonExistent();
        if(! this.driverVehicle.get(this.loggedUserEmail).equals(licensePlate))
            throw new DriverNotAuthorized();

        this.vehicleList.get(licensePlate).setAverageSpeed(newSpeed);
    }

    /**
     * Changes the fare of a vehicle with a given license plate to a new fare
     * @param licensePlate  String of the vehicle to be updated
     * @param fare          double with new fare to be set
     * @throws VehicleNonExistent   if no vehicle exists with the given license plate
     * @throws DriverNotAuthorized  if the vehicle with given license plate does not belong to the currently logged driver
     */
    public void setFare(String licensePlate, double fare) throws VehicleNonExistent, DriverNotAuthorized{
        if(! this.vehicleList.containsKey(licensePlate))
            throw new VehicleNonExistent();
        if(! this.driverVehicle.get(this.loggedUserEmail).equals(licensePlate))
            throw new DriverNotAuthorized();

        this.vehicleList.get(licensePlate).setFare(fare);
    }

    /**
     * Set's user location
     * @param coord Coordinates of new location to be update Client to
     */
    public void setUserLocation(Coordinates coord){
        ( (Client) this.userList.get(this.loggedUserEmail) ).setLocation(coord);
    }

    /**
     * Set's new lovation to vehicle of currently logged driver
     * @param coord Coordinates of new location to be update currently logged driver's vehicle to
     */
    public void setDriverVehicleLocation (Coordinates coord){
        this.vehicleList.get(this.driverVehicle.get(this.loggedUserEmail)).setLocation(coord);
    }


    /** 
     * Sets logged variable to true or false
     * @param logged boolean value to set Logged state
     */
    private void setLogged(boolean logged){
        this.isLogged = logged;
    }

    /** 
     * Set user list
     * @param list  Map to set UserList to
     */
    private void setUserList(Map<String, User> list){
        this.userList = new HashMap<String, User>();
        for(String key : list.keySet()){
            this.userList.put(new String(key), list.get(key).clone());
        }
    }

    /** 
     * Set vehicle list
     * @param list  Map to set vehicle List to
     */
    private void setVehicleList(Map<String, Vehicle> list){
        this.vehicleList = new HashMap<String,Vehicle>();
        for(String key : list.keySet()){
            this.vehicleList.put(new String(key), list.get(key).clone());
        }
    }

    /** 
     * Set trip list
     * @param list  Map to set trip list to
     */
    private void setTripList(TreeMap<Integer, Trip> list){
        this.tripList = list;
        for(Integer key : list.keySet()){
            this.tripList.put(key, list.get(key).clone());
        }
    }

    /** 
     * Set driver vehicle map
     * @param list  Map to set driverVehicle to
     */
    private void setDriverVehicle(HashMap<String, String> list){
        this.driverVehicle = list;
        for(String key : list.keySet()){
            this.driverVehicle.put(new String(key), new String(list.get(key)));
        }
    }
    
    /**
     * Change the availability of a given driver
     * @param driver    String with email of the driver which's availability will be changed
     * @param available boolean with the value to which the driver's availability will be set
     * @throws  UserNonExistent             if no user exists with the given email
     * @throws UserIsNotDriverException     if user with given email is not a driver
     */
    public void setDriverAvailability(String driver, boolean available) throws UserNonExistent, UserIsNotDriverException{
        if(!this.userList.containsKey(driver))
            throw new UserNonExistent();
        if(! (this.userList.get(driver) instanceof Driver))
            throw new UserIsNotDriverException();

        ((Driver) this.userList.get(driver)).setAvailability(available);
    }

    /**
     * Gives new classification to given driver
     * @param driverEmail       String with email of driver to give classification to
     * @param classification    int with classification to give to driver. must be between 0 and 100
     * @throws UserNonExistent  if no user exists with the given email
     * @throws NotValidValue    if classification is not between 0 and 100
     */
    public void giveClassification(String driverEmail, int classification) throws UserNonExistent, NotValidValue{
        if(!this.userList.containsKey(driverEmail))
            throw new UserNonExistent();
        if(classification > 100 || classification < 0)
            throw new NotValidValue();
        
        ((Driver) this.userList.get(driverEmail)).addClassification(classification);
    }

    //  ----------  QUESTIONING  ----------  //

    /**
     * Checks if the driver currently logged has a vehile
     * @return  boolean value. true if currently logged driver has a vehicle associated, false otherwise
     * @throws NoUserLoggedException    if no user is currently logged
     * @throws UserIsNotDriverException if currently logged user is not a driver
     */
    public boolean hasVehicle() throws NoUserLoggedException, UserIsNotDriverException{
        if(this.loggedUserEmail == null)
            throw new NoUserLoggedException();
        if(! this.driverVehicle.containsKey(this.loggedUserEmail))
            throw new UserIsNotDriverException();

        return this.driverVehicle.containsKey(this.loggedUserEmail);
    }

    /**
     * Says if someone is logged in
     * @return true if someone is logged in, false otherwise
     */
    public boolean isLogged(){
        return this.isLogged;
    }

    /**
     * Checks if a user with a given email and password (hashed) exist
     * @param email     String with email to validate
     * @param password  String with password (already hashed) to validate
     * @return true if combination of email and password is a valid user, false otherwise
     */
    public boolean isValidUser (String email, String password){
        if (!this.userList.containsKey(email))
            return false;

        return this.userList.get(email).confirmPass(password);
    }

    /**
     * Checks if a given email is available or already in use
     * @param email String of email to be checked
     * @returns true if email is available to be used. false if email is already in use
     */
    public boolean isEmailAvailable (String email){
        if (this.userList.containsKey(email))
            return false;
        return true;
    }

    /**
     * Checks if a given license plate is available or already in use
     * @param licensePlate  String with license plate to be checked
     * @return true if license plate is available. false if license plate is already in use
     */
    public boolean isLicensePlateAvailable (String license) {
        return this.vehicleList.containsKey(license);
    }

    /**
     * Check if a given driver's is available for request
     * @param driverEmail  Strind with driver's email
     * @return boolean true if the driver with that email is available, false otherwise.
     * @throws UserNonExistent          if no such user exists.
     * @throws UserIsNotDriverException if user with given email is not a driver
     */
    public boolean isDriverAvailable (String driverEmail) throws UserNonExistent, UserIsNotDriverException{
        if(this.isEmailAvailable(driverEmail))
            throw new UserNonExistent("No such user\n");
        if(! (this.userList.get(driverEmail) instanceof Driver ))
            throw new UserIsNotDriverException();
        
        return ((Driver) this.userList.get(driverEmail)).getAvailability();
    }

    /**
     * Checks if driver with given email has a vehicle associated to them
     * @param driverEmail   String with email of user to check
     * @return true if user with given email has vehicle associated to him. false otherwise
     */
    public boolean checkDriverHasVehicle (String driverEmail){
        return this.driverVehicle.containsKey(driverEmail);
    }

    /**
     * Check if the currently logged user is a Client
     * @return boolean with true currently logged user is Client, false otherwise.
     */
    public boolean isClient(){
        return this.userList.get(this.loggedUserEmail) instanceof Client;
    }


    /**
     * Check if the currently logged user is a Driver
     * @return boolean with true currently logged user is Driver, false otherwise.
     */
    public boolean isDriver(){
        return this.userList.get(this.loggedUserEmail) instanceof Driver;
    }

    /**
     * Checks if a driver already has a vehicle associated to him.
     * @return boolean with true if driver already has vehicle, false otherwise.
     */
    public boolean alreadyHasVehicle(){
        return this.driverVehicle.containsKey(this.loggedUserEmail);
    }

    //  ----------  ADD DATA  -----------  //

    /**
     * Adds new user to database.
     * @param user  User to be added to database.
     * @throws EmailUnavailable if this user's email is already in use.
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
     * Adds new Trip to date base.
     * Updates the corresponding driver and client's trip history.
     * Updates Vehicle locations
     * @param trip  Trip to add
     * @throws UserNonExistent          if either client or driver in the trip do not exist
     * @throws UserIsNotClientException if trip client is not a client
     * @throws UserIsNotDriverException if trip driver is not a driver
     * @throws VehicleNonExistent       if trip vehicle does not exist
     * @throws DriverNotAuthorized      if trip driver does not own trip vehicle
     */
    public void addTrip (Trip trip) throws UserNonExistent, UserIsNotClientException, UserIsNotDriverException, VehicleNonExistent, DriverNotAuthorized{
        if(! this.userList.containsKey(trip.getClient()) || ! this.userList.containsKey(trip.getDriver()))
            throw new UserNonExistent();
        if(! (this.userList.get(trip.getClient()) instanceof Client))
            throw new UserIsNotClientException();
        if(! (this.userList.get(trip.getDriver()) instanceof Driver))
            throw new UserIsNotDriverException();
        
        if(! this.vehicleList.containsKey(trip.getTaxi()))
            throw new VehicleNonExistent();
        if(! this.driverVehicle.get(trip.getDriver()).equals(trip.getTaxi()))
            throw new DriverNotAuthorized();

        this.tripList.put(trip.getId(), trip.clone());
        ((Driver) this.userList.get(trip.getDriver())).addTripToHistory(trip);
        ((Client) this.userList.get(trip.getClient())).addTripToHistory(trip);
        this.vehicleList.get(trip.getTaxi()).setLocation(trip.getDestination());
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
     * @throws NoUserLoggedException if no user is logged
     */
    public void changePassword (String password) throws NoUserLoggedException{
        if(this.loggedUserEmail == null)
            throw new NoUserLoggedException();
        this.userList.get(loggedUserEmail).setPassword(password);
    }

    /**
     * Changes address of corrently logged user
     * @param address   new Address for user
     * @throws NoUserLoggedException if no user is logged
     */
    public void changeAddress (Address address) throws NoUserLoggedException{
        if(this.loggedUserEmail == null)
            throw new NoUserLoggedException();
        this.userList.get(loggedUserEmail).setAddress(address);
    }
    
    //  ----------  INFORMATION STRINGS  ----------  //

    /**
     * Gives out string with information about the logged user, if it's a client.
     * Writes client's name, email, address, birthday and location
     * @return String with client information as string
     * @throws NoUserLoggedException    if no user is logged
     * @throws UserIsNotClientException if currently logged user is not a client
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
        sb.append("\nLocation: ");
        sb.append(user.getLocation());
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Gives out string with information about the logged user, if it's a driver.
     * Writes driver's name, email, address, birthday and total revenew
     * @return String with driver information as string
     * @throws NoUserLoggedException    if no user is logged
     * @throws UserIsNotClientException if currently logged user is not a driver
     */
    public String getThisDriverProfileString () throws DriverHasNoVehicleException, UserIsNotDriverException, NoUserLoggedException, UserIsNotClientException{
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
        if(this.driverVehicle.containsKey(this.loggedUserEmail))
            sb.append(this.getThisDriverVehicleProfile());
        return sb.toString();
    }    

    /**
     * Gives out string with information about the logged user's vehicle, if it's a driver.
     * Writes vehicle's license plate, average speed, total kms and location
     * @return String with driver information as string
     * @throws NoUserLoggedException        if no user is logged
     * @throws UserIsNotClientException     if currently logged user is not a driver
     * @throws DriverHasNoVehicleException  if currently logged driver does not have an associated vehicle
     */
    private String getThisDriverVehicleProfile() throws NoUserLoggedException, UserIsNotDriverException, DriverHasNoVehicleException{
        if (this.loggedUserEmail == null)
            throw new NoUserLoggedException();      // Este caso nunca acontece
        if (! isDriver() )
            throw new UserIsNotDriverException();   // Este caso nunca acontece
        if(!this.driverVehicle.containsKey(this.loggedUserEmail))
            throw new DriverHasNoVehicleException();

        StringBuilder sb = new StringBuilder();

        Vehicle vehicle = this.vehicleList.get(this.driverVehicle.get(this.loggedUserEmail));

        sb.append("\nVehicle ");
        sb.append(vehicle.getClass().getName());
        sb.append("\nLicense Plate: ");
        sb.append(vehicle.getLicensePlate());
        sb.append("\nAverage Speed: ");
        sb.append(vehicle.getAverageSpeed());
        sb.append("\nKms total: ");
        sb.append(vehicle.getKmsTotal());
        sb.append("\nLocation: ");
        sb.append(vehicle.getLocation());
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Gives out string with trip history of currently logged user. between given dates.
     * Writes trips' Date, client email, driver email, destination, real trip duration and real cost
     * @param firstDate earliest date to get trips from
     * @param lastDate  latest date to get trips from
     * @return String with trips' information between the two given dates
     * @throw NoUserLoggedException if not user is currently logged
     */
    public String getTripHistoryString(LocalDate firstDate, LocalDate lastDate) throws NoUserLoggedException{
        if(this.loggedUserEmail == null)
            throw new NoUserLoggedException();

        Trip trip;
        StringBuilder sb = new StringBuilder("Trip History:\n");

        for(int id: this.userList.get(loggedUserEmail).getTripHistory()){
            trip = this.tripList.get(id);
            if( trip.getDate().toLocalDate().isBefore(lastDate) && trip.getDate().toLocalDate().isAfter(firstDate) ){
                //newList.add(trip.clone());
                sb.append("\nDate:\n");
                sb.append(trip.getDate().toString());
                sb.append("\nClient: ");
                sb.append(trip.getClient());
                sb.append("\nDriver: ");
                sb.append(trip.getDriver());
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


    // ----------  QUERIES ----------  //

    /**
     * Gives list with copy of 10 users who spend the most ordered by spending in descending order
     * @return list with users who spend the most ordered by spending in descending order 
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
     * Top 5 driveres with the most deviation of Real Trip time to Estimated Trip Time
     * @return string with a list of names an emails of the drivers and number of deviations
     */
    public String worstPreformingDrivers() {
        
        // Gets trip where trip time was >25% longer than expected
        List<Trip> tripsBadPerformance 
                    = new LinkedList<Trip>( this.tripList.values()
                                                .stream()
                                                .filter( trip -> trip.getExpectedTripCost() * 1.25 > trip.getRealTripCost() )
                                                .collect(Collectors.toList())
                                                );

        
        // Gets a map of drivers in the last list, and how many bad trips they had
        Map<String,Integer> driverPerformance = new HashMap<String,Integer>();

        tripsBadPerformance.forEach( 
            trip -> 
            { if (driverPerformance.containsKey(trip.getDriver())){
                driverPerformance.put(trip.getDriver(), driverPerformance.get(trip.getDriver() ) +1);
                }else {
                driverPerformance.put(trip.getDriver(), 1);
                }
            } );

        
        // Gets a list of the top five worst perfromers
        List<String> topFive = new LinkedList<String>( driverPerformance.entrySet()
                                                                        .stream()
                                                                        .sorted((e1, e2) -> e1.getValue()
                                                                                              .compareTo(e1.getValue()))
                                                                        .limit(5)
                                                                        .map(Map.Entry :: getKey)
                                                                        .collect(Collectors.toList())
                                                     );

        // Gets a String of the previous list
        StringBuilder sb = new StringBuilder();
        User d;
        for(String email : topFive){
            d = this.userList.get(email);
            sb.append("\nName: ");
            sb.append(d.getName());
            sb.append("\nEmail: ");
            sb.append(email);
            sb.append("\nNumber of deviations: ");
            sb.append(driverPerformance.get(email));
            sb.append("\n");
        }
        
        return sb.toString();
    }


    /**
     * Get's email of nearest driver available
     * @param vehicleType   1 for car, 2 for Van, 3 for Motorcycle
     * @return String with driver and vehicle information of closest driver and vehicle
     * @throws NoVehicleAvailableException  if no vehicles are available
     * @throws NoUserLoggedException        if no user is logged
     * @throws UserIsNotClientException     if currently logged user is not a client
     */
    public String getNearestDriver(String vehicleType) throws NoVehicleAvailableException, NoUserLoggedException,UserIsNotClientException {

        if(this.loggedUserEmail == null)
            throw new NoUserLoggedException();
        if(!isClient())
            throw new UserIsNotClientException();

        SortedMap<Double, String> driversOrderedByDistance = new TreeMap<Double,String>();
        Coordinates userLocation = ((Client) this.userList.get(this.loggedUserEmail)).getLocation();
        User auxDriver;
        Vehicle auxVehicle;
        double distance;

        for(String driverEmail : this.userList.keySet()){
            auxDriver = this.userList.get(driverEmail);

            if(auxDriver instanceof Driver){
                if(((Driver) auxDriver).getAvailability()){
                    auxVehicle = this.vehicleList.get( this.driverVehicle.get(driverEmail) );
                    
                    if(auxVehicle.getClass().getSimpleName().equals(vehicleType)){
                        distance = userLocation.distance( auxVehicle.getLocation() );
                        driversOrderedByDistance.put(distance, driverEmail);
                    }
                }
            }
        }

        if(driversOrderedByDistance.size() == 0)
            throw new NoVehicleAvailableException();

        if( driversOrderedByDistance.get( driversOrderedByDistance.firstKey()) == null)
            throw new NoVehicleAvailableException();
            
        return driversOrderedByDistance.get( driversOrderedByDistance.firstKey());
    }

    /**
     * Gets string describing nearest vehicles, ordered by closeness
     * Writes driver name, email, classification and performance, and vehicle license plate, average speed, fare and reliability
     * @return String string describing nearest vehicles, ordered by closeness
     * @throws NoUserLoggedException    if no user is currently logged
     * @throws UserIsNotClientException if currently logged user is not a client
     */
    public String getNearestVehiclesString() throws NoUserLoggedException, UserIsNotClientException {

        if(this.loggedUserEmail == null)
            throw new NoUserLoggedException();
        if(!isClient())
            throw new UserIsNotClientException();

        StringBuilder sb = new StringBuilder();
        SortedMap<Double, String> driversOrderedByDistance = new TreeMap<Double,String>();
        Coordinates userLocation = ((Client) this.userList.get(this.loggedUserEmail)).getLocation();
        User auxUser;
        Vehicle auxVehicle;
        double distance;

        for(String driverEmail : this.userList.keySet()){
            auxUser = this.userList.get(driverEmail);

            if(auxUser instanceof Driver){
                if(((Driver) auxUser).getAvailability()){
                    auxVehicle = this.vehicleList.get( this.driverVehicle.get(driverEmail) );
                    distance = userLocation.distance( auxVehicle.getLocation() );
                    driversOrderedByDistance.put(distance, driverEmail);
                }
            }
        }


        
        String driverEmail = driversOrderedByDistance.remove(driversOrderedByDistance.firstKey());
        Driver auxDriver = (Driver) this.userList.get(driverEmail);
        while(auxDriver != null){
            auxVehicle = this.vehicleList.get( this.driverVehicle.get(driverEmail));
            sb.append("\nDriver - Name: ");
            sb.append(auxDriver.getName());
            sb.append("Email: ");
            sb.append(auxDriver.getEmail());
            sb.append("\nClassifications: ");
            sb.append(auxDriver.getClassification());
            sb.append("\nPerformance: ");
            sb.append(auxDriver.getPerformance());
            sb.append("\nVehicle - License Plate: ");
            sb.append(auxVehicle.getLicensePlate());
            sb.append("\nAverage Speed: ");
            sb.append(auxVehicle.getAverageSpeed());
            sb.append("\nFare: ");
            sb.append(auxVehicle.getFare());
            sb.append("\nReliability");
            sb.append(auxVehicle.getReliability());
            sb.append("\n");

            driverEmail = driversOrderedByDistance.remove(driversOrderedByDistance.firstKey());
            auxDriver = (Driver) this.userList.get(driverEmail);
        }
        
        return sb.toString();
    }
    

    /**
     * Makes a trip With nearest vehicle available
     * @param driver				String with email of client who requests the trip
	 * @param date  				String with email of Driver who performs the trip
	 * @param taxi 					String of vehicle licensePlate who performs the trip
	 * @param taxiLocation 			Coordinates object with taxi's location
	 * @param clientLocation 		Coordinates object with client's location
	 * @param destination 			Coordinates object with the trip's destination
	 * @param estimatedTripTime 	estimated time of the trip
	 * @param realTripTime 			time of the trip
	 * @param expectedTripCost 		cost of trip
     * @param realTripCost			real cost of trip
     * @throws NoUserLoggedException        if no user is logged
     * @throws UserIsNotClientException     if currently logged user is not a client
     */
    public void makeTrip(String driver, LocalDateTime date, String taxi, Coordinates taxiLocation, Coordinates clientLocation, Coordinates destination, int estimatedTripTime,int realTripTime,double expectedTripCost, double realTripCost) throws DriverNotAuthorized, VehicleNonExistent, UserIsNotDriverException, UserNonExistent, NoUserLoggedException, UserIsNotClientException {

        if(this.loggedUserEmail == null)
            throw new NoUserLoggedException();
        if(!isClient())
            throw new UserIsNotClientException();

        this.tripNumber +=1;
        Trip newTrip = new Trip(this.tripNumber,
                                this.loggedUserEmail,
                                driver,
                                date,
                                taxi,
                                taxiLocation,
                                clientLocation,
                                destination,
                                estimatedTripTime,
                                realTripTime,
                                expectedTripCost,
                                realTripCost
                                );
        this.addTrip(newTrip);
    }

    /**
     * Sets reliability of a given vehicle (sets a new random double)
     * @param licensePlate  String with vehicle to set reliability to
     * @throws VehicleNonExistent   if no vehicle exists with given license plate
     */
    public void setReliability(String licensePlate) throws VehicleNonExistent{
        if(!this.vehicleList.containsKey(licensePlate))
            throw new VehicleNonExistent();
            
        this.vehicleList.get(licensePlate).setReliability();
    }

    /**
     * Gets reliability of a given vehicle
     * @return double with given vehicle reliability
     * @throws VehicleNonExistent   if no vehicle exists with given license plate
     */
    public double getReliabilityOfVehicle(String licensePlate) throws VehicleNonExistent{
        if(!this.vehicleList.containsKey(licensePlate))
            throw new VehicleNonExistent();

        return this.vehicleList.get(licensePlate).getReliability();
    }

}

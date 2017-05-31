
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
    private boolean isLogged;
    private String loggedUserEmail;
    private HashMap<String, User> userList;
    private HashMap<String, Vehicle> vehicleList;
    private TreeMap<Integer, Trip> tripList;
    private HashMap<String, String> driverVehicle;
    private int tripNumber;

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
     * 
     */
    public void printString(){
        System.out.println(this.toString());
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

    /**
     * Gets the vehicle of a given driver
     */
    public Vehicle getDriversVehicle(String driverEmail){
        // check if driver exists
        // check if has vehicle
        return this.vehicleList.get( this.driverVehicle.get(driverEmail) ).clone();
    }

    /**
     * Gets the user ojvect of a given driver email
     */
    public User getDriverObject(String driverEmail) throws UserNonExistent, UserIsNotDriverException{
        if(! this.userList.containsKey(driverEmail))
            throw new UserNonExistent();
        if(! (this.userList.get(driverEmail) instanceof Driver))
            throw new UserIsNotDriverException();
        
        return this.userList.get( driverEmail ).clone();
    }


    //  ----------  SETTERS  -----------  //

    /**
     * Set's user location
     */
    public void setUserLocation(Coordinates coord){
        ( (Client) this.userList.get(this.loggedUserEmail) ).setLocation(coord);
    }

    /**
     * Set's user location
     */
    public void setDriverVehicleLocation (Coordinates coord){
        this.vehicleList.get(this.driverVehicle.get(this.loggedUserEmail)).setLocation(coord);
    }


    /** Sets logged variable to true or false
     * @param logged boolean value to set Logged state
     */
    private void setLogged(boolean logged){
        this.isLogged = logged;
    }

    /** 
     * 
     */
    private void setUserList(HashMap<String, User> list){
        this.userList = list;
    }

    /**
     * 
     */
    private void setVehicleList(HashMap<String, Vehicle> list){
        this.vehicleList = list;
    }

    /**
     * 
     */
    private void setTripList(TreeMap<Integer, Trip> list){
        this.tripList = list;
    }

    /**
     * 
     */
    private void setDriverVehicle(HashMap<String, String> list){
        this.driverVehicle = list;
    }
    
    /**
     * 
     */
    public void setDriverAvailability(String driver, boolean available){
        ((Driver) this.userList.get(driver)).setAvailability(available);
    }

    /**
     * 
     */
    public void giveClassification(String driverEmail, int classification){
        // check if driver exists
        // check classification value
        ((Driver) this.userList.get(driverEmail)).addClassification(classification);
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
     * @param
     * @return 
     */
    public boolean isLicensePlateAvailable (String license) {
        if (this.vehicleList.containsKey(license))
            return false;

        return true;
    }

    /**
     * Check if a given driver's is available for request
     * @param driverEmail  Strind with driver's email
     * @return boolean true if the driver with that email is available, false otherwise.
     * @throws UserNonExistent if no such user exists.
     */
    public boolean isDriverAvailable (String driverEmail) throws UserNonExistent{
        if(this.isEmailAvailable(driverEmail))
            throw new UserNonExistent("No such user\n");
        
        return ((Driver) this.userList.get(driverEmail)).getAvailability();
    }

    /**
     * Checks if driver with given email has a vehicle
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
     * 
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
     * @param trip  Trip do add
     */
    public void addTrip (Trip trip){
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
    
    //  ----------  INFORMATION STRINGS  ----------  //

    /**
     * Gives out string with information about the logged user, if it's a client.
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
        if(this.driverVehicle.containsKey(this.loggedUserEmail))
            sb.append(this.getThisDriverVehicleProfile());
        return sb.toString();
    }    

    /**
     * 
     */
    private String getThisDriverVehicleProfile(){
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
     * 
     */
    public String getTripHistoryString(LocalDate firstDate, LocalDate lastDate){
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
     * @return
     */
    public String getNearestDriver(int vehicleType) throws NoVehicleAvailableException {

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
                    switch(vehicleType){
                        case 1:
                            if(auxVehicle instanceof Car){
                                distance = userLocation.distance( auxVehicle.getLocation() );
                                driversOrderedByDistance.put(distance, driverEmail);
                            }
                            break;
                        case 2:
                            if(auxVehicle instanceof Van){
                                distance = userLocation.distance( auxVehicle.getLocation() );
                                driversOrderedByDistance.put(distance, driverEmail);
                            }
                            break;
                        case 3:
                            if(auxVehicle instanceof Motorcycle){
                                distance = userLocation.distance( auxVehicle.getLocation() );
                                driversOrderedByDistance.put(distance, driverEmail);
                            }
                            break;
                        default:
                            break;
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
     * 
     */
    public String getNearestVehiclesString(){
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
     */
    public void makeTrip(String driver, LocalDateTime date, String taxi, Coordinates taxiLocation, Coordinates clientLocation, Coordinates destination, int estimatedTripTime,int realTripTime,double expectedTripCost, double realTripCost){

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
        
    }

}


/**
 * Write a description of class UMeR here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.io.Serializable;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream;

public class UMeR implements Serializable
{
    private static String loggedUserEmail;
    private static HashMap<String, User> userList;
    private static HashMap<String, Vehicle> vehicleList;
    private static TreeMap<int, Trip> tripList;

    /**
     * 
     */
    public static UMeR (){
        this.loggedUserEmail = NULL;
        this.setClientList(new HashMap<String, User>());
        this.setVehicleList(new HashMap<String, Vehicle>());
        this.setTripList(new TreeMap<int, Trip>());
    }

    /**
     * 
     * @throws 
     */
    public void addUser (User user) throws EmailUnavailable {
        if ( !this.isEmailAvailable(user.getEmail()) )
            throw new EmailUnavailable();
        else
            this.userList.put(user.getEmail(), user.clone());
    }

    /**
     * 
     */
    public void addVehicle (Vehicle vehicle) {
        if( !this.isLicencePlateAvailable(vehicle.getLicencePlate()) )
            throw new LicencePlateUnavailable()
        else
            this.vehicleList.put(vehicle.getLicencePlate(), vehicle.clone() );
    }

    /**
     * 
     */
    public void addTrip (Trip trip){
        this.tripList.put(trip.getId(), trip.clone());
    }

    /**
     * 
     */
    public User getUser (int email) throws UserNonExistent{
        return this.userList.get(id).clone();
    }

    /**
     * 
     */
    public Vehicle getVehicle (String matricula) throws VehicleNonExistent{
        return this.vehicleList.get(matricula).clone();
    } 

    /**
     * 
     */
    public Trip getTrip (int id) throws TripNonExistent {
        if (!this.tripList.containsKey(id))
            throw new TripNonExistent();
        else
            return this.tripList.get(id);
    }

    /**
     * 
     */
    public void changePassword (String password) {
        this.userList.get(loggedUserEmail).setPassword(password);
    }

    /**
     * 
     */
    public void changeAddress (Address address){
        this.userList.get(loggedUserEmail).changeAddress(address);
    }

    /**
     * 
     */
    public void logIn(String email, String password) throws WrongEmailOrPassword{
        if (!this.validUser(emai,password))
            throws new WrongEmailOrPassword();
        else
            this.loggedUserEmail = email;
    }

    /**
     * 
     */
    public boolean validUser (String email, String password){
        if (!this.userList.containsKey(email))
            return false;
        else
            if this.userList.get(email).getPassword().equals(password)
                return true;
    }

    /**
     * 
     */
    public void isEmailAvailable (String email){
        if (this.userList.containsKey(email))
            return false;
        return true;
    }

    /**
     * 
     */
    public void isLicencePlateAvailable (String licence) {
        if (this.vehicleList.containsKey(licence))
            return false;

        return true;
    }

    /**
     * 
     */
    public String getThisClienteProfile (){
        if (this.loggedUserEmail != NULL)
            return "";  // Este caso nunca acontece
        if (!(this.userList.get(loggedUserEmail) instanceof Cliente))
            return "";  // Este caso nunca acontece
        
        Client user = (Client) this.userList.get(loggedUserEmail);
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nName: ")
        sb.append(user.getName());
        sb.append("\nEmail: ")
        sb.append(user.getEmail());
        sb.append("\nAddress: ");
        sb.append(user.getAddres().toString());
        sb.append("\nBirthday: ");
        sb.append(user.getBirthday().toString());
        sb.append("\n");

        return sb.toString();
    }

    /**
     * 
     */
    public String getThisDriverProfile (){
        if (this.loggedUserEmail != NULL)
            return "";  // Este caso nunca acontece
        if ( !(this.userList.get(loggedUserEmail) instanceof Driver))
            return "";  // Este caso nunca acontece
        
        Driver user = (Driver) this.userList.get(loggedUserEmail);
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nName: ")
        sb.append(user.getName());
        sb.append("\nEmail: ")
        sb.append(user.getEmail());
        sb.append("\nAddress: ");
        sb.append(user.getAddres().toString());
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
    public String getUserType(){
        if this.loggedUserEmail != NULL
            return this.userList.get(loggedUserEmail).getClass().getSimpleName();
        else
            throw new NoUserLogged();
    }

    /**
     * 
     */
    public String getTripHistory(LocalDate firstDate, LocalDate lastDate){
        StringBuilder sb = new StringBuilder("Trip History:\n");

        for(int i: this.userList.get(loggedUserEmail).getTripHisotry()){
            trip = this.tripList.get(i);
            if(trip.getDate().isBefore(lastDate){
                break;
            }
            if( trip.getDate().isAfter(firstDate) ){
                newList.add(trip.clone());
                sb.append("\nDate:\n");
                sb.append(trip.getDate().toString());
                sb.append("\nDriver: ");
                sb.append(trip.getDriver().getName());
                sb.append("\nDestination: ");
                sb.append(trip.getDestination());
                sb.append("\nTrip duration: ");
                sb.append(trip.getRealTripTime());
                sb.append("\nCost: ");
                sb.append(trip.getRealCost());
                sb.append("\n\n");
            }
        }

        return sb.toString();
    }

    /**
     * 
     */
    public List<User> get10MostSpendingClients (){
        return this.userList.values()
                            .sorted((c1,c2) -> ComparatorUserTripCost.compare(c1,c2))
                            .limit(10)
                            .collector(Collectors.toList());
    }

    
}

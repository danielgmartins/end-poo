
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.lang.Exception;
import java.lang.RuntimeException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import Exceptions.*;

public class Main implements Interface
{   
    //private static Console console = System.console();
    private static UMeR umer;
    private static boolean running;

    /**
     * Main. Chooses what menu to choose from and starts from there.
     * Always initializes a new UMeR object.
     */
    public static void main(String args[]) {
        umer = new UMeR();
        running = true;

        while(running){
            clean();
            if(umer.isLogged()){
                if(umer.isClient()) clientMenu();
                else if(umer.isDriver()) driverMenu();
            }
            else noUserMenu();
        } // end of while(running)
    }

    /**
     * Main meu for client
     */
    private static void clientMenu(){
        Scanner sc = new Scanner(System.in);
        
        clean();
        Interface.mainMenuForClient(umer.getUserLogged());
        try{
            switch(sc.nextInt()){
                case 1: umer.logOut();      return; // Log Out
                case 2: requestTrip();      break;  // Request Trip
                case 3: viewProfile();      break;  // View Profile
                case 4: viewStatistics();   break;  // View Statistics
                case 5: saveSession();      break;  // Save Session
                case 99: umer.printString();System.exit(0);
                case 0: exit();             return; // Exit application
                default: System.out.println("Invalid option, try again.");
            }
        }catch(NoSuchElementException | IllegalStateException e){
            System.out.println("You must enter a number.");
        }
    }

    /**
     * Main menu for Driver
     */
    private static void driverMenu(){
        Scanner sc = new Scanner(System.in);

        clean();
        Interface.mainMenuForDriver(umer.getUserLogged());
        try{
            switch(sc.nextInt()){
                case 1: umer.logOut();          return; // Log Out
                case 2: viewProfile();          break;  // View Profile
                case 3: registerVehicle();      break;  // Register Vehicle
                case 4: setLocation();          break;  // Set Location
                case 5: switchAvailability();   break;  // Change Availability
                case 6: viewStatistics();       break;  // View Statistics
                case 7: saveSession();          break;  // Save Session
                case 99: umer.printString();    System.exit(0);
                case 0: exit();                 return; // Exit application
                default: System.out.println("Invalid option, try again.");
            }
        }catch(NoSuchElementException | IllegalStateException e){
            System.out.println("You must enter a number.");
        }
    }

    /**
     * Main menu when no user is logged
     */
    private static void noUserMenu(){
        Scanner sc = new Scanner(System.in);
        
        clean();
        Interface.mainMenuNotLogged();
        try{
            switch(sc.nextInt()){
                case 1: logIn();                    break;  // Log In
                case 2: signUp();                   break;  // Sign Up
                case 3: loadSession();              return; // Load Session
                case 4: viewStatistics();           break;  // View Statistics
                case 5: saveSession();              break;  // Load Session
                case 99: umer.printString();    System.exit(0);
                case 0: exit();                 return; // Exit application
                default: System.out.println("Invalid option, try again.");
            }
        }catch(NoSuchElementException | IllegalStateException e){
            System.out.println("You must enter a number.");
        }
    }

    /**
     * Method for logging in
     */
    private static void logIn(){
        Scanner sc = new Scanner(System.in);
        String email = new String();
        String password = new String();
        boolean validInput = false;
        int inp = -1;

        clean();
        EmailValidator eval = new EmailValidator();
        

        do { // getting info for logIn
            System.out.println("0. Go back");

            do{ // getting email
                System.out.print("Email: ");
                email = sc.nextLine().replaceAll("[\n\r]","");

                if(email.equals("0")) return; // breaks in case of 0.

                validInput = eval.validate(email);

                if(!validInput){
                    System.out.println("Email not valid. Try again.");
                }
            } while (!validInput);

             

            do{ // getting password
                System.out.print("Password: ");
                password = sc.nextLine().replaceAll("[\n\r]","");

                if(password.equals("0")) return; // breaks in case of 0.
                
                try{
                    password = hashPassword(password);
                    validInput = true;
                }
                catch(NullPointerException | IllegalStateException e){
                    System.out.println(e.getMessage());
                    validInput= false;
                }
            } while(!validInput);

             

            try{ // attempts log in
                umer.logIn(email,password);
                validInput = true;
            }
            catch(WrongEmailOrPassword e){
                System.out.println(e.getMessage());
                validInput = false;
                clean();
                System.out.println("Log In unsuccessful. Try again.");
            }
        }while(!validInput);

        do{ // Going back
            System.out.println("\nLog In successful. Hello " + umer.getUserLogged());
            System.out.println("0. Go Back");

            try{
                inp = sc.nextInt();
                if(inp == 0) return;
                else System.out.println("Not a valid option");
            }catch(InputMismatchException | IllegalStateException e){
                e.getMessage();
                System.out.println("You must enter a number.");
            }
            sc.nextLine().replaceAll("[\n\r]","");;
        }while(true);
    }

    /**
     * Method for signing up user
     */
    private static void signUp(){
        Scanner sc  = new Scanner(System.in);
        boolean validInput = false;
        clean();
        System.out.println("0. Go back");

        User newUser;
        int userType = 0; // 1 = Client, 2 = Driver
        String name, email, password, city, country;
        int bday=0, bmonth=0, byear=0, inp =-1;
        Address address = new Address();
        LocalDate birthday = LocalDate.now();
        EmailValidator eval = new EmailValidator();

        do {    // getting info for sign up

            do{ // signing up as Cliente or Driver?
                System.out.println("1. Sign Up as Client");
                System.out.println("2. Sign Up as Driver");

                try{
                    userType = sc.nextInt();
                    if(userType == 0) return;
                    if(userType == 1 || userType == 2)
                        validInput = true;
                }catch(InputMismatchException | IllegalStateException e){
                    e.getMessage();
                    System.out.println("You must enter a number.");
                    validInput = false;
                }

                sc.nextLine().replaceAll("[\n\r]","");;
            }while(!validInput);

             

            validInput = false;
            do{ //getting email
                System.out.print("Email: ");
                email = sc.nextLine().replaceAll("[\n\r]","");

                if(email.equals("0")) return;
                
                if(!eval.validate(email)){
                    System.out.println("Not a valid email.");
                }else if(!umer.isEmailAvailable(email)){
                    System.out.println("Email already in use.");
                }else{
                    validInput = true;
                }
            }while(!validInput);

             

            validInput = false;
            do{                         // getting password
                System.out.print("Password: ");
                password = sc.nextLine().replaceAll("[\n\r]","");

                if(password.equals("0")) return; // breaks in case of 0.
                if(password.length() < 6) {
                    System.out.println("Password must be 6 characters long, or higher");
                    continue; // does not accept password with less then 6 char
                }
                try{
                    password = hashPassword(password);
                    validInput = true;
                }
                catch(NullPointerException | IllegalStateException e){
                    System.out.println(e.getMessage());
                    validInput= false;
                }
            }while(!validInput);

             

            validInput = false;
            do{                         // getting name
                System.out.print("Name: ");
                name = sc.nextLine().replaceAll("[\n\r]","");
                if(name.equals("0")) return;
                if(name == null || name.equals("")){
                    System.out.println("Not a valid name.");
                }else{
                    validInput = true;
                }
            }while(!validInput);

             

            validInput = false;
            do{                         // getting birthday
                System.out.println("Birthday");
                try{
                    System.out.print("Day: ");
                    bday = sc.nextInt();
                    if(bday == 0) return;
                     
                    System.out.print("Month: ");
                    bmonth = sc.nextInt();
                    if(bmonth == 0) return;
                     
                    System.out.print("Year: ");
                    byear = sc.nextInt();
                    if(byear == 0) return;
                    validInput = true;
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                    validInput = false;
                }
                sc.nextLine().replaceAll("[\n\r]","");;
                if(validInput){
                    try{
                        birthday = LocalDate.of(byear,bmonth,bday);
                        validInput = true;
                    }catch(DateTimeException e){
                        System.out.println("Not a valid date.");
                        validInput = false;
                    }
                }
            }while(!validInput);

            validInput = false;
            do{                         // getting address
                System.out.print("Address\nCity: ");

                city = sc.nextLine().replaceAll("[\n\r]","");;
                 
                if(city.equals("0")) return;
                try{
                    address.setCity(city);
                }catch(NullPointerException | IllegalStateException e){
                    System.out.println("Error:" + e.getMessage());
                    continue;
                }
                System.out.print("Country: ");
                country = sc.nextLine().replaceAll("[\n\r]","");;
                 
                if(country.equals("0")) return;
                try{
                    address.setCountry(country);
                }catch(NullPointerException | IllegalStateException e){
                    System.out.println("Error:" + e.getMessage());
                    continue;
                }
                validInput = true;
            }while(!validInput);

            validInput = false;
            try{                        // attempt creating user
                if(userType == 1){
                    newUser = new Client(name, address, birthday, email, password);
                    umer.addUser(newUser);
                    validInput = true;
                }
                else if(userType == 2){
                    newUser = new Driver(name, address, birthday, email, password);
                    umer.addUser(newUser);
                    validInput = true;
                }
            } catch(EmailUnavailable e){
                clean();
                System.out.println("This email is already in user.");
                validInput = false;
                System.out.println("Sign Up unsuccessful. Try again.");
            }
        }while(!validInput);

        do{ // Going back
            System.out.println("\nSign Up successful.");
            System.out.println("0. Go Back");

            try{
                inp = sc.nextInt();
                if(inp == 0) return;
                else System.out.println("Not a valid option");
            }catch(InputMismatchException | IllegalStateException e){
                e.getMessage();
                System.out.println("You must enter a number.");
            }
            sc.nextLine().replaceAll("[\n\r]","");;
        }while(true);
    }

    /**
     * Profile menu with user information
     */
    private static void viewProfile(){
        Scanner sc = new Scanner(System.in);

        clean();
        if(umer.isClient()){   // CLIENT PROFILE
            do{
                try{
                    clean();
                    Interface.profileMenu( umer.getThisClientProfileString() );
                }catch(NoUserLoggedException | UserIsNotClientException e){
                    System.out.println("NoUserLoggedException | UserIsNotClientException");
                }
                try{
                    switch (sc.nextInt()){
                        case 1 : viewTripHistory(); break;
                        case 2 : // changePassword();  break;
                        case 0 : return;
                        default: System.out.println("Not valid input."); continue;
                    }
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                }
            }while(true);
        }
        else if(umer.isDriver()){  // DRIVER PROFILE
            do{
                try{
                    clean();
                    Interface.profileMenu( umer.getThisDriverProfileString() );
                }catch(NoUserLoggedException | UserIsNotClientException e){
                    System.out.println("NoUserLoggedException | UserIsNotClientException");
                }
            
                try{
                    switch (sc.nextInt()){
                        case 1 : viewTripHistory(); break;
                        case 2 : // changePassword();  break;
                        case 0 : return;
                        default: System.out.println("Not valid input."); continue;
                    }
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                }
            }while(true);
        }
    }

    /**
     * Prints a user's trip history within a certain range.
     */
    private static void viewTripHistory() {
        Scanner sc = new Scanner(System.in);
        int inp = -1;
        int fday=0, fmonth=0, fyear=0;
        int sday=0, smonth=0, syear=0;
        LocalDate first_date= LocalDate.now(), second_date = LocalDate.now();
        boolean validInput = false;
        
        do{
            clean();
            System.out.println("View trip history.");
            System.out.println("Choose range of dates to view your trip history.");
            System.out.println("0. Go Back\n");

            System.out.println("From what date do you want to see your trip history?");
            do{ // GET FIRST DATE
                try{
                    System.out.print("Day: ");
                    fday = sc.nextInt();
                    if(fday == 0) return;
                    
                    System.out.print("Month: ");
                    fmonth = sc.nextInt();
                    if(fmonth == 0) return;
                    
                    System.out.print("Year: ");
                    fyear = sc.nextInt();
                    if(fyear == 0) return;
                    validInput = true;
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                    validInput = false;
                }

                sc.nextLine().replaceAll("[\n\r]","");;
                if(validInput){
                    try{
                        first_date = LocalDate.of(fyear,fmonth,fday);
                        validInput = true;
                    }catch(DateTimeException e){
                        System.out.println("Not a valid date.");
                        validInput = false;
                    }
                }
            }while(!validInput);

            do{ // GET SECOND DATE
                System.out.println("\nUp to what date do you want to see your trip history?");
                try{
                    System.out.print("Day: ");
                    sday = sc.nextInt();
                    if(sday == 0) return;
                    
                    System.out.print("Month: ");
                    smonth = sc.nextInt();
                    if(smonth == 0) return;
                    
                    System.out.print("Year: ");
                    syear = sc.nextInt();
                    if(syear == 0) return;
                    validInput = true;
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                    validInput = false;
                }

                sc.nextLine().replaceAll("[\n\r]","");;
                if(validInput){
                    try{
                        second_date = LocalDate.of(syear,smonth,sday);
                        validInput = true;
                    }catch(DateTimeException e){
                        System.out.println("Not a valid date.");
                        validInput = false;
                    }
                }
            }while(!validInput);

            System.out.println(umer.getTripHistoryString(first_date, second_date));

            do{ // Going back
                System.out.println("1. View Other Dates");
                System.out.println("0. Go Back");

                try{
                    inp = sc.nextInt();
                    if(inp == 0) return;
                    else if(inp == 1){ validInput = false; break; }
                    else System.out.println("Not a valid option");
                }catch(InputMismatchException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                }
                sc.nextLine().replaceAll("[\n\r]","");;
            }while(true);
            
        }while(!validInput);

        
    }

    /**
     * Register Vehicle. 
     * A driver is only allowed to register a vehicle if does not already have one associated.
     */
    private static void registerVehicle(){
        if(!umer.isDriver())
            return;
        
        
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
        int vehicleType = -1, inp = -1;
        Vehicle newVehicle;
        String licensePlate;

        clean();

        if(!umer.alreadyHasVehicle()){
            do{
                System.out.println("0. Go Back");
                System.out.println("1. Register Car");
                System.out.println("2. Register Van");
                System.out.println("3. Register Motorcycle");
                    
                do{ // Selects Vechicle type
                    try{
                        switch(sc.nextInt()){
                            case 1: vehicleType = 1; validInput = true; break;
                            case 2: vehicleType = 2; validInput = true; break;
                            case 3: vehicleType = 3; validInput = true; break;
                            case 0: return;
                            default: System.out.println("Not a valid option.");
                        }
                    }catch(NoSuchElementException | IllegalStateException e){
                        System.out.println("You must enter a number.");
                        validInput = false;
                    }
                    sc.nextLine().replaceAll("[\n\r]","");;
                }while(!validInput);

                do {    // Get lincese plate
                    System.out.println("Lincese Plate: ");
                    licensePlate = sc.nextLine().replaceAll("[\n\r]","");
                    if(licensePlate.equals("0")) return;
                    validInput = true;
                }while(!validInput);

                try{
                    switch(vehicleType){
                        case 1:
                            newVehicle = new Car( licensePlate );
                            umer.addVehicle(newVehicle);
                            validInput = true;
                            break;
                        case 2:
                            newVehicle = new Van( licensePlate );
                            umer.addVehicle(newVehicle);
                            validInput = true;
                            break;
                        case 3: 
                            newVehicle = new Motorcycle( licensePlate );
                            umer.addVehicle(newVehicle);
                            validInput = true;
                            break;
                        default: break;
                    }
                }catch(LicensePlateUnavailable e){
                    clean();
                    System.out.println("License plate already in use.");
                    System.out.println("Could not register Vehicle. Try Again");
                    validInput = false;
                }
                if(validInput) System.out.println("\nRegistered Vechicle succsessfully");
            }while(!validInput);
        }
        else{
            System.out.println("You already have a vehicle.");
        }
        do{ // Going back
            System.out.println("0. Go Back");

            try{
                inp = sc.nextInt();
                if(inp == 0) return;
                else System.out.println("Not a valid option");
            }catch(InputMismatchException | IllegalStateException e){
                System.out.println("You must enter a number.");
            }
            sc.nextLine().replaceAll("[\n\r]","");;
        }while(true);
    }

    /**
     * Request Trip
     */
    private static void requestTrip(){
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
        int inp = -1;
        String driverEmail = "";
        Driver driver = null;
        Vehicle vehicle = null;
        int xcoordinate, ycoordinate, timeToClient=-1, timeToDestination=-1, realTripTime=-1, classification=100;
        double realTripCost, estimatedTripCost=0.0, vehicleReliability=0.0, paidPrice=0, totalDistance = 0;
        Coordinates userLocation=null, destination=null;
        Trip newTrip;
        clean();
        
        do{
            do{     //Get User Location     THIS IS THE ONLY TIME YOU CANNONT ENTER 0 TO GO BACK
                System.out.println("Please enter your location. Enter 'C' to cancel.");
                try{
                    System.out.print("Coordenada x: ");
                    xcoordinate = sc.nextInt();
                    System.out.print("Coordenada y: ");
                    ycoordinate = sc.nextInt();
                    userLocation = new Coordinates(xcoordinate, ycoordinate);
                    umer.setUserLocation(userLocation);
                    validInput = true;
                }catch(NoSuchElementException | IllegalStateException e){
                    if(sc.nextLine().equals("C")) return;
                    System.out.println("You must enter a number.");
                    validInput = false;
                }
                sc.nextLine();
            }while(!validInput);
            validInput = false;

            do{     //Get User Location     THIS IS THE ONLY TIME YOU CANNONT ENTER 0 TO GO BACK
                System.out.println("Please enter your destination. Enter 'C' to cancel.");
                try{
                    System.out.print("Coordenada x: ");
                    xcoordinate = sc.nextInt();
                    System.out.print("Coordenada y: ");
                    ycoordinate = sc.nextInt();
                    destination = new Coordinates(xcoordinate, ycoordinate);
                    validInput = true;
                }catch(NoSuchElementException | IllegalStateException e){
                    if(sc.nextLine().equals("C")) return;
                    System.out.println("You must enter a number.");
                    validInput = false;
                }
                sc.nextLine();
            }while(!validInput);

            do{     // Request Closest OR Specific vehicle
                clean();
                System.out.println("1. Request closest vehicle");
                System.out.println("2. Request specific vehicle");
                System.out.println("0. Go Back");
                try{
                    switch(sc.nextInt()){
                        case 1: // Requests closest Vehicle
                            driverEmail = requestClosestVehicle();
                            if(driverEmail != null) validInput = true;
                            break;
                        case 2: 
                            driverEmail = requestSpecificVehicle();
                            if(driverEmail != null) validInput = true;
                            break;
                        case 0: return;
                        default:
                            System.out.println("Not a valid option");
                            break;
                    }
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                    validInput = false;
                }
            }while(!validInput);
            validInput = false;

            //      Calculate trip values
            try{
                driver = (Driver) umer.getDriverObject(driverEmail);
            }catch(UserNonExistent | UserIsNotDriverException e){
                System.out.println(e.getMessage());
                continue;
            }
            vehicle = umer.getDriversVehicle(driverEmail);

            totalDistance       = userLocation.distance(vehicle.getLocation()) + userLocation.distance(destination);
            timeToClient        = (int) ( (double)( userLocation.distance(vehicle.getLocation()) * 60 ) / vehicle.getAverageSpeed() ) ;
            timeToDestination   = (int) ( (double) (userLocation.distance(destination) * 60) / vehicle.getAverageSpeed() );
            estimatedTripCost   = (int) ( (timeToClient + timeToDestination) * (vehicle.getFare()/10) );

            vehicle.setReliability();
            vehicleReliability = vehicle.getReliability();


            clean();
            System.out.print("Your driver: ");
            System.out.println(driver.getName());
            System.out.print("Driver classification: ");
            System.out.println(driver.getClassification());
            System.out.print("Vehicle License Plate: ");
            System.out.println(vehicle.getLicensePlate());
            System.out.print("Vehicle reliability: ");
            System.out.println(vehicleReliability);
            System.out.print("Minutes to your location: ");
            System.out.println(timeToClient);
            System.out.print("Minutes from you location to destination: ");
            System.out.println(timeToDestination);
            System.out.print("Total estimated trip time: ");
            System.out.println(timeToClient + timeToDestination);
            System.out.print("Total Distance: ");
            System.out.println(totalDistance);
            System.out.print("Estimated Cost: ");
            System.out.println(estimatedTripCost);

            do{     // Checks if client wants to request the trip
                try{
                    System.out.println("Do you want to request this trip?");
                    System.out.println("1. Request Trip");
                    System.out.println("0. Cancel and go back");
                    switch(sc.nextInt()){
                        case 1:
                            umer.setDriverAvailability(driverEmail, false);
                            validInput = true;
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("Not a valid option.");
                            break;
                    }
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                    validInput = false;
                }
                sc.nextLine();
            }while(!validInput);
            validInput = true;
        }while(!validInput);

        /*
        realTripTime is calculated by taking the time estimate and multiplying by
        a value determined by the vehicle Reliability ( 100% + ((50%)- reliability/2) )
        if reliability is 100% (1.0) then  it will it take 50% of the estimated time to arrive to destination
        if reliability is 0% (0.0) the it will take 150% of the estimated time to arrive to destination
        */
        realTripTime = (int)( (timeToClient + timeToDestination) * (1 + ((0.5)-vehicleReliability/2)) );
        realTripCost = realTripTime * (vehicle.getFare()/10);


        // Trip has finished. 
        if( realTripTime >= (timeToClient + timeToDestination) * 1.25){  // The trip took over 125% the expected time
            clean();
            System.out.println("Your trip took longer than expected. We are sorry for that.");
            System.out.print("Estimated trip time: ");
            System.out.println(timeToClient + timeToDestination);
            System.out.print("Real trip time: ");
            System.out.println(realTripTime);
            System.out.print("Estimated cost: ");
            System.out.println(estimatedTripCost);
            System.out.print("Real cost: ");
            System.out.println(realTripCost);
            System.out.println("How much are you willing to pay?");
            do{
                try{
                    paidPrice = sc.nextDouble();
                    if(paidPrice < realTripCost * 0.5){
                        System.out.println("We don't find that to be a fair price. You can do better.");
                        validInput = false;
                    }else validInput = true;
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                    validInput = false;
                }
            }while(!validInput);
            validInput= false;

            realTripCost = paidPrice;
        }else{
            clean();
            System.out.println("You arrived at your destination.");
            System.out.print("Real trip Time: ");
            System.out.println(realTripTime);
            System.out.print("Real trip Cost: ");
            System.out.println(realTripCost);
            do{
                System.out.println("0. To Continue");
                try{
                    if(sc.nextInt() == 0) validInput=true;
                    else System.out.println("Not a valid option");
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                    validInput = false;
                }
                sc.nextLine();
            }while(!validInput);
            validInput= false;       
        }

        umer.setDriverAvailability(driverEmail, true);

        // GET DRIVER CLASSIFICATION
        clean();
        System.out.println("How would you rate this driver from 0 to 100");
        do{
            try{
                classification = sc.nextInt();
                if(classification <= 100 && classification >= 0)
                    validInput = true;
                else
                    System.out.println("Rating must be between 0 and 100.");
            }catch(NoSuchElementException | IllegalStateException e){
                System.out.println("You must enter a number.");
                validInput = false;
            }
        }while(!validInput);


        umer.makeTrip(driverEmail,
                      LocalDateTime.now(),
                      vehicle.getLicensePlate(),
                      vehicle.getLocation(),
                      userLocation,
                      destination,
                      timeToClient + timeToDestination,
                      realTripTime,
                      estimatedTripCost,
                      realTripCost
                    );
        umer.giveClassification(driverEmail,classification);
        return;
    }

    /**
     * Request the closest vehicles available, depending on the type of vehicle.
     */
    private static String requestClosestVehicle(){
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
        int inp = -1;
        String driverEmail = null;
        clean();
        do{ 
            System.out.println("What kind of vehicle do you want o request?");
            System.out.println("1. Car        (4 seats)");
            System.out.println("2. Van        (7 seats)");
            System.out.println("3. Motorcycle (1 seat)");
            System.out.println("0. Go Back");


            try{
                inp = sc.nextInt();
                switch(inp){
                    case 1: 
                        driverEmail = umer.getNearestDriver(inp); 
                        validInput = true;
                        break;
                    case 2: 
                        driverEmail = umer.getNearestDriver(inp);
                        validInput = true;
                        break;
                    case 3: 
                        driverEmail = umer.getNearestDriver(inp);
                        validInput = true;
                        break;
                    case 0: return null;
                    default: 
                        System.out.println("Not a valid option.");
                        validInput = false;
                }
            }catch(NoSuchElementException | IllegalStateException e){
                System.out.println("You must enter a number.");
                validInput = false;
            }catch(NoVehicleAvailableException e){
                System.out.println("There are no vehicles of this kind available right now.");
                validInput = false;
            }
            sc.nextLine();
        }while(!validInput);

        return driverEmail;
    }

    /**
     * Request the closest vehicles available, depending on the type of vehicle.
     */
    private static String requestSpecificVehicle(){
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
        int inp = -1;
        String driverEmail;

        do{
            clean();
            System.out.println("Here's a list of the closest vehicles available.");
            System.out.println(umer.getNearestVehiclesString());
            System.out.println("Write the email of the driver you want to request.");
            driverEmail = sc.nextLine();
            if(driverEmail.equals("0")) return null;
            try{
                if(umer.isDriverAvailable(driverEmail) && umer.checkDriverHasVehicle(driverEmail))
                    validInput = true;
                else
                    System.out.println("Not a valid option.");
            }catch(UserNonExistent e){
                System.out.println("Not a valid option.");
                validInput = false;
            }
        }while(!validInput);

        return driverEmail;
    }

    /**
     * 
     */
    private static void switchAvailability(){
        Scanner sc = new Scanner(System.in);
        int inp = -1;
        String userEmail=null;
        try{
            userEmail = umer.getUserLogged();
        }catch(Exception e){
            System.out.println("User must be logged");
            System.exit(0);
        }

        do{
            clean();
            System.out.print("Availability is now set to: ");
            try{
                System.out.println(umer.isDriverAvailable(userEmail));
            }catch(UserNonExistent e){
                System.out.println("User must be logged");
                System.exit(0);
            }
            System.out.println("1. Switch availability");
            System.out.println("0. Go Back");
            try{
                switch(sc.nextInt()){
                    case 1:
                        try{
                            umer.setDriverAvailability(umer.getUserLogged(), !umer.isDriverAvailable(userEmail));
                        }catch(UserNonExistent e){
                            System.out.println("User must be logged");
                            System.exit(0);
                        } 
                        break;
                    case 0: 
                        return;
                    default:
                        System.out.println("Not a valid option.");
                        break;
                }
            }catch(NoSuchElementException | IllegalStateException e){
                System.out.println("You must enter a number.");
            }
        }while(true);
    }

    /**
     * 
     */
    private static void setLocation(){
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
        int xcoordinate, ycoordinate, inp;

        do{     //Get User Location     THIS IS THE ONLY TIME YOU CANNONT ENTER 0 TO GO BACK
            clean();
            System.out.println("Please enter your location. Enter 'C' to cancel.");
            try{
                System.out.print("Coordenada x: ");
                xcoordinate = sc.nextInt();
                System.out.print("Coordenada y: ");
                ycoordinate = sc.nextInt();
                umer.setDriverVehicleLocation(new Coordinates(xcoordinate, ycoordinate));
                validInput = true;
                System.out.println("Location set successfully.");
            }catch(NoSuchElementException | IllegalStateException e){
                if(sc.nextLine().equals("C")) return;
                System.out.println("You must enter a number.");
            }
            sc.nextLine();
        }while(!validInput);

        do{ // Going back
            System.out.println("0. Go Back");
            try{
                inp = sc.nextInt();
                if(inp == 0) return;
                else System.out.println("Not a valid option");
            }catch(InputMismatchException | IllegalStateException e){
                System.out.println("You must enter a number.");
            }
            sc.nextLine().replaceAll("[\n\r]","");;
        }while(true);
    }

    /** 
     * Exits application 
     */
    private static void exit(){
        running = false;
        if(umer.isLogged()) umer.logOut();
        //clean();
    }

    /**
     * View Statistics.
     * Worst performing Drivers and Most spending clients
     */
    private static void viewStatistics(){
        Scanner sc = new Scanner(System.in);
        int inp = -1;
        
        do{
            clean();
            System.out.println("View Statistics");
            System.out.println("1. 10 Clients who spend the most");
            System.out.println("2. 5 Drivers with worst performance");
            System.out.println("0. Go Back");
            try{
                switch(sc.nextInt()){
                    case 1: clientsSpendMost(); break;
                    case 2: worstDrivers();     break;
                    case 0: return;
                    default: System.out.println("Not a valid option."); break;
                }
            }catch(InputMismatchException | IllegalStateException e){
                System.out.println("You must enter a number.");
            }
        }while(true);
    }

    /**
     * Clients who spend the most
     */
    private static void clientsSpendMost(){
        Scanner sc = new Scanner(System.in);
        int inp = -1;
        List<User> userList = new LinkedList<User>(umer.get10MostSpendingClients());
        clean();
        for(User u: userList){
            System.out.print("Name:\t");
            System.out.println(u.getName());
            System.out.print("Total Spent:\t");
            System.out.println(u.getTotalTripCost());
            System.out.print("\n");
        }
        
        System.out.println("0. Go back");
        do{
            try{
                if(sc.nextInt() == 0) return;
                else System.out.println("Not valid option.");
            }catch(InputMismatchException | IllegalStateException e){
                System.out.println("You must enter a number.");
            }
        }while(true);
    }

    /**
     * Drivers with who most deviate from expeted trip time
     */
    private static void worstDrivers(){
        Scanner sc = new Scanner(System.in);
        int inp = -1;

        clean();
        System.out.println(umer.worstPreformingDrivers());

        System.out.println("0. Go back");
        do{
            try{
                if(sc.nextInt() == 0) return;
                else System.out.println("Not valid option.");
            }catch(InputMismatchException | IllegalStateException e){
                System.out.println("You must enter a number.");
            }
        }while(true);
    }

    /**
     * Function to encrypt password when creating user
     * @param pass Password to be encrypted
     */
    private static String hashPassword(String password) throws NullPointerException, IllegalStateException {
        if(password == null)
            throw new NullPointerException("Password can't be null!");
        if(password.trim() == "")
            throw new IllegalStateException("Password can't be empty!");
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < byteData.length; i++)
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            return sb.toString();
        } catch (NoSuchAlgorithmException e){   // Unable to encrypt password
            return password;
        }
    }


    /**
     * Clean the terminal view
     */
    private static void clean(){
        System.out.print("\u001b[2J" + "\u001b[H");
    }

    /*--------------------- SESSION -----------------------*/

    /**
     * Save the application state to an object file named "UMeR_savefile"
     */
    private static void saveSession () {
        Scanner sc = new Scanner(System.in);
        int inp = -1;

        try {
            FileOutputStream fos = new FileOutputStream("UMeR_savefile");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(umer);
            oos.flush();
            oos.close();
            fos.close();
            System.out.println("\nSuccessfully saved session.");
        } catch (Exception e) {
            System.out.println("\nCould not save session.");
            System.out.println(e.getMessage());
        }

        do{ // Going back
            System.out.println("0. Go Back");

            try{
                inp = sc.nextInt();
                if(inp == 0) return;
                else System.out.println("Not a valid option");
            }catch(InputMismatchException | IllegalStateException e){
                System.out.println("You must enter a number.");
            }
            sc.nextLine().replaceAll("[\n\r]","");;
        }while(true);
    }

    /**
     * Load a previous application session from the file "UMeR_savefile", if the file
     * is not in the directory the state will not be loaded
     */
    private static void loadSession () {
        Scanner sc = new Scanner(System.in);
        int inp = -1;

        try {
            FileInputStream fis = new FileInputStream("UMeR_savefile");
            ObjectInputStream ois = new ObjectInputStream(fis);
            umer = (UMeR) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("\nSuccessfully loaded session.");
        } catch (Exception e) {
            System.out.println("\nCould not load session.");
            System.out.println(e.getMessage());
        }

        do{ // Going back
            System.out.println("0. Go Back");

            try{
                inp = sc.nextInt();
                if(inp == 0) return;
                else System.out.println("Not a valid option");
            }catch(InputMismatchException | IllegalStateException e){
                System.out.println("You must enter a number.");
            }
            sc.nextLine().replaceAll("[\n\r]","");;
        }while(true);
    }

}

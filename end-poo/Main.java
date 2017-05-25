
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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import Exceptions.*;

public class Main
{   
    private static Console console = System.console();
    private static UMeR umer;
    private static boolean running;

    /**
     * 
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
     * 
     */
    public static void clientMenu(){
        Scanner sc = new Scanner(System.in);
        
        clean();
        Interface.mainMenuForClient(umer.getUserLogged());
        try{
            switch(sc.nextInt()){
                case 1: umer.logOut();         return; // Log Out
                case 2: requestTrip();         break;  // Request Trip
                case 3: viewProfile();         break;  // View Profile
                case 99: umer.printString();   System.exit(0);
                case 0: exit();                return; // Exit application
                default: System.out.println("Invalid option, try again.");
            }
        }catch(NoSuchElementException | IllegalStateException e){
            System.out.println("You must enter a number.");
        }
    }

    /**
     * 
     */
    public static void driverMenu(){
        Scanner sc = new Scanner(System.in);

        clean();
        Interface.mainMenuForDriver(umer.getUserLogged());
        try{
            switch(sc.nextInt()){
                case 1: umer.logOut();     return; // Log Out
                case 2: viewProfile();     break;  // View Profile
                case 3: registerVehicle(); break;  // Register Vehicle
                case 99: umer.printString(); System.exit(0);
                case 0: exit();            return; // Exit application
                default: System.out.println("Invalid option, try again.");
            }
        }catch(NoSuchElementException | IllegalStateException e){
            System.out.println("You must enter a number.");
        }
    }

    /**
     * 
     */
    public static void noUserMenu(){
        Scanner sc = new Scanner(System.in);
        
        clean();
        Interface.mainMenuNotLogged();
        try{
            switch(sc.nextInt()){
                case 1: logIn();               break;  // Log In
                case 2: signUp();              break;  // Sign Up
                case 3: loadSession();         break;  // Load Session
                case 99: umer.printString();   System.exit(0);
                case 0: exit();                return; // Exit application
                default: System.out.println("Invalid option, try again.");
            }
        }catch(NoSuchElementException | IllegalStateException e){
            System.out.println("You must enter a number.");
        }
    }

    /**
     * 
     */
    public static void logIn(){
        Scanner sc = new Scanner(System.in);
        String email = new String();
        String password = new String();
        boolean validInput = false;

        clean();
        System.out.println("0. Go back");
        EmailValidator eval = new EmailValidator();
        

        do { // getting info for logIn

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
            }
            catch(WrongEmailOrPassword e){
                System.out.println(e.getMessage());
                continue;
            }
            validInput = true;
        }while(!validInput);
    }

    /**
     * 
     */
    public static void signUp(){
        Scanner sc  = new Scanner(System.in);
        boolean validInput = false;
        clean();
        System.out.println("0. Go back");

        User newUser;
        int userType = 0; // 1 = Client, 2 = Driver
        String name, email, password, city, country;
        int bday, bmonth, byear;
        Address address = new Address();
        LocalDate birthday = LocalDate.now();
        EmailValidator eval = new EmailValidator();

        do {    // getting info for sign up

            do{ // signing up as Cliente or Driver?
                System.out.println("1. Sign Up as Client");
                System.out.println("1. Sign Up as Driber");
                try{
                    userType = sc.nextInt();
                    if(userType == 0) return;
                    if(userType == 1 || userType == 2)
                        validInput = true;
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                    continue;
                }
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
                if(password.length() <= 6) continue; // does not accept password with less then 6 char
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
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                    continue;
                }
                try{
                    birthday = LocalDate.of(byear,bmonth,bday);
                    validInput = true;
                }catch(DateTimeException e){
                    System.out.println("Not a valid date.");
                    validInput = false;
                }
            }while(!validInput);

            validInput = false;
            do{                         // getting address
                System.out.print("Address\nCity: ");

                city = sc.nextLine().replaceAll("[\n\r]","");
                //city = sc.nextLine().replaceAll("[\n\r]","");

                if(city.equals("0")) return;
                try{
                    address.setCity(city);
                }catch(NullPointerException | IllegalStateException e){
                    System.out.println("Error:" + e.getMessage());
                    continue;
                }
                System.out.print("Country: ");
                country = sc.nextLine().replaceAll("[\n\r]","");
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
                System.out.println("This email is already in user.");
                validInput = false;
            }
        }while(!validInput);
    }


    /**
     * 
     */
    public static void viewProfile(){
        Scanner sc = new Scanner(System.in);

        clean();
        if(umer.isClient()){   // CLIENT PROFILE
            System.out.println( umer.getThisClientProfileString() );
            System.out.println("1. View Trip History");
            System.out.println("2. Change password");
            System.out.println("3. View Favorites");
            System.out.println("0. Go Back");

            do{
                try{
                    switch (sc.nextInt()){
                        case 1 : // viewTripHistory(); break;
                        case 2 : // changePassword();  break;
                        case 3 : // viewFavorites();   break;
                        case 0 : return;
                        default: System.out.println("Not valid input."); continue;
                    }
                }catch(NoSuchElementException | IllegalStateException e){
                    System.out.println("You must enter a number.");
                }
            }while(true);
        }
        else if(umer.isDriver()){  // DRIVER PROFILE
            try{
                System.out.println( umer.getThisDriverProfileString() );
            }catch(NoUserLoggedException | UserIsNotClientException e){
                System.out.println("NoUserLoggedException | UserIsNotClientException");
            }
            System.out.println("1. View Trip History");
            System.out.println("2. Change password");
            System.out.println("0. Go Back");

            do{
                try{
                    switch (sc.nextInt()){
                        case 1 : // viewTripHistory(); break;
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
     * 
     */
    public static void registerVehicle(){
        if(!umer.isDriver())
            return;
        
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
        int vehicleType = -1;
        Vehicle newVehicle;
        String lincesePlate;

        clean();
        System.out.println("0. Go Back");

        do{
            System.out.println("1. Register Car");
            System.out.println("2. Register Motorcycle");
            System.out.println("3. Register Van");
                
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
                }
            }while(!validInput);

            do {    // Get lincese plate
                System.out.println("lincese Plate: ");
                lincesePlate = sc.nextLine().replaceAll("[\n\r]","");
                if(lincesePlate.equals("0")) return;
                validInput = true;
            }while(!validInput);

            switch(vehicleType){
                case 1:
                    newVehicle = new Car(lincesePlate);
                    try{
                        umer.addVehicle(newVehicle);
                    }catch(LicensePlateUnavailable e){
                        System.out.println("License plate already in use.");
                    }
                case 2: break;
                
                case 3: break;

                default: break;
            }

        }while(!validInput);
    }

    /**
     * 
     */
    private static void requestTrip(){

        /*
        get user location
        Ask if what kind of vehicle the user wants, or if they want to request a specific vehicl
        
        If Specific Vehicle
            get vehicle license plate
            check availability
            if not available    go back
            else                continue

        If any vehicle of given type
            get the closest vehicle
        
        calculate time taken to arrival 
        calculate time taken to destination
        calcutale total time
        calculate expected price
        print all this info allong with vehicle and driver info
        check if driver wants to make request

        calculate real time
        if realtime > time estimated * 125% || realtime < time estimated * 125%
            get how much client wants to pay
        else
            calculate real price
        
        get reating 0 - 100
        
        make object trip
            (when creating trip, in UMeR we have to update the vehicle location to the trip destination)


        */
        return;
    }

    /** Exits application 
     */
    public static void exit(){
        running = false;
        if(umer.isLogged()) umer.logOut();
    }

    /**
     * Function to encrypt password when creating user
     * @param pass Password to be encrypted
     */
    public static String hashPassword(String password) throws NullPointerException, IllegalStateException {
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
        if (console != null) System.out.print("\u001b[2J" + "\u001b[H");
    }

    /*--------------------- APPLICATION STATE -----------------------*/

    /**
     * Save the application state to an object file named 'geocaching'
     */
    private static void saveState () {
        try {
            FileOutputStream fos = new FileOutputStream("UMeR_savefile");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(umer);
            oos.close();
            System.out.println("Saved!");
            if (console != null) console.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Load a previous application state from the file 'geocaching', if the file
     * is not in the directory the state will not be loaded
     */
    private static void loadSession () {
        try {
            FileInputStream fis = new FileInputStream("UMeR_savefile");
            ObjectInputStream ois = new ObjectInputStream(fis);
            umer = (UMeR) ois.readObject();
            ois.close();
            System.out.println("Successfully loaded state.");
            if (console != null) console.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

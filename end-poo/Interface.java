
/**
 *
 * Interface is the class which holds methods for printing menus on the screen
 * @author  a55617 Elísio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version 30/4/2017
 */

public interface Interface {
    /**
     * Prints Main menu when no user is logged
     */
    public static void mainMenuNotLogged(){
        System.out.println("Main Menu   -   You are not logged in");
        System.out.println("1. Log In");
        System.out.println("2. Sign Up");
        System.out.println("3. Load Session");
        System.out.println("4. View Statistics");
        System.out.println("5. Save Session");
        System.out.println("0. Exit");
    }

    /**
     * Prints Main menu when client is logged
     */
    public static void mainMenuForClient(String email){
        System.out.println("Main Menu   -   Logged in as " + email);
        System.out.println("1. Log Out");
        System.out.println("2. Request Trip");
        System.out.println("3. View Profile");
        System.out.println("4. View Statistics");
        System.out.println("5. Save Session");
        System.out.println("0. Exit");

    }

    /**
     * Prints Main menu when driver is logged
     */
    public static void mainMenuForDriver(String email){
        System.out.println("Main Menu   -   Logged in as " + email);
        System.out.println("1. Log Out");
        System.out.println("2. View Profile");
        System.out.println("3. Register Vehicle");
        System.out.println("4. Set location");
        System.out.println("5. Switch Availability");
        System.out.println("6. View Statistics");
        System.out.println("7. Save Session");
        System.out.println("0. Exit");
    }

    /**
     * Prints profile and menu for client
     */
    public static void profileMenuClient(String profile){
        System.out.println(profile);
        System.out.println("1. View Trip History");
        System.out.println("2. Change password");
        System.out.println("0. Go Back");
    }

    /**
     * Prints profile and menu for driver
     */
    public static void profileMenuDriver(String profile){
        System.out.println(profile);
        System.out.println("1. View Trip History");
        System.out.println("2. Change password");
        System.out.println("3. Vehicle profile");
        System.out.println("0. Go Back");
    }


}

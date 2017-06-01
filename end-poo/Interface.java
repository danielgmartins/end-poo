
/**
 * Write a description of class Interface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public interface Interface
{
    public static void mainMenuNotLogged(){
        System.out.println("Main Menu   -   You are not logged in");
        System.out.println("1. Log In");
        System.out.println("2. Sign Up");
        System.out.println("3. Load Session");
        System.out.println("4. View Statistics");
        System.out.println("5. Save Session");
        System.out.println("0. Exit");
    }

    public static void mainMenuForClient(String email){
        System.out.println("Main Menu   -   Logged in as " + email);
        System.out.println("1. Log Out");
        System.out.println("2. Request Trip");
        System.out.println("3. View Profile");
        System.out.println("4. View Statistics");
        System.out.println("5. Save Session");
        System.out.println("0. Exit");

    }
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

    public static void profileMenuClient(String profile){
        System.out.println(profile);
        System.out.println("1. View Trip History");
        System.out.println("2. Change password");
        System.out.println("0. Go Back");
    }

    public static void profileMenuDriver(String profile){
        System.out.println(profile);
        System.out.println("1. View Trip History");
        System.out.println("2. Change password");
        System.out.println("3. Vehicle profile");
        System.out.println("0. Go Back");
    }


}

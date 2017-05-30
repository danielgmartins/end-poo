



import java.util.Comparator;

public class ComparatorUserTripCost implements Comparator<User>, Serializable{

    /**
     * Compares to users by their total Trip cost
     * @param user1 User to compare
     * @param user2 User to be compared against
     * @return returns 0 if total cost is the same, 1 if user1 has greater total cost, -1 if user2 has greater total cost
     */
    public int compare(User user1, User user2){
        double cost1 = user1.getTotalTripCost();
        double cost2 = user2.getTotalTripCost();

        if (cost1 == cost2) return 0;
        if (cost1 > cost2) return 1;
        return -1;
    }
}






import java.util.Comparator;

public class ComparatorUserId implements Comparator<User> {

    /**
     * Compares to users by their id
     * @param user1 User to compare
     * @param user2 User to be compared against
     * @return returns 0 if id is the same, 1 if user1 has an higher id, -1 if user2 has an higher id
     */
    public int compare(User user1, User user2){
        int id1 = user1.getId();
        int id2 = user2.getId();

        if (id1 == id2) return 0;
        if (id1 > id2) return 1;
        return -1;
    }
}
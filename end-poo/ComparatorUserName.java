/**
 *
 * ComparatorUserName compares users by name
 * @author  a55617 Elísio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version 12/04/2017
 */

import java.util.Comparator;
import java.io.Serializable;
public class ComparatorUserName implements Comparator<User>, Serializable {

    /**
     * Compares to users alphabetically by their name
     * @param user1 User to compare
     * @param user2 User to be compared against
     * @return returns 0 if name is the same, 1 if user1 name is first alphabetically, -1 if user2 otherwise
     */
    public int compare(User user1, User user2){
        return user1.getName().compareTo(user2.getName());
    }
}

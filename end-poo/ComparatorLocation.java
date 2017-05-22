import java.util.Comparator;

/**
 * Compares cars
 *
 * @author a55617 Elisio Fernandes, a73175 Daniel Martins, a78879 Nuno Silva
 * @version (a version number or a date)
 */
public class ComparatorLocation implements Comparator <Vehicle> {
    public int compare(Vehicle v1, Vehicle v2) {
        return ComparatorCoordinates(v1.getLocation(), v2.getLocation());
    }
}

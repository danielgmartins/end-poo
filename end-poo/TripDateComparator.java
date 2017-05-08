/**
 * TripDateComparator is a class that implements a date-based comparator for class trip.
 * @author Elisio Fernandes A55617 Daniel Martins A73175 Nuno Silva A78879
 * @version 8/05/2017
 */
import java.util.Comparator;
import java.io.Serializable;
import java.time.LocalDateTime;

public class TripDateComparator implements Comparator<Trip> , Serializable {

	/**
	 * Compare compares two objects of class Trip using their LocalDateTime objects.
	 * @param  t1	Trip object
	 * @param  t2   Another Trip object
	 * @return      int value containing -1 if our object is "lower" than t, 1 if it is "bigger" and 0 if equal.
	 */
	public int compare (Trip t1,Trip t2){
		return t1.getDate().compareTo(t2.getDate());
	}
}

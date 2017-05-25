package Exceptions;

public class TripNonExistent extends Exception {
  public TripNonExistent () { super(); }
  public TripNonExistent (String s) { super(s); }
}
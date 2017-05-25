package Exceptions;

public class NoSuchVehicleException extends Exception {
  public NoSuchVehicleException () { super(); }
  public NoSuchVehicleException (String s) { super(s); }
}
package Exceptions;

public class VehicleNotAvailable extends Exception {
  public VehicleNotAvailable () { super(); }
  public VehicleNotAvailable (String s) { super(s); }
}
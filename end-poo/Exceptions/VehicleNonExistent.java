package Exceptions;

public class VehicleNonExistent extends Exception {
  public VehicleNonExistent () { super(); }
  public VehicleNonExistent (String s) { super(s); }
}
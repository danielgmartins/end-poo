package Exceptions;

public class DriverNotAuthorized extends Exception {
  public DriverNotAuthorized () { super(); }
  public DriverNotAuthorized (String s) { super(s); }
}
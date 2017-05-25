package Exceptions;

public class LicensePlateUnavailable extends Exception {
  public LicensePlateUnavailable () { super(); }
  public LicensePlateUnavailable (String s) { super(s); }
}
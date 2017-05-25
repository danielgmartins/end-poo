package Exceptions;

public class EmailUnavailable extends Exception {
  public EmailUnavailable () { super(); }
  public EmailUnavailable (String s) { super(s); }
}
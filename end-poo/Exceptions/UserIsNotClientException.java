package Exceptions;

public class UserIsNotClientException extends Exception {
  public UserIsNotClientException () { super(); }
  public UserIsNotClientException (String s) { super(s); }
}
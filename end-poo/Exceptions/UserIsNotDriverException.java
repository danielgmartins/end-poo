package Exceptions;

public class UserIsNotDriverException extends Exception {
  public UserIsNotDriverException () { super(); }
  public UserIsNotDriverException (String s) { super(s); }
}
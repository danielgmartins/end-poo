package Exceptions;

public class UserNonExistent extends Exception {
  public UserNonExistent () { super(); }
  public UserNonExistent (String s) { super(s); }
}
package Exceptions;

public class WrongEmailOrPassword extends Exception {
  public WrongEmailOrPassword () { super(); }
  public WrongEmailOrPassword (String s) { super(s); }
}
package exceptions;

public class InvalidDirection extends Exception {
  public InvalidDirection(String direction) {
    super("\"" + direction + "\"" + " is an invalid direction, please try again");
  }
}

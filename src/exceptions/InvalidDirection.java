package exceptions;

public class InvalidDirection extends Exception {
  public InvalidDirection(String direction) {
    super("The " + direction + " is a wall, please try again");
  }
}

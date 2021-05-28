package exceptions;

public class DoorLockedExcpetion extends Exception {
  public DoorLockedExcpetion(String message) {
    super(message);
  }

  public DoorLockedExcpetion() {
    super();
  }
}

package fixtures;

public class Door extends Fixture {
  public Door(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  private Room room = null;

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public void story() {}
}

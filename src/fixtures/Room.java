package fixtures;

public class Room extends Fixture {
  private Room[] exits = null;

  public Room(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  public Room[] getExits() {
    return exits;
  }

  public void setExits(Room[] exits) {
    this.exits = exits;
  }

  public Room getExit(String direction) throws Exception {
    switch (direction.toLowerCase()) {
      default:
        throw new Exception();
      case "north":
        return exits[0];
      case "east":
        return exits[1];
      case "south":
        return exits[2];
      case "west":
        return exits[3];
    }
  }
}

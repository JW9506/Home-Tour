package fixtures;

import exceptions.InvalidDirection;

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

  public Room getExit(String direction) throws InvalidDirection {
    switch (direction.toLowerCase()) {
      default:
        throw new InvalidDirection("You have chosen an invalid direction, please try again");
      case "north":
        if (exits[0] == null) {
          throw new InvalidDirection("The north side is a wall");
        }
        return exits[0];
      case "east":
        if (exits[1] == null) {
          throw new InvalidDirection("The east side is a wall");
        }
        return exits[1];
      case "south":
        if (exits[2] == null) {
          throw new InvalidDirection("The south side is a wall");
        }
        return exits[2];
      case "west":
        if (exits[3] == null) {
          throw new InvalidDirection("The west side is a wall");
        }
        return exits[3];
    }
  }
}

package fixtures;

import exceptions.InvalidDirection;

public class Room extends Fixture {
  private Room[] exits = null;
  private Item[] items = null;

  public Room(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  public Item[] getItems() {
    return items;
  }

  public void setItems(Item[] items) {
    this.items = items;
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
          throw new InvalidDirection("north");
        }
        return exits[0];
      case "east":
        if (exits[1] == null) {
          throw new InvalidDirection("east");
        }
        return exits[1];
      case "south":
        if (exits[2] == null) {
          throw new InvalidDirection("south");
        }
        return exits[2];
      case "west":
        if (exits[3] == null) {
          throw new InvalidDirection("west");
        }
        return exits[3];
    }
  }
}

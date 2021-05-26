package fixtures;

import java.util.Map;
import java.util.TreeMap;

import exceptions.InvalidDirection;

public class Room extends Fixture {
  private Map<String, Room> exits = new TreeMap<>();

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

  public Map<String, Room> getExits() {
    return exits;
  }

  public void setExits(String direction, Room room) {
    exits.put(direction, room);
  }

  public Room getExit(String direction) throws InvalidDirection {
    direction = direction.toLowerCase();
    if (!exits.containsKey(direction)) {
      throw new InvalidDirection(direction);
    } else {
      return exits.get(direction);
    }
  }
}

package fixtures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import exceptions.InvalidDirection;

public class Room extends Fixture {
  private Map<String, Room> exits = new TreeMap<>();

  private List<Item> items = new ArrayList<>();

  public Room(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  public List<Item> getItems() {
    return items;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItems(Item item) {
    items.remove(item);
  }

  public Map<String, Room> getExits() {
    return exits;
  }

  public void setExits(String direction, Room room) {
    exits.put(direction, room);
  }

  public Room getExit(String direction) throws InvalidDirection {
    direction = direction.toLowerCase().intern();
    if (!exits.containsKey(direction)) {
      throw new InvalidDirection(direction);
    } else {
      return exits.get(direction);
    }
  }
}

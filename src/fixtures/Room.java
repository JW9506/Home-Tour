package fixtures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import exceptions.InvalidDirection;

public class Room extends Fixture {
  private Map<String, Room> exits = new TreeMap<>();

  private List<Item> items = new ArrayList<>();
  private List<Fixture> environmentFixtures = new ArrayList<>();
  private boolean locked = false;

  public Room(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public Room(String name, String shortDescription, String longDescription, boolean locked) {
    this(name, shortDescription, longDescription);
    setLocked(locked);
  }

  public List<Fixture> getEnvironmentFixture() {
    return environmentFixtures;
  }

  public void addEnvironmentFixture(Fixture environmentFixture) {
    environmentFixtures.add(environmentFixture);
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
    for (String k : exits.keySet()) {
      if (k.startsWith(direction)) {
        direction = k;
      }
    }
    if (!exits.containsKey(direction)) {
      throw new InvalidDirection(direction);
    } else {
      return exits.get(direction);
    }
  }
}

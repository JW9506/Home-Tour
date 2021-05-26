package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fixtures.Item;
import fixtures.Room;

public class Player {
  private Room currentRoom = null;
  private List<Item> inventory = new ArrayList<>();
  private Map<Item, Integer> inventoryItemDistribution = new HashMap<>();

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public List<Item> getInventory() {
    return inventory;
  }

  public Map<Item, Integer> getInventoryItemDistribution() {
    return inventoryItemDistribution;
  }

  public void addToInventory(Item item) {
    inventoryItemDistribution.put(item, inventoryItemDistribution.getOrDefault(item, 0) + 1);
    inventory.add(item);
  }

  public void removeFromInventory(Item item) {
    inventoryItemDistribution.put(item, inventoryItemDistribution.get(item) - 1);
    if (inventoryItemDistribution.get(item) == 0) {
      inventoryItemDistribution.remove(item);
    }
    inventory.remove(item);
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }
}

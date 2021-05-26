package game;

import java.util.ArrayList;
import java.util.List;

import fixtures.Item;
import fixtures.Room;

public class Player {
  private Room currentRoom = null;
  private List<Item> inventory = new ArrayList<>();

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public List<Item> getInventory() {
    return inventory;
  }

  public void addToInventory(Item item) {
    inventory.add(item);
  }

  public void removeFromInventory(Item item) {
    inventory.remove(item);
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }
}

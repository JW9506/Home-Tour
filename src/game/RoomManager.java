package game;

import java.util.HashMap;
import java.util.Map;

import fixtures.Item;
import fixtures.Room;

public class RoomManager {
  public static Room startingRoom;
  public static Room[] rooms;
  final public static Map<Integer, String> directionMap = new HashMap<Integer, String>() {
    {
      put(0, "North");
      put(1, "East");
      put(2, "South");
      put(3, "West");
    }
  };

  private RoomManager() {
  }

  public static void init() {
    Room smallRoom = new Room("The Smallroom", "a small room", "This is a small room");
    Room diningRoom = new Room("The Diningroom", "a big diningroom", "This is a big diningroom");
    Room foyer = new Room("The Foyer", "a small foyer",
        "The small entryway of a neo-colonial house. A dining room is open to the south, where a large table can be seen."
            + "\n" + "The hardwood floor leads west into doorway, next to a staircase that leads up to a second floor."
            + "\n" + "To the north is a small room, where you can see a piano.");
    rooms = new Room[] { foyer, smallRoom };
    foyer.setExits(new Room[] { smallRoom, null, diningRoom, null });
    diningRoom.setExits(new Room[] { foyer, null, null, null });
    smallRoom.setExits(new Room[] { null, null, foyer, null });
    smallRoom.setItems(new Item[] { new Item("Piano", "a small piano", "this is a small piano") });
    startingRoom = foyer;
  }
}

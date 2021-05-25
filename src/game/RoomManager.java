package game;

import java.util.HashMap;
import java.util.Map;

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
    Room restroom = new Room("Restroom", "a small restroom", "foobar");
    Room foyer = new Room("The Foyer", "a small foyer",
        "The small entryway of a neo-colonial house. A dining room is open to the south, where a large table can be seen."
            + "\n" + "The hardwood floor leads west into doorway, next to a staircase that leads up to a second floor."
            + "\n" + "To the north is a small room, where you can see a piano.");
    rooms = new Room[] { foyer, restroom };
    foyer.setExits(new Room[] { restroom, null, null, null });
    restroom.setExits(new Room[] { null, null, foyer, null });
    startingRoom = foyer;
  }
}

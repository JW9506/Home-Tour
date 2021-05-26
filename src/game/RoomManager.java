package game;

import fixtures.Item;
import fixtures.Room;

public class RoomManager {
  public static Room startingRoom;

  private RoomManager() {
  }

  public static void init() {
    Room smallRoom = new Room("The Smallroom", "a small room", "This is a small room");
    Room diningRoom = new Room("The Diningroom", "a big diningroom", "This is a big diningroom");
    Room foyer = new Room("The Foyer", "a small foyer",
        "The small entryway of a neo-colonial house. A dining room is open to the south, where a large table can be seen."
            + "\n" + "The hardwood floor leads west into doorway, next to a staircase that leads up to a second floor."
            + "\n" + "To the north is a small room, where you can see a piano.");

    foyer.setExits("north", smallRoom);
    foyer.setExits("south", diningRoom);

    diningRoom.setExits("north", foyer);

    smallRoom.setExits("south", foyer);
    smallRoom.addItem(new Item("Book", "a novel", "this is an interesting novel"));

    startingRoom = foyer;
  }
}

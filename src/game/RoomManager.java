package game;

import common.g;
import fixtures.Item;
import fixtures.Room;
import fixtures.doors.BasementDoor;
import fixtures.items.Book;
import fixtures.items.Key;
import fixtures.items.Paper;
import fixtures.items.Pen;

final public class RoomManager {
  public static Room startingRoom;

  private RoomManager() {
  }

  public static void initPlayer(Player player) {
    g.setPlayer(player);
    player.setCurrentRoom(startingRoom);
  }

  public static void init() {
    Room smallRoom = new Room("The Smallroom", "a small room", "This is a small room");
    Room diningRoom = new Room("The Diningroom", "a big diningroom", "This is a big diningroom");
    Room bedRoom = new Room("The Bedroom", "a big bedroom", "This is a big bedroom");
    Room foyer = new Room("The Foyer", "a small foyer",
        "The small entryway of a neo-colonial house. A dining room is open to the south, where a piece of paper can be seen."
            + "\n" + "To the north is a small room, where you can see a book.");
    Room basement = new Room("The Basement", "a big basement", "This is a big basement", true);

    Item paper = new Paper("Paper", "a sheet of paper", "this is a sheet of plain paper");

    foyer.setExits("north", smallRoom);
    foyer.setExits("south", diningRoom);

    diningRoom.setExits("north", foyer);
    diningRoom.setExits("east", basement);
    basement.setExits("west", diningRoom);

    smallRoom.setExits("south", foyer);
    smallRoom.setExits("east", bedRoom);
    bedRoom.setExits("west", smallRoom);
    bedRoom.addItem(paper);
    smallRoom.addItem(new Book("Book", "a novel", "this is an interesting novel"));
    diningRoom.addItem(new Pen("Pen", "a blue pen", "this is an expensive blue pen"));
    diningRoom.addItem(new Key("Key", "a key", "this is a key to open the basement door"));
    diningRoom
        .addEnvironmentFixture(new BasementDoor("Basement Door", "A door", "this is a door to the basement", basement));
    startingRoom = foyer;
  }
}

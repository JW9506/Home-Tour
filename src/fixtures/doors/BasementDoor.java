package fixtures.doors;

import common.g;
import fixtures.Door;
import fixtures.Room;

public class BasementDoor extends Door {
  public BasementDoor(String name, String shortDescription, String longDescription, Room room) {
    super(name, shortDescription, longDescription);
    setRoom(room);
  }

  @Override
  public void story() {
    System.out.println("\n\t\tAn odor of disgust came straight to you...");
    g.getPlayer().setCurrentRoom(getRoom());
  }
}

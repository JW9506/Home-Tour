package fixtures.items;

import common.Param;
import exceptions.InvalidCommand;
import fixtures.Door;
import fixtures.Item;
import fixtures.Room;

public class Key extends Item {
  public Key(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  public void do_use(@Param("lock | unlock") Object string, @Param("door") Object door) throws InvalidCommand {
    if (string instanceof String && door instanceof Door) {
      String _string = (String) string;
      Door _door = (Door) door;
      Room room = _door.getRoom();
      if (_string.equals("lock")) {
        room.setLocked(true);
        System.out.println("\n\t\tDoor has been locked...");
      } else if (_string.equals("unlock")) {
        room.setLocked(false);
        System.out.println("\n\t\tDoor has been unlocked...");
      }
      if (!room.isLocked()) {
        System.out.println("\nUsing key to open door...");
        _door.story();
      }
    } else {
      throw new InvalidCommand();
    }
  }
}

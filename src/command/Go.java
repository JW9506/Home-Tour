package command;

import common.g;
import exceptions.DoorLockedExcpetion;
import exceptions.InvalidCommand;
import exceptions.InvalidDirection;
import fixtures.Room;
import game.Player;

public class Go implements Command {
  public final static Command instance = new Go();

  private Go() {
  }

  @Override
  public void action(String entity) throws InvalidCommand {
    Player player = g.getPlayer();
    try {
      Room room = player.getCurrentRoom().getExit(entity);
      if (!room.isLocked()) {
        player.setCurrentRoom(room);
      } else {
        throw new DoorLockedExcpetion("Door is locked! Go find a key in the house");
      }
      System.out.println("\nYou have entered \"" + player.getCurrentRoom().getName() + "\"!");
    } catch (InvalidDirection | DoorLockedExcpetion e) {
      System.out.println("\n\t\tError: " + e.getMessage() + "\n");
    }
  }
}

package command;

import exceptions.InvalidCommand;
import exceptions.InvalidDirection;
import game.Player;

public class Go extends CommandBase {
  public final static CommandBase instance = new Go();

  private Go() {
  }

  @Override
  public void action(Player player, String entity) throws InvalidCommand {
    try {
      player.setCurrentRoom(player.getCurrentRoom().getExit(entity));
    } catch (InvalidDirection e) {
      System.out.println("\n\t\tError: " + e.getMessage() + "\n");
    }
  }
}

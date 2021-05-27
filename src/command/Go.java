package command;

import exceptions.InvalidCommand;
import exceptions.InvalidDirection;
import game.Player;

public class Go implements Command {
  public final static Command instance = new Go();

  private Go() {
  }

  @Override
  public void action(Player player, String entity) throws InvalidCommand {
    try {
      player.setCurrentRoom(player.getCurrentRoom().getExit(entity));
      System.out.println("\nYou have entered \"" + player.getCurrentRoom().getName() + "\"!");
    } catch (InvalidDirection e) {
      System.out.println("\n\t\tError: " + e.getMessage() + "\n");
    }
  }
}

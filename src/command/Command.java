package command;

import exceptions.InvalidCommand;
import game.Player;

public interface Command {
  public abstract void action(Player player, String entity) throws InvalidCommand;

  public static Command getCommand(String command) throws InvalidCommand {
    String _command = command.toLowerCase().intern();
    if ("go".startsWith(_command)) {
      return Go.instance;
    } else if ("take".startsWith(_command)) {
      return Take.instance;
    } else if ("place".startsWith(_command)) {
      return Place.instance;
    } else if ("view".startsWith(_command)) {
      return View.instance;
    } else {
      throw new InvalidCommand("Invalid command " + command);
    }
  }
}

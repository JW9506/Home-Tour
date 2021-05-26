package command;

import exceptions.InvalidCommand;
import game.Player;

public abstract class CommandBase {
  public abstract void action(Player player, String entity) throws InvalidCommand;

  public static CommandBase getCommand(String command) throws InvalidCommand {
    switch (command.toLowerCase()) {
      case "go":
        return Go.instance;
      case "take":
        return Take.instance;
      case "place":
        return Place.instance;
      case "view":
        return View.instance;
      default:
        throw new InvalidCommand("Invalid command " + command);
    }
  }
}

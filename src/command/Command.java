package command;

import exceptions.InvalidCommand;

public interface Command {
  public abstract void action(String entity) throws InvalidCommand;

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
    } else if ("inspect".startsWith(_command)) {
      return Inspect.instance;
    } else {
      throw new InvalidCommand("Invalid command " + command);
    }
  }
}

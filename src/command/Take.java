package command;

import exceptions.InvalidCommand;
import fixtures.Item;
import game.Player;

public class Take extends CommandBase {
  public final static CommandBase instance = new Take();

  private Take() {
  }

  @Override
  public void action(Player player, String entity) throws InvalidCommand {
    for (Item item : player.getCurrentRoom().getItems()) {
      if (item.getName().toLowerCase().intern().equals(entity.toLowerCase().intern())) {
        player.getCurrentRoom().removeItems(item);
        player.addToInventory(item);
        System.out.println("\nYou have taken \"" + item.getName() + "\" from the room!");
        return;
      }
    }
    throw new InvalidCommand("There is no \"" + entity + "\" in the room!");
  }
}

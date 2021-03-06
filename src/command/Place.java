package command;

import common.g;
import exceptions.InvalidCommand;
import fixtures.Item;
import game.Player;

public class Place implements Command {
  public final static Command instance = new Place();

  private Place() {
  }

  @Override
  public void action(String entity) throws InvalidCommand {
    Player player = g.getPlayer();
    for (Item item : player.getInventory()) {
      if (item.getName().toLowerCase().intern().equals(entity.toLowerCase().intern())) {
        player.removeFromInventory(item);
        player.getCurrentRoom().addItem(item);
        System.out.println("\nYou have placed \"" + item.getName() + "\" to the room!");
        return;
      }
    }
    throw new InvalidCommand("\nThere is no \"" + entity + "\" in your inventory!");
  }
}

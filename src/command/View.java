package command;

import java.util.List;

import common.StringUtils;
import exceptions.InvalidCommand;
import fixtures.Item;
import game.Player;

public class View extends CommandBase {
  public final static CommandBase instance = new View();

  private View() {
  }

  @Override
  public void action(Player player, String entity) throws InvalidCommand {
    if ("inventory".startsWith(entity.toLowerCase())) {
      List<Item> inv = player.getInventory();
      System.out.println(StringUtils.repeat('_', 80));
      System.out.println("|You have:" + StringUtils.repeat(' ', 69) + "|");
      if (inv.isEmpty()) {
        System.out.println(StringUtils.prettify("Your inventory is empty."));
      } else {
        for (Item item : inv) {
          int rightSpace = item.getName().length();
          System.out.println("|\t" + item.getName() + StringUtils.repeat(' ', 71 - rightSpace) + "|");
        }
      }
      System.out.println(StringUtils.repeat('-', 80));
    }
  }
}

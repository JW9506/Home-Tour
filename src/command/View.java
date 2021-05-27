package command;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import common.StringUtils;
import exceptions.InvalidCommand;
import fixtures.Item;
import game.Player;

public class View implements Command {
  public final static Command instance = new View();

  private View() {
  }

  @Override
  public void action(Player player, String entity) throws InvalidCommand {
    if ("inventory".startsWith(entity.toLowerCase().intern())) {
      List<Item> inv = player.getInventory();
      System.out.println(StringUtils.repeat('_', 80));
      System.out.println("|You have:" + StringUtils.repeat(' ', 69) + "|");
      if (inv.isEmpty()) {
        System.out.println(StringUtils.prettify("Your inventory is empty."));
      } else {
        Map<Item, Integer> itemDist = player.getInventoryItemDistribution();
        for (Entry<Item, Integer> item : itemDist.entrySet()) {
          String name = item.getKey().getName();
          String str = "\t" + name + " x " + item.getValue();
          int rightSpace = str.length();
          System.out.println("|\t" + str + StringUtils.repeat(' ', 64 - rightSpace) + "|");
        }
      }
      System.out.println(StringUtils.repeat('-', 80));
    } else {
      throw new InvalidCommand(entity + " is not a valid entity to be viewed");
    }
  }
}

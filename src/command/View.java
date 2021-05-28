package command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import common.StringUtils;
import common.g;
import exceptions.InvalidCommand;
import fixtures.Fixture;
import fixtures.Item;
import game.Player;

public class View implements Command {
  public final static Command instance = new View();

  private View() {
  }

  private void inventoryViewHandler(Player player, String entity) throws InvalidCommand {
    List<Item> inv = player.getInventory();
    List<Fixture> environFixture = player.getCurrentRoom().getEnvironmentFixture();
    List<Item> items = new ArrayList<>();

    // Pretty print inventory content
    System.out.println(StringUtils.repeat('_', 80));
    System.out.println("|You have:" + StringUtils.repeat(' ', 69) + "|");
    if (inv.isEmpty()) {
      System.out.println(StringUtils.prettify("Your inventory is empty."));
    } else {
      Map<Item, Integer> itemDist = player.getInventoryItemDistribution();
      for (Entry<Item, Integer> item : itemDist.entrySet()) {
        items.add(item.getKey());
        String name = item.getKey().getName();
        String str = "\t" + name + " x " + item.getValue();
        int rightSpace = str.length();
        System.out.println("|\t" + str + StringUtils.repeat(' ', 64 - rightSpace) + "|");
      }
    }

    System.out.println(StringUtils.repeat('-', 80));
    if (!items.isEmpty()) {
      System.out.println("Use items from your inventory?");
      System.out.println("Use: ");
      for (Item item : items) {
        System.out.println("\t" + item.getName());
      }
      List<Method> methods = null;
      String[] input = null;
      Item selectedItem = null;
      while (g.loop.isLoop()) {
        System.out.print("\nEnter your decision whether to use items: (leave blank if no)\n\t");
        input = g.collectInput();
        if (input[0] == null || input[0].trim().isEmpty()) {
          g.loop.setLoop(false);
          break;
        }
        for (Item item : items) {
          if (item.getName().toLowerCase().intern().equals(input[0].toLowerCase().intern())) {
            selectedItem = item;
            break;
          }
        }
        try {
          // methods = Item.availableMethods(Utils.upperCaseFirstLetter(input[0]));
          methods = selectedItem.availableMethods();
          g.loop.setLoop(false);
        } catch (Exception e) {
          System.out.println("\nYou don't have " + "\"" + input[0] + "\"" + " in your inventory!");
        }
      }
      g.loop.setLoop(true);
      if (input[0] != null && !input[0].trim().isEmpty()) {
        System.out.print("\nSelect a specific action:\n\t");
        input = g.collectInput();
        if (input[0] != null && !input[0].trim().isEmpty()) {
          Method mtd = null;
          for (Method method : methods) {
            if (method.getName().toLowerCase().intern().startsWith(("do_" + input[0]).toLowerCase().intern())) {
              mtd = method;
              break;
            }
          }
          List<Object> hydrateInput = new ArrayList<>();
          for (int i = 1; i < input.length; ++i) {
            boolean flag = false;
            for (int j = 0; j < inv.size(); ++j) {
              Item item = inv.get(j);
              if (item.getName().toLowerCase().intern().equals(input[i].toLowerCase().intern())) {
                hydrateInput.add(item);
                flag = true;
                break;
              }
            }
            if (!flag) {
              for (int j = 0; j < environFixture.size(); ++j) {
                Fixture fixture = environFixture.get(j);
                if (fixture.getName().toLowerCase().intern().startsWith(input[i].toLowerCase().intern())) {
                  hydrateInput.add(fixture);
                  flag = true;
                  break;
                }
              }
            }
            if (!flag) {
              hydrateInput.add(input[i]);
            }
          }
          selectedItem.invokeByClsMethod(mtd, hydrateInput.toArray(new Object[0]));
        }
      }
    }
  }

  @Override
  public void action(String entity) throws InvalidCommand {
    Player player = g.getPlayer();
    if ("inventory".startsWith(entity.toLowerCase().intern())) {
      inventoryViewHandler(player, entity);
    } else {
      throw new InvalidCommand(entity + " is not a valid entity to be viewed");
    }
  }
}

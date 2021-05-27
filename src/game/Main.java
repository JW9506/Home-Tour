package game;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import command.Command;
import common.StringUtils;
import common.Utils;
import common.g;
import exceptions.InvalidCommand;
import fixtures.Item;
import fixtures.Room;

public class Main {
  public static void main(String[] args) {
    RoomManager.init();
    Player player = new Player();
    player.setCurrentRoom(RoomManager.startingRoom);

    while (g.loop.isLoop()) {
      Utils.clearScreen();
      System.out.println(StringUtils.repeat('-', 10) + "Welcome to" + StringUtils.repeat('-', 60));
      System.out.println(StringUtils.repeat('-', 19) + "Home Tour" + StringUtils.repeat('-', 52));
      System.out.println("\t\t\t\t\t\tt[ake]  ITEM");
      System.out.println("\t\t\t\t\t\tg[o]    DIRECTION");
      System.out.println("\t\t\t\t\t\tp[lace] ITEM");
      System.out.println("\t\t\t\t\t\tv[iew]  i[nventory]");
      Room currRoom = player.getCurrentRoom();
      System.out.println("You're in " + currRoom.getName());
      System.out.println("\n" + currRoom.getLongDescription());
      try {
        Map<String, Room> rooms = currRoom.getExits();
        System.out.println("\n" + "Exits:");
        for (Entry<String, Room> e : rooms.entrySet()) {
          System.out.println("\t" + e.getKey().toUpperCase().intern() + ": " + e.getValue().getName());
        }
        List<Item> items = currRoom.getItems();
        if (!items.isEmpty()) {
          System.out.println("\n" + "Items in the room:");
          for (int i = 0; i < items.size(); ++i) {
            System.out.println("\t" + items.get(i).getName());
          }
        }
        System.out.print("\nEnter your action:\n\t");
        parse(g.collectInput(), player);
      } catch (InvalidCommand e) {
        System.out.println("\n\t\tError: " + e.getMessage() + "\n");
      } finally {
        System.out.println("\nHit Enter to Continue...");
        try {
          g.sc.nextLine();
        } catch (Exception e) {
          g.Exit();
        }
      }
    }
  }

  private static void printRoom(Player player) {
    System.out.println(player.getCurrentRoom().getLongDescription());
  }

  private static void parse(String[] command, Player player) throws InvalidCommand {
    if (command.length < 2) {
      throw new InvalidCommand("Invalid command, please read the instruction");
    }
    Command cmd = Command.getCommand(command[0]);
    cmd.action(player, command[1]);
  }
}

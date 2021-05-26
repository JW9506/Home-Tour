package game;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import command.CommandBase;
import common.StringUtils;
import exceptions.InvalidCommand;
import fixtures.Item;
import fixtures.Room;

public class Main {
  private static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    RoomManager.init();
    Player player = new Player();
    player.setCurrentRoom(RoomManager.startingRoom);

    while (true) {
      System.out.println("\t\t\t\t\t\tgo   [direction]");
      System.out.println("\t\t\t\t\t\ttake [item]");
      System.out.println("\t\t\t\t\t\tview  inventory");
      System.out.println("\t\t\t\t\t\tplace [item]");
      Room currRoom = player.getCurrentRoom();
      System.out.println("You're in " + currRoom.getName());
      System.out.println("\n" + currRoom.getLongDescription());
      try {
        Map<String, Room> rooms = currRoom.getExits();
        System.out.println("\n" + "Exits:");
        for (Entry<String, Room> e : rooms.entrySet()) {
          System.out.println("\t" + e.getKey().toUpperCase() + ": " + e.getValue().getName());
        }
        List<Item> items = currRoom.getItems();
        if (!items.isEmpty()) {
          System.out.println("\n" + "Items in the room:");
          for (int i = 0; i < items.size(); ++i) {
            System.out.println("\t" + items.get(i).getName());
          }
        }
        parse(collectInput(), player);
        System.out.println();
      } catch (InvalidCommand e) {
        System.out.println(e.getMessage());
      }
      System.out.println(StringUtils.repeat('-', 10) + "Welcome to" + StringUtils.repeat('-', 60));
      System.out.println(StringUtils.repeat('-', 19) + "Home Tour" + StringUtils.repeat('-', 52));
    }
  }

  private static void printRoom(Player player) {
    System.out.println(player.getCurrentRoom().getLongDescription());
  }

  private static String[] collectInput() {
    String input = "";
    try {
      input = sc.nextLine();
    } catch (Exception e) {
      System.out.println("\nThank you for visiting");
      System.exit(0);
    }
    return input.split(" ");
  }

  private static void parse(String[] command, Player player) throws InvalidCommand {
    if (command.length < 2) {
      throw new InvalidCommand("Invalid command, please read the instruction");
    }
    CommandBase cmd = CommandBase.getCommand(command[0]);
    cmd.action(player, command[1]);
  }
}

package game;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import exceptions.InvalidCommand;
import exceptions.InvalidDirection;
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
      System.out.println("You are in " + currRoom.getName());
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
      } catch (InvalidDirection e) {
        System.out.println(e.getMessage());
      } catch (InvalidCommand e) {
        System.out.println(e.getMessage());
      }
      System.out.println(new String(new char[80]).replace("\0", "-"));
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

  // TODO: Dependency Inversion Principle
  private static void parse(String[] command, Player player) throws InvalidDirection, InvalidCommand {
    if (command.length < 2) {
      throw new InvalidCommand("Invalid command");
    }
    switch (command[0].toLowerCase()) {
      default:
        break;
      case "go":
        player.setCurrentRoom(player.getCurrentRoom().getExit(command[1]));
        break;
      case "take":
        for (Item item : player.getCurrentRoom().getItems()) {
          if (item.getName().toLowerCase().equals(command[1].toLowerCase())) {
            player.getCurrentRoom().removeItems(item);
            player.addToInventory(item);
            System.out.println("\nYou have taken \"" + item.getName() + "\" from the room!");
            return;
          }
        }
        throw new InvalidCommand("There is no \"" + command[1] + "\" in the room!");
      case "view":
        if ("inventory".startsWith(command[1].toLowerCase())) {
          List<Item> inv = player.getInventory();
          System.out.println("\nYou have:");
          if (inv.isEmpty()) {
            System.out.println("\tYour inventory is empty.");
          } else {
            for (Item item : inv) {
              System.out.println("\t" + item.getName());
            }
          }
        }
        break;
      case "place":
        for (Item item : player.getInventory()) {
          if (item.getName().toLowerCase().equals(command[1].toLowerCase())) {
            player.removeFromInventory(item);
            player.getCurrentRoom().addItem(item);
            System.out.println("\nYou have placed \"" + item.getName() + "\" to the room!");
            return;
          }
        }
        throw new InvalidCommand("\nThere is no \"" + command[1] + "\" in your inventory!");
    }
  }
}

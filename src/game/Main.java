package game;

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
      Room currRoom = player.getCurrentRoom();
      System.out.println("You are in " + currRoom.getName());
      System.out.println("\n" + currRoom.getLongDescription());
      try {
        Map<String, Room> rooms = currRoom.getExits();
        System.out.println("\n" + "Exits:");
        for (Entry<String, Room> e : rooms.entrySet()) {
          System.out.println("\t" + e.getKey().toUpperCase() + ": " + e.getValue().getName());
        }
        Item[] items = currRoom.getItems();
        if (items != null) {
          System.out.println("\n" + "Items:");
          for (int i = 0; i < items.length; ++i) {
            System.out.println("\t" + items[i].getName());
          }
        }
        parse(collectInput(), player);
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

  private static void parse(String[] command, Player player) throws InvalidDirection, InvalidCommand {
    if (command.length < 2) {
      throw new InvalidCommand("Invalid command, use \"go [direction]\"");
    }
    player.setCurrentRoom(player.getCurrentRoom().getExit(command[1]));
  }
}

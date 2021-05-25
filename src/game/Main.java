package game;

import java.util.Scanner;

import exceptions.InvalidDirection;
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
      System.out.println("\n" + "Exits:");
      try {
        Room[] rooms = currRoom.getExits();
        for (int i = 0; i < rooms.length; ++i) {
          if (rooms[i] == null) {
            continue;
          }
          String direction = "";
          if (!RoomManager.directionMap.containsKey(i)) {
            throw new Exception("Invalid room count for " + rooms[i].getName());
          } else {
            direction = RoomManager.directionMap.get(i);
          }
          System.out.println("\t" + direction + ": " + (rooms[i].getName()));
        }
        parse(collectInput(), player);
      } catch (InvalidDirection e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        sc.next();
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

  private static void parse(String[] command, Player player) throws InvalidDirection {
    if (command.length < 2) {
      throw new InvalidDirection("Invalid command, use \"go [direction]\"");
    }
    player.setCurrentRoom(player.getCurrentRoom().getExit(command[1]));
  }
}

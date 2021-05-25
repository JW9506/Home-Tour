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
    // printRoom(player);
    while (true) {
      System.out.println("You are in " + player.getCurrentRoom().getName());
      try {
        Room[] rooms = player.getCurrentRoom().getExits();
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
          System.out.println(direction + ": " + (rooms[i].getName()));
        }
        parse(collectInput(), player);
        System.out.println(player.getCurrentRoom().getName());
      } catch (InvalidDirection e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        sc.next();
      }
      System.out.println();
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
      System.out.println("Thank you for visiting");
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

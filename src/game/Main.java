package game;

import java.util.Scanner;

import fixtures.Room;

public class Main {
  private static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    RoomManager.init();
    Player player = new Player();
    player.setCurrentRoom(RoomManager.startingRoom);
    // printRoom(player);
    while (true) {
      System.out.print("\nYou are in ");
      System.out.println(player.getCurrentRoom().getName());
      try {
        Room[] rooms = player.getCurrentRoom().getExits();
        for (int i = 0; i < rooms.length; ++i) {
          if (rooms[i] == null) {
            continue;
          }
          String direction = "";
          switch (i) {
            default:
              throw new Exception("Invalid room count for " + rooms[i].getName());
            case 0:
              direction = "North";
              break;
            case 1:
              direction = "East";
              break;
            case 2:
              direction = "South";
              break;
            case 3:
              direction = "West";
              break;
          }
          System.out.print(direction + ": ");
          System.out.println(rooms[i].getName());
        }
        parse(collectInput(), player);
        System.out.println(player.getCurrentRoom().getName());
      } catch (Exception e) {
        sc.next();
      }
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

  private static void parse(String[] command, Player player) throws Exception {
    if (command.length < 2) {
      throw new Exception();
    }
    player.setCurrentRoom(player.getCurrentRoom().getExit(command[1]));
  }
}

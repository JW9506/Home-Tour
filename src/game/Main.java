package game;

import java.util.Scanner;

public class Main {
  private static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {
    RoomManager.init();
    Player player = new Player();
    player.setCurrentRoom(RoomManager.startingRoom);
    printRoom(player);
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

package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import command.Command;
import exceptions.InvalidCommand;
import fixtures.Fixture;
import fixtures.Item;
import fixtures.Room;
import game.Player;

final public class g {
  private g() {
  }
  private static Player player = null;

  public static Player getPlayer() {
    return player;
  }

  public static void setPlayer(Player player) {
    g.player = player;
  }

  public static Scanner sc = new Scanner(System.in);

  public static Loop loop = new Loop();

  public static void menuUI() throws InvalidCommand {
    Room currRoom = g.getPlayer().getCurrentRoom();
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
    List<Fixture> environment = currRoom.getEnvironmentFixture();
    if (!environment.isEmpty()) {
      System.out.println("\n" + "Other Fixtures in the Room:");
      for (int i = 0; i < environment.size(); ++i) {
        System.out.println("\t" + environment.get(i).getName());
      }
    }
    System.out.print("\nEnter your action:\n\t");
    parse(collectInput());
  }

  public static void parse(String[] command) throws InvalidCommand {
    if (command.length < 2) {
      throw new InvalidCommand("Invalid command, please read the instruction");
    }
    Command cmd = Command.getCommand(command[0]);
    cmd.action(command[1]);
  }

  /**
   * Input Collecter that treats double quotation mark as a piece of long string
   * @return String[]
  */
  public static String[] collectInput() {
    String input = "";
    try {
      input = sc.nextLine();
    } catch (Exception e) {
      Exit();
    }
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    char[] charArr = input.toCharArray();
    for (int i = 0; i < charArr.length; ++i) {
      char c = charArr[i];
      if (c == '"') {
        while (charArr[i + 1] != '"') {
          c = charArr[i + 1];
          sb.append(c);
          ++i;
        }
        res.add(sb.toString());
        sb.delete(0, sb.length());
        ++i;
      } else if (c != ' ') {
        sb.append(c);
      } else {
        res.add(sb.toString());
        sb.delete(0, sb.length());
      }
    }
    if (sb.length() > 0) {
      res.add(sb.toString());
    }
    if (res.size() == 0) {
      res.add(null);
    }
    return res.toArray(new String[0]);
  }

  public static void Exit() {
    System.out.println("\nThank you for visiting");
    System.exit(0);
  }
}

package game;

import common.StringUtils;
import common.Utils;
import common.g;
import exceptions.InvalidCommand;
import fixtures.Room;

public class Main {
  public static void main(String[] args) {
    RoomManager.init();
    Player player = new Player();
    RoomManager.initPlayer(player);

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
      System.out.println("\n" + "<" + currRoom.getLongDescription() + ">");
      try {
        g.menuUI();
      } catch (InvalidCommand e) {
        System.out.println("\n\t\tError: " + e.getMessage() + "\n");
      } finally {
        System.out.println("\nHit Enter to Continue...");
        g.collectInput();
      }
    }
  }
}

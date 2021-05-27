package common;

import java.util.Scanner;

final public class g {
  private g() {
  }

  public static Scanner sc = new Scanner(System.in);

  public static Loop loop = new Loop();

  public static String[] collectInput() {
    String input = "";
    try {
      input = sc.nextLine();
    } catch (Exception e) {
      Exit();
    }
    return input.split(" ");
  }

  public static void Exit() {
    System.out.println("\nThank you for visiting");
    System.exit(0);
  }
}

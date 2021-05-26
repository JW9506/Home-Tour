package common;

enum OSType {
  Bash, WindowsCMD
}

public class Utils {
  public static OSType getConsoleType() {
    try {
      Process p = Runtime.getRuntime().exec("uname");
      p.waitFor();
      if (p.exitValue() == 0) {
        return OSType.Bash;
      } else {
        return OSType.WindowsCMD;
      }
    } catch (Exception e) {
      return OSType.WindowsCMD;
    }
  }

  public static void clearScreen() {
    try {
      if (getConsoleType() == OSType.Bash) {
        new ProcessBuilder("sh", "-c", "clear").inheritIO().start().waitFor();
      } else if (getConsoleType() == OSType.WindowsCMD) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void Exit() {
    System.out.println("\nThank you for visiting");
    System.exit(0);
  }
}

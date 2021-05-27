package common;

enum OSType {
  Bash, WindowsCMD
}

final public class Utils {
  private Utils() {
  }

  private static OSType osType = getConsoleType();

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
      if (osType == OSType.Bash) {
        new ProcessBuilder("sh", "-c", "clear").inheritIO().start().waitFor();
      } else if (osType == OSType.WindowsCMD) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

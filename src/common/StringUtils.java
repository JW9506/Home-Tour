package common;

public class StringUtils {
  public static String repeat(char ch, int length) {
    return new String(new char[length]).replace("\0", String.valueOf(ch)).intern();
  }

  public static String prettify(String text) {
    return "|\t" + text + repeat(' ', 71 - text.length()) + "|";
  }
}

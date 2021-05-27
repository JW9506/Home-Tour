package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final public class g {
  private g() {
  }

  public static Scanner sc = new Scanner(System.in);

  public static Loop loop = new Loop();

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

package fixtures.items;

import common.Param;
import exceptions.InvalidCommand;
import fixtures.Item;

public class Pen extends Item {
  public Pen(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  public void do_write(@Param("paper") Object paper, @Param("text") Object string) throws InvalidCommand {
    if (paper instanceof Paper && string instanceof String) {
      Paper _paper = (Paper) paper;
      String _string = (String) string;
      _paper.setText(_string);
    } else {
      throw new InvalidCommand();
    }
  }
}

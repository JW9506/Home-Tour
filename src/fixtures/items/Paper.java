package fixtures.items;

import fixtures.Item;

public class Paper extends Item {
  public Paper(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  private String text = "";

  public String getText() {
    if (text.equals("")) {
      return "<There is nothing on this paper>";
    }
    return text;
  }

  public void setText(String text) {
    this.text = text;
    System.out.println("\n\t<Something has been written to the Paper...>");
  }

  public void do_read() {
    System.out.println("On the paper it reads:\n" + "\t" + getText());
  }
}

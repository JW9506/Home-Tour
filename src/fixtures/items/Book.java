package fixtures.items;

import fixtures.Item;

public class Book extends Item {
  public Book(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  public void do_open() {
    System.out.println("\n\t\tChapter 1: Once upon a time...");
  }
}

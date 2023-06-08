## Features

This is a command-line text-based game similar to the `Colossal Cave Adventure` game! Have fun!

## Use of Reflections and Annotation to easily extend on actions of Items.
```java
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

```

## Directions
  - t[ake]  ITEM
  - g[o]    DIRECTION
  - p[lace] ITEM
  - v[iew]  i[nventory]

## Demonstrations
![image](https://i.ibb.co/fMkm9n8/Screenshot-38.jpg)
***
![image](https://i.ibb.co/SthY0YR/Screenshot-39.jpg)
***
![image](https://i.ibb.co/7XDCkst/Screenshot-40.jpg)
***
![image](https://i.ibb.co/GFWzLwZ/Screenshot-41.jpg)
***
![image](https://i.ibb.co/3Cc2b9D/Screenshot-42.jpg)
***
![image](https://i.ibb.co/G3p8fMf/Screenshot-44.jpg)

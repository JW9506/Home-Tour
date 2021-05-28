package fixtures.others;

import java.lang.reflect.Method;

import common.Param;
import common.g;
import exceptions.InvalidCommand;
import fixtures.Fixture;
import fixtures.OtherFixture;
import fixtures.Room;

public class LightSwitch extends OtherFixture {
  public LightSwitch(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  public void do_turn(@Param("on | off") Object onOff) throws InvalidCommand {
    String _onOff = null;
    if (onOff instanceof String) {
      _onOff = (String) onOff;
    } else {
      throw new InvalidCommand();
    }
    Method mth;
    try {
      Room room = g.getPlayer().getCurrentRoom();
      mth = room.getClass().getMethod("setLightOn", Boolean.class);
      if (_onOff.equals("on")) {
        mth.invoke(room, true);
      } else if (_onOff.equals("off")) {
        mth.invoke(room, false);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw Fixture.invalidCommand;
    }
  }
}

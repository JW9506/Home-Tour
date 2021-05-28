package fixtures.rooms;

import fixtures.Room;

public class Basement extends Room {
  public Basement(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
    updateLightDesc(isLightOn());
  }

  public Basement(String name, String shortDescription, String longDescription, boolean locked) {
    super(name, shortDescription, longDescription, locked);
  }

  private Boolean lightOn = false;

  public Boolean isLightOn() {
    return lightOn;
  }

  private String longDescCache = getLongDescription();

  private void updateLightDesc(Boolean lightStatus) {
    if (isLightOn()) {
      setLongDescription(longDescCache + " [Light is on]");
    } else {
      setLongDescription(longDescCache + " [Light is off]");
    }
  }

  public void setLightOn(Boolean lightOn) {
    if (lightOn) {
      System.out.println("\n\t\tLight is turning on...");
    } else {
      System.out.println("\n\t\tLight is turning off...");
    }
    this.lightOn = lightOn;
    updateLightDesc(lightOn);
  }
}

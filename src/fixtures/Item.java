package fixtures;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.InvalidCommand;

public class Item extends Fixture {
  public Item(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  final static private String clsPrefix = "fixtures.items.";
  final static private InvalidCommand invalidCommand = new InvalidCommand(
      "Please double check your command is valid");

  public static void invokeByClsMethod(String cls, String method, Item item, Object[] params) throws InvalidCommand {
    Method mtd = null;
    int paramLength = params == null ? 0 : params.length;
    try {
      Class<?> clazz = Class.forName(clsPrefix + cls);
      List<Class<?>> sigType = new ArrayList<>(Collections.nCopies(paramLength, (Class<?>) Object.class));
      mtd = clazz.getMethod("do_" + method, (Class<?>[]) sigType.toArray(new Class<?>[0]));
    } catch (Exception e) {
      e.printStackTrace();
      throw invalidCommand;
    }
    try {
      invokeByClsMethod(mtd, item, params);
    } catch (InvalidCommand e) {
      throw e;
    }
  }

  public static void invokeByClsMethod(Method mtd, Item item, Object[] params) throws InvalidCommand {
    try {
      mtd.invoke(item, params);
    } catch (Exception e) {
      throw invalidCommand;
    }
  }

  public static List<Method> availableMethods(String cls) throws InvalidCommand {
    try {
      Class<?> clazz = Class.forName(clsPrefix + cls);
      Method[] methods = clazz.getMethods();
      System.out.println("\t\tAvailable Actions:");
      List<Method> validMethods = new ArrayList<>();
      for (Method m : methods) {
        if (m.getName().startsWith("do_")) {
          validMethods.add(m);
          System.out.println("\t\t\t" + m.getName().substring(3).intern());
        }
      }
      return validMethods;
    } catch (Exception e) {
      throw new InvalidCommand(cls + " is not a valid command");
    }
  }
}

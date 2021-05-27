package fixtures;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import common.Param;
import exceptions.InvalidCommand;

public class Item extends Fixture {
  public Item(String name, String shortDescription, String longDescription) {
    super(name, shortDescription, longDescription);
  }

  final static private String clsPrefix = "fixtures.items.";
  final static private InvalidCommand invalidCommand = new InvalidCommand(
      "Please double check your command is valid,\nor you don't have the necessary items to run the command");

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
          String[] paramNameList = getMethodParameterNamesByAnnotation(m);
          String paramStr = " ";
          if (paramNameList != null && paramNameList.length > 0) {
            for (int i = 0; i < paramNameList.length; ++i) {
              paramStr += "<" + paramNameList[i] + ">" + " ";
            }
          }
          System.out.println("\t\t\t" + m.getName().substring(3).intern() + paramStr);
        }
      }
      return validMethods;
    } catch (Exception e) {
      e.printStackTrace();
      throw new InvalidCommand(cls + " is not a valid command");
    }
  }

  private static String[] getMethodParameterNamesByAnnotation(Method method) {
    Annotation[][] parameterAnnotations = method.getParameterAnnotations();
    if (parameterAnnotations == null || parameterAnnotations.length == 0) {
      return null;
    }
    String[] parameterNames = new String[parameterAnnotations.length];
    int i = 0;
    for (Annotation[] parameterAnnotation : parameterAnnotations) {
      for (Annotation annotation : parameterAnnotation) {
        if (annotation instanceof Param) {
          Param param = (Param) annotation;
          parameterNames[i++] = param.value();
        }
      }
    }
    return parameterNames;
  }
}

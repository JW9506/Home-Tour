package fixtures;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import common.Param;
import exceptions.InvalidCommand;

public abstract class Fixture {
  private String name = "";
  private String shortDescription = "";
  private String longDescription = "";

  public Fixture(String name, String shortDescription, String longDescription) {
    this.setName(name);
    this.setShortDescription(shortDescription);
    this.setLongDescription(longDescription);
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  final static public InvalidCommand invalidCommand = new InvalidCommand(
      "Please double check your command is valid,\nor you don't have the necessary items to run the command");

  public void invokeByClsMethod(Method mtd, Object[] params) throws InvalidCommand {
    try {
      mtd.invoke(this, params);
    } catch (Exception e) {
      e.printStackTrace();
      throw invalidCommand;
    }
  }

  public Method getMethodByName(List<Method> methods, String name) {
    Method mtd = null;
    for (Method method : methods) {
      if (method.getName().toLowerCase().intern().startsWith(("do_" + name).toLowerCase().intern())) {
        mtd = method;
        break;
      }
    }
    return mtd;
  }

  public List<Method> availableMethods() throws InvalidCommand {
    try {
      Method[] methods = this.getClass().getMethods();
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
      throw new InvalidCommand("Unexpected Error");
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

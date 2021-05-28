package command;

import java.lang.reflect.Method;
import java.util.List;

import common.g;
import exceptions.InvalidCommand;
import fixtures.Fixture;

public class Inspect implements Command {
  public final static Command instance = new Inspect();

  private Inspect() {
  }

  @Override
  public void action(String entity) throws InvalidCommand {
    int idx = entity.charAt(0) - 'a';
    Fixture f = g.getPlayer().getCurrentRoom().getEnvironmentFixture().get(idx);
    List<Method> methods = f.availableMethods();
    System.out.print("\nWhat would you like to do?\n\t");
    String[] inputs = g.collectInput();
    Method mtd = f.getMethodByName(methods, inputs[0]);
    String[] params = new String[inputs.length - 1];
    for (int i = 1; i < inputs.length; ++i) {
      params[i - 1] = inputs[i];
    }
    f.invokeByClsMethod(mtd, params);
  }
}

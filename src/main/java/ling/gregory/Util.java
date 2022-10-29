package ling.gregory;

import java.lang.reflect.InvocationTargetException;

public class Util {
  @SuppressWarnings("unchecked")
  public static <T> T copy(T original) {
    if (original == null) return null;
    try {
      return (T) original.getClass().getConstructor(original.getClass()).newInstance(original);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}

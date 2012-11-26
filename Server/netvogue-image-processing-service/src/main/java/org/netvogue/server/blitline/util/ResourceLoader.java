package org.netvogue.server.blitline.util;

import java.io.IOException;
import java.util.Properties;

public class ResourceLoader {

  private static Properties props = new Properties();

  static {
    loadProperties();
  }

  public static void loadProperties() {
    try {
      props.load(ResourceLoader.class.getClassLoader().getResourceAsStream("blitline.properties"));
    } catch (IOException io) {
      System.out.println("Exception in reading applicaton properties: " + io.getMessage());
    }
    props.list(System.out);
  }

  public static String getProperty(String key) {
    return props.getProperty(key);
  }

}

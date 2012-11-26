package org.netvogue.server.blitline.model;

import java.util.HashMap;
import java.util.Map;

public class BlitlineFunction {
  
  String name;
  Map<String, String> params = new HashMap<String, String>();
  SaveParameters save;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Map<String, String> getParams() {
    return params;
  }
  public void setParams(Map<String, String> params) {
    this.params = params;
  }
  public SaveParameters getSave() {
    return save;
  }
  public void setSave(SaveParameters save) {
    this.save = save;
  }
  

}

package org.netvogue.server.blitline.model;

import java.util.List;

import org.netvogue.server.blitline.util.ResourceLoader;

public class BlitlineMessageRequest {
  
  String application_id = ResourceLoader.getProperty("application_id");
  String src;
  String type = "postback";
  String postback_url = ResourceLoader.getProperty("postback_url");
  List<BlitlineFunction> functions;
  
  public String getApplication_id() {
    return application_id;
  }
  public void setApplication_id(String application_id) {
    this.application_id = application_id;
  }
  public String getSrc() {
    return src;
  }
  public void setSrc(String src) {
    this.src = src;
  }
  public String getPostback_url() {
    return postback_url;
  }
  public void setPostback_url(String postback_url) {
    this.postback_url = postback_url;
  }
  public List<BlitlineFunction> getFunctions() {
    return functions;
  }
  public void setFunctions(List<BlitlineFunction> functions) {
    this.functions = functions;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

}

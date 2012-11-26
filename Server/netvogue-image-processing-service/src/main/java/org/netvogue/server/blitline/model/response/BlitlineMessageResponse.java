package org.netvogue.server.blitline.model.response;

import java.util.List;
import java.util.Map;

public class BlitlineMessageResponse {
  
  Map<String, String> original_meta;
  String job_id;
  List<ImageResponse> images;
  String error;
  public Map<String, String> getOriginal_meta() {
    return original_meta;
  }
  public void setOriginal_meta(Map<String, String> original_meta) {
    this.original_meta = original_meta;
  }
  public String getJob_id() {
    return job_id;
  }
  public void setJob_id(String job_id) {
    this.job_id = job_id;
  }
  public List<ImageResponse> getImages() {
    return images;
  }
  public void setImages(List<ImageResponse> images) {
    this.images = images;
  }
  public String getError() {
    return error;
  }
  public void setError(String error) {
    this.error = error;
  }  
  
}

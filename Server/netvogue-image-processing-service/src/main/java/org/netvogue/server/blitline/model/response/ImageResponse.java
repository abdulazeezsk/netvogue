package org.netvogue.server.blitline.model.response;

import java.util.Map;

public class ImageResponse {
  
  String image_identifier;
  String s3_url;
  Map<String, String> meta;
  public String getImage_identifier() {
    return image_identifier;
  }
  public void setImage_identifier(String image_identifier) {
    this.image_identifier = image_identifier;
  }
  public String getS3_url() {
    return s3_url;
  }
  public void setS3_url(String s3_url) {
    this.s3_url = s3_url;
  }
public Map<String, String> getMeta() {
	return meta;
}
public void setMeta(Map<String, String> meta) {
	this.meta = meta;
}  

}

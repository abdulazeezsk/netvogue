package org.netvogue.server.blitline.model;

import org.netvogue.server.blitline.util.ResourceLoader;

public class S3DestinationDetails {
  
  String bucket = ResourceLoader.getProperty("amazon_bucket_name");
  String key;
  
  public String getBucket() {
    return bucket;
  }
  public void setBucket(String bucket) {
    this.bucket = bucket;
  }
  public String getKey() {
    return key;
  }
  public void setKey(String key) {
    this.key = key;
  }
  
  

}

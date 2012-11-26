package org.netvogue.server.blitline.model;

public class SaveParameters {
  
  String image_identifier;
  S3DestinationDetails s3_destination;
  
  public String getImage_identifier() {
    return image_identifier;
  }
  public void setImage_identifier(String image_identifier) {
    this.image_identifier = image_identifier;
  }
  public S3DestinationDetails getS3_destination() {
    return s3_destination;
  }
  public void setS3_destination(S3DestinationDetails s3_destination) {
    this.s3_destination = s3_destination;
  }
  
  

}

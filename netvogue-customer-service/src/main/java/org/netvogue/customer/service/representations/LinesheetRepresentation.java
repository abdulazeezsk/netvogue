package org.netvogue.customer.service.representations;


public class LinesheetRepresentation {

  private String id;

  private String productLine;

  private String category;

  private String createdBy;

  private String season;

  private int year;

  private String profileLink;

  private String privacy;

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getLookbookName() {
    return season + " " + year;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(final String season) {
    this.season = season;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(final String category) {
    this.category = category;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(final String createdBy) {
    this.createdBy = createdBy;
  }

  public void setProductLine(final String productLine) {
    this.productLine = productLine;
  }

  public int getYear() {
    return year;
  }

  public void setYear(final int year) {
    this.year = year;
  }

  public String getProfileLink() {
    return profileLink;
  }

  public void setProfileLink(final String profileLink) {
    this.profileLink = profileLink;
  }

  public String getPrivacy() {
    return privacy;
  }

  public void setPrivacy(final String privacy) {
    this.privacy = privacy;
  }

  public String getProductLine() {
    return productLine;
  }

}

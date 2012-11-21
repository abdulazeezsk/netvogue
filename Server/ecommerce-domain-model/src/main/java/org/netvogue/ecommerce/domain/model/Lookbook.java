package org.netvogue.ecommerce.domain.model;

import java.util.Date;

public class Lookbook {

  private String id;

  private Date createdDate = new Date();

  private String productLine;

  private String category;

  private String createdBy;

  private Season season;

  private int year;

  private String profileLink;

  private Privacy privacy;

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getLookbookName() {
    return season.name()+" " + year;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(final Date createdDate) {
    this.createdDate = createdDate;
  }

  public Season getSeason() {
    return season;
  }

  public void setSeason(final Season season) {
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


  public Privacy getPrivacy() {
    return privacy;
  }

  public void setPrivacy(final Privacy privacy) {
    this.privacy = privacy;
  }

  public String getProductLine() {
    return productLine;
  }

}

package org.netvogue.ecommerce.domain.model;

import java.util.Date;

public class Lookbook {

  private String id;

  private Date createdDate = new Date();

  private ProductLine productLine;

  private Category category;

  private User createdBy;

  private Season season;

  private int year;


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

  public ProductLine getProductLine() {
    return productLine;
  }

  public void setProductLine(final ProductLine productLine) {
    this.productLine = productLine;
  }

  public Season getSeason() {
    return season;
  }

  public void setSeason(final Season season) {
    this.season = season;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(final User createdBy) {
    this.createdBy = createdBy;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(final Category category) {
    this.category = category;
  }

  public int getYear() {
    return year;
  }

  public void setYear(final int year) {
    this.year = year;
  }


}

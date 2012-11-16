package org.netvogue.ecommerce.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Linesheet {

  private String id;

  private String linesheetname;

  private Date createdDate = new Date();

  private Category productcategory;

  private User createdBy;

  private Set<Style> styles = new HashSet<Style>();

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getLinesheetname() {
    return linesheetname;
  }

  public void setLinesheetname(final String linesheetname) {
    this.linesheetname = linesheetname;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(final Date createdDate) {
    this.createdDate = createdDate;
  }

  public Category getProductcategory() {
    return productcategory;
  }

  public void setProductcategory(final Category productcategory) {
    this.productcategory = productcategory;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(final User createdBy) {
    this.createdBy = createdBy;
  }

  public Set<Style> getStyles() {
    return styles;
  }

  public void setStyles(final Set<Style> styles) {
    this.styles = styles;
  }

  public void addStyle(final Style style) {
    styles.add(style);
  }

}

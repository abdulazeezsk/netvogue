package org.netvogue.ecommerce.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Style {

  private String styleId;

  private String stylename;

  private String styleno;

  private String description;

  private String fabrication;

  private long price;

  private Set<StyleSize> availableSizes = new HashSet<StyleSize>();

  private Set<String> availableColors = new HashSet<String>();

  private Date createdDate;

  private User brand;

  private ProductLine productLine;

  private boolean active;

  // suman : for now not planning to implement. Will do this
  // once basic functionality is done
  private Set<Review> reviews = new HashSet<Review>();

  public Set<Review> getReviews() {
    return reviews;
  }

  public void setReviews(final Set<Review> reviews) {
    this.reviews = reviews;
  }

  public String getStyleId() {
    return styleId;
  }

  public void setStyleId(final String styleId) {
    this.styleId = styleId;
  }

  public String getStylename() {
    return stylename;
  }

  public void setStylename(final String stylename) {
    this.stylename = stylename;
  }

  public String getStyleno() {
    return styleno;
  }

  public void setStyleno(final String styleno) {
    this.styleno = styleno;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getFabrication() {
    return fabrication;
  }

  public void setFabrication(final String fabrication) {
    this.fabrication = fabrication;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(final long price) {
    this.price = price;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(final boolean active) {
    this.active = active;
  }

  public Set<StyleSize> getAvailableSizes() {
    return availableSizes;
  }

  public void setAvailableSizes(final Set<StyleSize> availableSizes) {
    this.availableSizes = availableSizes;
  }

  public Set<String> getAvailableColors() {
    return availableColors;
  }

  public void setAvailableColors(final Set<String> availableColors) {
    this.availableColors = availableColors;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(final Date createdDate) {
    this.createdDate = createdDate;
  }

  public User getBrand() {
    return brand;
  }

  public void setBrand(final User brand) {
    this.brand = brand;
  }

  public ProductLine getProductLine() {
    return productLine;
  }

  public void setProductLine(final ProductLine productLine) {
    this.productLine = productLine;
  }

}

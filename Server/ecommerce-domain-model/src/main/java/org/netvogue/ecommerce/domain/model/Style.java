package org.netvogue.ecommerce.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Style {

  private String id;

  private String styleName;

  private String styleNo;

  private String description;

  private String fabrication;

  private long price;

  private Set<StyleSize> availableSizes = new HashSet<StyleSize>();

  private Set<String> availableColors = new HashSet<String>();

  private Set<String> availableImages = new HashSet<String>();

  private Date createdDate;

  private User brand;

  private ProductLine productLine;

  private Category category;

  private Lookbook lookbook;

  private Linesheet linesheet;

  private Privacy privacy;

  public Style(final String styleName, final String styleNo, final long price) {
    this.styleName = styleName;
    this.styleNo = styleNo;
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(final Category category) {
    this.category = category;
  }

  public Lookbook getLookbook() {
    return lookbook;
  }

  public void setLookbook(final Lookbook lookbook) {
    this.lookbook = lookbook;
  }

  public Linesheet getLinesheet() {
    return linesheet;
  }

  public void setLinesheet(final Linesheet linesheet) {
    this.linesheet = linesheet;
  }

  public String getStyleName() {
    return styleName;
  }

  public void setStyleName(final String styleName) {
    this.styleName = styleName;
  }

  public String getStyleNo() {
    return styleNo;
  }

  public void setStyleNo(final String styleNo) {
    this.styleNo = styleNo;
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

  public Set<StyleSize> getAvailableSizes() {
    return availableSizes;
  }

  public void addSize(final StyleSize size) {
    availableSizes.add(size);
  }

  public void setAvailableSizes(final Set<StyleSize> availableSizes) {
    this.availableSizes = availableSizes;
  }

  public Set<String> getAvailableColors() {
    return availableColors;
  }

  public void addColor(final String color) {
    availableColors.add(color);
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

  public Set<String> getAvailableImages() {
    return availableImages;
  }

  public void addImage(final String image) {
    availableImages.add(image);
  }

  public void setAvailableImages(final Set<String> availableImages) {
    this.availableImages = availableImages;
  }

  public boolean isInLookbook() {
    return lookbook != null;
  }

  public boolean isInLinesheet() {
    return linesheet != null;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public Privacy getPrivacy() {
    return privacy;
  }

  public void setPrivacy(final Privacy privacy) {
    this.privacy = privacy;
  }

}

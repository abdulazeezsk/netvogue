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

  private String brand;

  private String productLine;

  private String category;

  private String lookbookId;

  private String linesheetId;

  private Privacy privacy;

  public Style(final String styleName, final String styleNo, final long price) {
    this.styleName = styleName;
    this.styleNo = styleNo;
    this.price = price;
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
    return lookbookId != null;
  }

  public boolean isInLinesheet() {
    return linesheetId != null;
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


  public String getBrand() {
    return brand;
  }


  public void setBrand(final String brand) {
    this.brand = brand;
  }


  public String getProductLine() {
    return productLine;
  }


  public void setProductLine(final String productLine) {
    this.productLine = productLine;
  }


  public String getCategory() {
    return category;
  }


  public void setCategory(final String category) {
    this.category = category;
  }


  public String getLookbookId() {
    return lookbookId;
  }


  public void setLookbookId(final String lookbookId) {
    this.lookbookId = lookbookId;
  }


  public String getLinesheetId() {
    return linesheetId;
  }


  public void setLinesheetId(final String linesheetId) {
    this.linesheetId = linesheetId;
  }


}

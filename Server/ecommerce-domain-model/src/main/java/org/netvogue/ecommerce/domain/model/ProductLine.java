package org.netvogue.ecommerce.domain.model;

public class ProductLine {

  private String id;

  private String name;

  private String description;

  private String size;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getSize() {
    return size;
  }

  public void setSize(final String size) {
    this.size = size;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

}

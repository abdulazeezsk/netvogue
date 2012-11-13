package org.netvogue.ecommerce.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Category {

  private String categoryType;

  private Set<ProductLine> productlines = new HashSet<ProductLine>();

  public String getCategoryType() {
    return categoryType;
  }

  public void setCategoryType(final String categoryType) {
    this.categoryType = categoryType;
  }


  public Set<ProductLine> getProductlines() {
    return productlines;
  }

  public void setProductlines(final Set<ProductLine> productlines) {
    this.productlines = productlines;
  }

}

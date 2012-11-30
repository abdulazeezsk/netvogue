package org.netvogue.ecommerce.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Category {

  private String id;

  private String categoryType;

  private List<ProductLine> productlines = new ArrayList<ProductLine>();

  public String getCategoryType() {
    return categoryType;
  }

  public void setCategoryType(final String categoryType) {
    this.categoryType = categoryType;
  }

  public void addProductLine(final ProductLine line) {
    productlines.add(line);
  }

  public void removeProductLine(final ProductLine line) {
    productlines.remove(line);
  }

  public void removeProductLine(final String name) {
    Iterator<ProductLine> it = productlines.iterator();
    while (it.hasNext()) {
      ProductLine line = it.next();
      if (line.getName().equals(name)) {
        it.remove();
      }
    }
  }

  public List<ProductLine> getProductlines() {
    return productlines;
  }

  public void setProductlines(final List<ProductLine> productlines) {
    this.productlines = productlines;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

}

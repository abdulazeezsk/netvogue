package org.netvogue.ecommerce.domain.model;


public class OrderLineItem {

  private String lineItemId;

  private Style style;

  private int quantity;

  private StyleSize styleSize;

  private long stylePrice;

  private long lineItemPrice;

  public Style getStyle() {
    return style;
  }

  public void setStyle(final Style style) {
    this.style = style;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(final int quantity) {
    this.quantity = quantity;
  }

  public String getLineItemId() {
    return lineItemId;
  }

  public void setLineItemId(final String lineItemId) {
    this.lineItemId = lineItemId;
  }


  public StyleSize getStyleSize() {
    return styleSize;
  }

  public void setStyleSize(final StyleSize styleSize) {
    this.styleSize = styleSize;
  }

  public long getStylePrice() {
    return stylePrice;
  }

  public void setStylePrice(final long stylePrice) {
    this.stylePrice = stylePrice;
  }

  public long getLineItemPrice() {
    return stylePrice*quantity;
  }

  public void setLineItemPrice(final long lineItemPrice) {
    this.lineItemPrice = lineItemPrice;
  }

}

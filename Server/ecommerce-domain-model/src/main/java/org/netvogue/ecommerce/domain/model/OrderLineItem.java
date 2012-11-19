package org.netvogue.ecommerce.domain.model;


public class OrderLineItem {

  private long lineItemId;

  private Style style;

  private int quantity;

  private StyleSize size;

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

  public long getLineItemId() {
    return lineItemId;
  }

  public void setLineItemId(final long lineItemId) {
    this.lineItemId = lineItemId;
  }

  public StyleSize getSize() {
    return size;
  }

  public void setSize(final StyleSize size) {
    this.size = size;
  }

  public long getStylePrice() {
    return stylePrice;
  }

  public void setStylePrice(final long stylePrice) {
    this.stylePrice = stylePrice;
  }

  public long getLineItemPrice() {
    return lineItemPrice;
  }

  public void setLineItemPrice(final long lineItemPrice) {
    this.lineItemPrice = lineItemPrice;
  }

}

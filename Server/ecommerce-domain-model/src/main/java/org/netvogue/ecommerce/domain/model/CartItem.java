package org.netvogue.ecommerce.domain.model;

public class CartItem {

  private Style style;

  private int quantity;

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

}

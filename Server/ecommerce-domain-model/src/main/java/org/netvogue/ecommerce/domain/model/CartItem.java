package org.netvogue.ecommerce.domain.model;

public class CartItem {

  private Style style;

  private int quantity;

  /**
   * This holds the brand comments when he rejected the items;
   */
  private String comments;

  private CartItemStatus status;

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

  public String getComments() {
    return comments;
  }

  public void setComments(final String comments) {
    this.comments = comments;
  }

  public CartItemStatus getStatus() {
    return status;
  }

  public void setStatus(final CartItemStatus status) {
    this.status = status;
  }

}

package org.netvogue.ecommerce.domain.model;

import java.util.ArrayList;
import java.util.List;

public class OrderLineItem {

  private long lineItemId;

  private Style style;

  private int quantity;

  private StyleSize size;

  private long stylePrice;

  private long lineItemPrice;

  private List<OrderLineItemReview> reviewes = new ArrayList<OrderLineItemReview>();

  public void addReview(final OrderLineItemReview review) {
    reviewes.add(review);
  }

  public List<OrderLineItemReview> getReviewes() {
    return reviewes;
  }

  public void setReviewes(final List<OrderLineItemReview> reviewes) {
    this.reviewes = reviewes;
  }

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

package org.netvogue.ecommerce.domain.model;

public class OrderLineItemReview {

  private String reviewedBy;

  private String reviewComments;

  private OrderLineItem adjustedLineItem;

  public String getReviewedBy() {
    return reviewedBy;
  }

  public void setReviewedBy(final String reviewedBy) {
    this.reviewedBy = reviewedBy;
  }

  public OrderLineItem getAdjustedLineItem() {
    return adjustedLineItem;
  }

  public void setAdjustedLineItem(final OrderLineItem adjustedLineItem) {
    this.adjustedLineItem = adjustedLineItem;
  }

  public String getReviewComments() {
    return reviewComments;
  }

  public void setReviewComments(final String reviewComments) {
    this.reviewComments = reviewComments;
  }

}

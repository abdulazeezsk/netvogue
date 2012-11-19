package org.netvogue.ecommerce.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderReview {

  private Set<OrderLineItem> lineItemsAfterReview = new HashSet<OrderLineItem>();

  private String reviewedBy;

  private Date reviewedDate;

  private String comments;

  public OrderReview() {

  }

  public Set<OrderLineItem> getLineItemsAfterReview() {
    return lineItemsAfterReview;
  }


  public void setLineItemsAfterReview(final Set<OrderLineItem> lineItemsAfterReview) {
    this.lineItemsAfterReview = lineItemsAfterReview;
  }


  public String getReviewedBy() {
    return reviewedBy;
  }

  public void setReviewedBy(final String reviewedBy) {
    this.reviewedBy = reviewedBy;
  }

  public Date getReviewedDate() {
    return reviewedDate;
  }

  public void setReviewedDate(final Date reviewedDate) {
    this.reviewedDate = reviewedDate;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(final String comments) {
    this.comments = comments;
  }

}

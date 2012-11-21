package org.netvogue.ecommerce.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderReview {

  private Set<OrderLineItem> lineItems = new HashSet<OrderLineItem>();

  private String reviewedBy;

  private Date reviewedDate;

  private String comments;

  public OrderReview() {

  }


  public Set<OrderLineItem> getLineItems() {
    return lineItems;
  }


  public void setLineItems(final Set<OrderLineItem> lineItems) {
    this.lineItems = lineItems;
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

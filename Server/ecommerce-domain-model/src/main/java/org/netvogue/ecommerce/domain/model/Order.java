package org.netvogue.ecommerce.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {

  /**
   * Suman: This is netvogue order id. This id uniquely identifies order in netvogue ecommerce db.
   */
  private String orderId;

  private Set<OrderLineItem> originalOrderedlLineItems = new HashSet<OrderLineItem>();

  private Set<OrderLineItem> finalizedLineItemsAfterReview = new HashSet<OrderLineItem>();

  private OrderTracking orderTracking;

  private long orderTotal;

  private Date orderedCreatedDate;

  private Date orderCompletionDate;

  private String resolutionComments;

  private PaymentMethod paymentMethod;

  private Address shippingAddress;

  private Address billingAddress;

  private Address brandAddress;

  private String brandName;

  private String orderedBy;

  private List<OrderReview> reviews = new ArrayList<OrderReview>();

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(final String orderId) {
    this.orderId = orderId;
  }


  public long getOrderTotal() {
    return orderTotal;
  }



  public Set<OrderLineItem> getOriginalOrderedlLineItems() {
    return originalOrderedlLineItems;
  }

  public void setOriginalOrderedlLineItems(final Set<OrderLineItem> originalOrderedlLineItems) {
    this.originalOrderedlLineItems = originalOrderedlLineItems;
  }

  public Set<OrderLineItem> getFinalizedLineItemsAfterReview() {
    return finalizedLineItemsAfterReview;
  }

  public void setFinalizedLineItemsAfterReview(final Set<OrderLineItem> finalizedLineItemsAfterReview) {
    this.finalizedLineItemsAfterReview = finalizedLineItemsAfterReview;
  }

  public Date getOrderedCreatedDate() {
    return orderedCreatedDate;
  }

  public void setOrderedCreatedDate(final Date orderedCreatedDate) {
    this.orderedCreatedDate = orderedCreatedDate;
  }

  public Date getOrderCompletionDate() {
    return orderCompletionDate;
  }

  public void setOrderCompletionDate(final Date orderCompletionDate) {
    this.orderCompletionDate = orderCompletionDate;
  }

  public String getResolutionComments() {
    return resolutionComments;
  }

  public void setResolutionComments(final String resolutionComments) {
    this.resolutionComments = resolutionComments;
  }

  public void setOrderTotal(final long orderTotal) {
    this.orderTotal = orderTotal;
  }

  public OrderTracking getOrderTracking() {
    return orderTracking;
  }

  public void setOrderTracking(final OrderTracking orderTracking) {
    this.orderTracking = orderTracking;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(final PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(final Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public String getOrderedBy() {
    return orderedBy;
  }

  public boolean isReviewed() {
    return !reviews.isEmpty();
  }

  public void setOrderedBy(final String orderedBy) {
    this.orderedBy = orderedBy;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(final Address billingAddress) {
    this.billingAddress = billingAddress;
  }

  public Address getBrandAddress() {
    return brandAddress;
  }

  public void setBrandAddress(final Address brandAddress) {
    this.brandAddress = brandAddress;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(final String brandName) {
    this.brandName = brandName;
  }

  public List<OrderReview> getReviews() {
    return reviews;
  }

  public void setReviews(final List<OrderReview> reviews) {
    this.reviews = reviews;
  }

}

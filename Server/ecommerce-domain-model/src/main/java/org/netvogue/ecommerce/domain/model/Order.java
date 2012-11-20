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
  private String id;

  private Set<OrderLineItem> originalLineItems = new HashSet<OrderLineItem>();

  private Set<OrderLineItem> finalizedLineItemsAfterReview = new HashSet<OrderLineItem>();

  private OrderTracking orderTracking;

  private long orderTotal;

  private Date ordereCreatedDate;

  private Date orderCompletionDate;

  private String resolutionComments;

  private PaymentMethod paymentMethod;

  private Address shippingAddress;

  private Address billingAddress;

  private Address brandAddress;

  private User brand;

  private User createdBy;

  private List<OrderReview> reviews = new ArrayList<OrderReview>();

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public long getOrderTotal() {
    return orderTotal;
  }


  public Set<OrderLineItem> getOriginalLineItems() {
    return originalLineItems;
  }

  public void setOriginallLineItems(final Set<OrderLineItem> originalLineItems) {
    this.originalLineItems = originalLineItems;
  }

  public Set<OrderLineItem> getFinalizedLineItemsAfterReview() {
    return finalizedLineItemsAfterReview;
  }

  public void setFinalizedLineItemsAfterReview(final Set<OrderLineItem> finalizedLineItemsAfterReview) {
    this.finalizedLineItemsAfterReview = finalizedLineItemsAfterReview;
  }

  public Date getOrdereCreatedDate() {
    return ordereCreatedDate;
  }

  public void setOrdereCreatedDate(final Date ordereCreatedDate) {
    this.ordereCreatedDate = ordereCreatedDate;
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

  public boolean isReviewed() {
    return !reviews.isEmpty();
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(final User createdBy) {
    this.createdBy = createdBy;
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

  public User getBrand() {
    return brand;
  }

  public void setBrand(final User brand) {
    this.brand = brand;
  }

  public void setOriginalLineItems(final Set<OrderLineItem> originalLineItems) {
    this.originalLineItems = originalLineItems;
  }

  public List<OrderReview> getReviews() {
    return reviews;
  }

  public void setReviews(final List<OrderReview> reviews) {
    this.reviews = reviews;
  }

  public void addReview(final OrderReview review) {
    reviews.add(review);
  }

}

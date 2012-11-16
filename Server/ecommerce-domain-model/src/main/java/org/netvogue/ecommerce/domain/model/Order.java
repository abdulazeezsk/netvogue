package org.netvogue.ecommerce.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {

  /**
   * Suman: This is netvogue order id. This id uniquely identifies order in netvogue ecommerce db.
   */
  private String orderId;

  private Set<OrderLineItem> originalLineItems = new HashSet<OrderLineItem>();

  private Set<OrderLineItem> finalLineItems = new HashSet<OrderLineItem>();

  private OrderTracking orderTracking;

  private long orderTotal;

  private Date orderedCreatedDate;

  private Date orderCompletionDate;

  private String resolutionComments;

  private PaymentMethod paymentMethod;

  private Address shippingAddress;

  private Address billingAddress;

  private String orderedBy;

  private boolean reviewed = false;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(final String orderId) {
    this.orderId = orderId;
  }

  public void addLineItem(final OrderLineItem item) {
    originalLineItems.add(item);
  }

  public void deleteLineItem(final OrderLineItem item) {
    originalLineItems.remove(item);
  }

  public Set<OrderLineItem> getLinteItems() {
    return originalLineItems;
  }

  public void setLinteItems(final Set<OrderLineItem> lineItems) {
    originalLineItems = lineItems;
  }

  public long getOrderTotal() {
    return orderTotal;
  }

  public Set<OrderLineItem> getLineItems() {
    return originalLineItems;
  }

  public void setLineItems(final Set<OrderLineItem> lineItems) {
    originalLineItems = lineItems;
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
    return reviewed;
  }

  public void setReviewed(final boolean reviewed) {
    this.reviewed = reviewed;
  }

  public void setOrderedBy(final String orderedBy) {
    this.orderedBy = orderedBy;
  }

  public Set<OrderLineItem> getOriginalLineItems() {
    return originalLineItems;
  }

  public void setOriginalLineItems(final Set<OrderLineItem> originalLineItems) {
    this.originalLineItems = originalLineItems;
  }

  public Set<OrderLineItem> getFinalLineItems() {
    return finalLineItems;
  }

  public void setFinalLineItems(final Set<OrderLineItem> finalLineItems) {
    this.finalLineItems = finalLineItems;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(final Address billingAddress) {
    this.billingAddress = billingAddress;
  }

}

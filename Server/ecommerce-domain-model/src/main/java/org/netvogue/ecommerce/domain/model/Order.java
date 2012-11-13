package org.netvogue.ecommerce.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {

  /**
   * Suman: This is netvogue order id. This id uniquely identifies order in netvogue ecommerce db.
   */
  private String orderId;

  private Set<OrderLineItem> lineItems = new HashSet<OrderLineItem>();

  private OrderTracking orderTracking;

  private long orderTotal;

  private Date orderedCreatedDate;

  private Date orderCompletionDate;

  private String resolutionComments;

  private String shoppingCartId;

  private PaymentMethod paymentMethod;

  private ShippingAddress shippingAddress;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(final String orderId) {
    this.orderId = orderId;
  }

  public void addLineItem(final OrderLineItem item) {
    lineItems.add(item);
  }

  public void deleteLineItem(final OrderLineItem item) {
    lineItems.remove(item);
  }

  public Set<OrderLineItem> getLinteItems() {
    return lineItems;
  }

  public void setLinteItems(final Set<OrderLineItem> lineItems) {
    this.lineItems = lineItems;
  }

  public long getOrderTotal() {
    return orderTotal;
  }

  public Set<OrderLineItem> getLineItems() {
    return lineItems;
  }

  public void setLineItems(final Set<OrderLineItem> lineItems) {
    this.lineItems = lineItems;
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

  public String getShoppingCartId() {
    return shoppingCartId;
  }

  public void setShoppingCartId(final String shoppingCartId) {
    this.shoppingCartId = shoppingCartId;
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

  public ShippingAddress getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(final ShippingAddress shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

}

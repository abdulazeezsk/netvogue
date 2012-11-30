package org.netvogue.ecommerce.domain.model;

public class OrderTracking {

  private String company;

  /**
   * Suman: For now this will be gharpay order id. We should use this order id when ever we talk to gharpay services.
   *
   */
  private String orderTrackingId;

  private OrderStatus status;

  public String getCompany() {
    return company;
  }

  public void setCompany(final String company) {
    this.company = company;
  }

  public String getOrderTrackingId() {
    return orderTrackingId;
  }

  public void setOrderTrackingId(final String orderTrackingId) {
    this.orderTrackingId = orderTrackingId;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(final OrderStatus status) {
    this.status = status;
  }


}

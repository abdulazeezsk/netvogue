package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Order;
import org.netvogue.ecommerce.domain.model.OrderReview;
import org.netvogue.ecommerce.domain.model.OrderStatus;

import java.util.List;

public interface OrderManagementDao {

  void placeOrder(Order order);

  void deleteOrderLineItem(final String orderLineItemId);

  void updateOrderStatus(String orderId, OrderStatus status);

  List<Order> findOrdersByUser(final String userName);

  List<Order> findOrdersByUserAndByStatus(final String userName, final OrderStatus status);

  void addReview(final String orderId, final OrderReview review);

  List<Order> findOrdersByTrackingId(final String trackingId);

}

package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Order;
import org.netvogue.ecommerce.domain.model.OrderStatus;

public interface OrderManagementDao {

  void placeOrder(Order order);

  void updateOrderStatus(String orderId, OrderStatus status);
}

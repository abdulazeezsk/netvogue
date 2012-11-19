package org.netvogue.ecommerce.persistence.mongo;

import org.netvogue.ecommerce.domain.model.Order;
import org.netvogue.ecommerce.domain.model.OrderReview;
import org.netvogue.ecommerce.domain.model.OrderStatus;
import org.netvogue.ecommerce.persistence.OrderManagementDao;

import java.util.List;

public class DefaultOrderManagementDaoImpl implements OrderManagementDao {

  public void placeOrder(final Order order) {

  }

  public void deleteOrderLineItem(final String orderLineItemId) {

  }

  public void updateOrderStatus(final String orderId, final OrderStatus status) {

  }

  public List<Order> findOrdersByUser(final String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Order> findOrdersByUserAndByStatus(final String userName, final OrderStatus status) {
    // TODO Auto-generated method stub
    return null;
  }

  public void addReview(final String orderId, final OrderReview review) {
    // TODO Auto-generated method stub

  }

}

package org.netvogue.ecommerce.persistence.mongo;

import static org.netvogue.ecommerce.persistence.util.Util.massageAsObjectId;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.netvogue.ecommerce.domain.model.Order;
import org.netvogue.ecommerce.domain.model.OrderLineItem;
import org.netvogue.ecommerce.domain.model.OrderReview;
import org.netvogue.ecommerce.domain.model.OrderStatus;
import org.netvogue.ecommerce.persistence.OrderManagementDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultOrderManagementDaoImpl implements OrderManagementDao {

  private MongoTemplate template;

  public static String ORDER_COLLECTION_NAME = "orders";

  public DefaultOrderManagementDaoImpl(final MongoTemplate template) {
    this.template = template;
  }

  public void placeOrder(final Order order) {
    template.insert(order, ORDER_COLLECTION_NAME);
  }

  public void deleteOrderLineItem(final String orderId, final String orderLineItemId) {
    Order order = template.findById(massageAsObjectId(orderId), Order.class, ORDER_COLLECTION_NAME);
    Iterator<OrderLineItem> it = order.getOriginalLineItems().iterator();
    while (it.hasNext()) {
      OrderLineItem lineItem = it.next();
      if (lineItem.getLineItemId() == orderLineItemId) {
        it.remove();
      }
    }

    template.save(order, ORDER_COLLECTION_NAME);
  }

  public void updateOrderStatus(final String orderId, final OrderStatus status) {
    Order order = template.findById(massageAsObjectId(orderId), Order.class, ORDER_COLLECTION_NAME);
    order.getOrderTracking().setStatus(status);
    template.save(order, ORDER_COLLECTION_NAME);
  }

  public List<Order> findOrdersByUser(final String userName) {
    List<Order> orders = template.find(new Query(where("createdBy").is(userName)), Order.class, ORDER_COLLECTION_NAME);
    if (orders == null) {
      orders = new ArrayList<Order>();
    }
    return orders;
  }

  public List<Order> findOrdersByBrand(final String userName) {
    List<Order> orders = template.find(new Query(where("brand").is(userName)), Order.class, ORDER_COLLECTION_NAME);
    if (orders == null) {
      orders = new ArrayList<Order>();
    }
    return orders;
  }

  public List<Order> findOrdersByUserAndByStatus(final String userName, final OrderStatus status) {
    List<Order> orders = template.find(
        new Query(where("createdBy").is(userName).andOperator(where("orderTracking.status").is(status.toString()))),
        Order.class, ORDER_COLLECTION_NAME);
    if (orders == null) {
      orders = new ArrayList<Order>();
    }
    return orders;
  }

  public List<Order> findOrdersByBrandAndStatus(final String userName, final OrderStatus status) {
    List<Order> orders = template.find(
        new Query(where("brand").is(userName).andOperator(where("orderTracking.status").is(status.toString()))),
        Order.class, ORDER_COLLECTION_NAME);
    if (orders == null) {
      orders = new ArrayList<Order>();
    }
    return orders;
  }

  public void addReview(final String orderId, final OrderReview review) {
    Order order = template.findById(massageAsObjectId(orderId), Order.class, ORDER_COLLECTION_NAME);
    order.addReview(review);
    template.save(order, ORDER_COLLECTION_NAME);
  }

  public Order getOrderByTrackingId(final String trackingId) {
    List<Order> orders = template.find(new Query(where("orderTracking.trackingId").is(trackingId)), Order.class,
        ORDER_COLLECTION_NAME);
    if (orders.size() > 1) {
      throw new RuntimeException("how come there are " + orders.size() + " orders with trackingId:" + trackingId);
    }
    return orders.get(0);
  }

}

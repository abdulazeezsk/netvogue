package org.netvogue.ecommerce.domain.model;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {

  private String cartId;

  private Set<CartItem> items = new HashSet<CartItem>();

  private PaymentMethod paymentMethod;

  private ShippingAddress shippingAddress;

  private String customerId;

  private CartStatus status;

  public ShoppingCart(final String customerId) {
    this.customerId = customerId;
  }

  public void addItem(final Style style) {
    CartItem item = new CartItem();
    item.setQuantity(1);
    item.setStyle(style);
    items.add(item);
  }

  public void deleteItem(final String styleId) {
    CartItem itemToDelte = null;
    for (CartItem item : items) {
      if (item.getStyle().getStyleId() == styleId) {
        itemToDelte = item;
        break;
      }
    }

    if (itemToDelte != null) {
      items.remove(itemToDelte);
    }
  }

  public void updateQuantity(final String styleId, final int newQuantity) {

    for (CartItem item : items) {
      if (item.getStyle().getStyleId() == styleId) {
        item.setQuantity(newQuantity);
        break;
      }
    }
  }

  public Set<CartItem> getItems() {
    return items;
  }

  public void setItems(final Set<CartItem> items) {
    this.items = items;
  }

  public String getCartId() {
    return cartId;
  }

  public void setCartId(final String cartId) {
    this.cartId = cartId;
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

  public String getCustomerId() {
    return customerId;
  }

  public CartStatus getStatus() {
    return status;
  }

  public void setStatus(final CartStatus status) {
    this.status = status;
  }

}

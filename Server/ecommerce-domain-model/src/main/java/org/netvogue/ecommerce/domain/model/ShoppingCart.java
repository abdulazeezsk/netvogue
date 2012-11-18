package org.netvogue.ecommerce.domain.model;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {

  private String cartId;

  private Set<CartItem> items = new HashSet<CartItem>();

  private String customerId;

  public ShoppingCart(final String customerId) {
    this.customerId = customerId;
  }

  public void addItem(final Style style, final StyleSize size) {
    CartItem item = new CartItem();
    item.setQuantity(1);
    item.setStyle(style);
    item.setSize(size);
    items.add(item);
  }

  public void deleteItem(final String styleId) {
    CartItem itemToDelte = null;
    for (CartItem item : items) {
      if (item.getStyle().getId() == styleId) {
        itemToDelte = item;
        break;
      }
    }

    if (itemToDelte != null) {
      items.remove(itemToDelte);
    }
  }

  public void updateQuantity(final String styleId, final StyleSize size, final int newQuantity) {

    if (newQuantity <= 0) {
      throw new ShoppingCartException("quantity should be greather than zero");
    }

    for (CartItem item : items) {
      if (item.getStyle().getId() == styleId && item.getSize().equals(size)) {
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

  public String getCustomerId() {
    return customerId;
  }

  public void checkOut() {
    validateCheckoutRules();
  }

  private void validateCheckoutRules() {

    if (items.size() == 0) {
      throw new ShoppingCartException("there are no items in the cart to checkout");
    }

  }

}

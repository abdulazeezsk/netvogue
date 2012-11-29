package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.CartItem;
import org.netvogue.ecommerce.domain.model.ShoppingCart;

public interface ShoppingCartdao {
  ShoppingCart getCartForUser(final String userName);
  void addItemToCart(final String userName, final CartItem item);
  void deleteItemFromCart(final String userName, final CartItem item);
  void updateCartItem(final String userName, final CartItem newItem);
}

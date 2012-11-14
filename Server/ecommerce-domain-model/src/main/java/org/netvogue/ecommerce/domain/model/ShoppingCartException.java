package org.netvogue.ecommerce.domain.model;

public class ShoppingCartException extends RuntimeException {

  private static final long serialVersionUID = -2558279958672430228L;

  public ShoppingCartException(final String message) {
    super(message);
  }

  public ShoppingCartException(final String message, final Throwable e) {
    super(message, e);
  }

  public ShoppingCartException(final Throwable e) {
    super(e);
  }
}

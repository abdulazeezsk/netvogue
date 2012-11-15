package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Style;

public interface StyleDao {

  void addStyle(Style style);

  void deleteStyle(String styleId);

  void activateStyle(String styleId);

  void deactivateStyle(String styleId);

}

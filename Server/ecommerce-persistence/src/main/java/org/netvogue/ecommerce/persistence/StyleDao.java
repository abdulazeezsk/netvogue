package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Style;

public interface StyleDao {

  void addStyle(Style style);

  void deleteStyle(long styleId);

  void activateStyle(long styleId);

  void deactivateStyle(long styleId);

}

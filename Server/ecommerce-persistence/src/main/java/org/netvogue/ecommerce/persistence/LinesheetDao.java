package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Style;

import java.util.List;

public interface LinesheetDao {

  void addLinesheet(final Linesheet sheet);

  void deleteLinesheet(final String linesheetId);

  void addStyleToLinesheet(final String linesheetId, final Style style);

  void deleteStyleFromLinesheet(final String linesheetId, final String styleId);

  List<Linesheet> findAllLinesheets();

  List<Linesheet> findLinesheetsByuser(final String userName);

  Linesheet getLinesheetById(final String linesheetId);


}

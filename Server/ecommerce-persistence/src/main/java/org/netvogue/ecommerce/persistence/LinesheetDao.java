package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Linesheet;

import java.util.List;

public interface LinesheetDao {

  void addLinesheet(final Linesheet sheet);

  void deleteLinesheet(final String linesheetId);

  List<Linesheet> findAllLinesheets();

  List<Linesheet> findLinesheetsByUser(final String userName);

  List<Linesheet> findLinesheetsByProductLine(final String productLineName);

  List<Linesheet> findLinesheetsByCategory(final String categoryId);

  Linesheet getLinesheetById(final String linesheetId);


}

package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Lookbook;

import java.util.List;

public interface LookbookDao {

  void addLookbook(final Lookbook lookbook);

  void deleteLookbook(final String lookbookId);

  List<Lookbook> findAllLookbooks();

  List<Lookbook> findLookbooksByUser(final String userName);

  List<Lookbook> findLookbooksByProductLine(final String productLineName);

  List<Lookbook> findLookbooksByCategory(final String categoryId);

  Lookbook getLookbookById(final String lookBookId);

}

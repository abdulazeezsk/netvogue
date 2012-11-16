package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.domain.model.Style;

import java.util.List;

public interface LookbookDao {

  void addLookbook(final Lookbook lookbook);

  void deleteLookbook(final String lookbookId);

  void addStyleToLookbook(final String lookbookId, final Style style);

  void deleteStyleFromLookbook(final String lookbookId, final String styleId);

  List<Lookbook> findAllLookbooks();

  List<Lookbook> findLookbooksByuser(final String userName);
}

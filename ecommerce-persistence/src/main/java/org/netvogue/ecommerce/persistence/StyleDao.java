package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Style;

import java.util.List;
import java.util.Set;

public interface StyleDao {

  void addStyleToLookbook(final String lookbookId, Style style);

  void unassignLookbook(String styleId);

  void addStyleToLinesheet(final String linesheetId, String styleId);

  void unassignLinesheet(String styleId);

  List<Style> findStylesByBrand(final String userName);

  List<Style> findStylesByProductLine(final String productLineNmae);

  List<Style> findStylesByCategory(final String categoryId);

  List<Style> findStylesByLinesheet(final String linesheetId);

  List<Style> findStylesByLookbook(final String lookbookId);

  List<Style> findStylesByPriceRange(final long fromPrice, final long toPrice);

  List<Style> findStylesByPriceRangeAndBrand(final long fromPrice, final long toPrice, final String brandUserName);

  List<Style> findStylesByPriceRangeAndBrands(final long fromPrice, final long toPrice,
      final Set<String> brandsUserNames);

  Style getStyleById(final String styleId);

}

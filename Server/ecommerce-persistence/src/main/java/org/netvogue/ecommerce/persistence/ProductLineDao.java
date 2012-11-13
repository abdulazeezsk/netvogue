package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.ProductLine;

import java.util.Set;

public interface ProductLineDao {

  void addProductLine(long categoryId, ProductLine productLine);

  void deleteProductLine(long categoryId, long productLineId);

  Set<ProductLine> findAllProductLinesByCategory(long categoryId);

  Set<ProductLine> findAllProductLines();

}

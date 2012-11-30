package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.ProductLine;

import java.util.List;

public interface CategoryDao {

  void addCategory(Category category);

  void deleteCategory(String categoryId);

  Category getCategory(String categoryId);

  List<Category> findAllCategories();

  void addProductLine(String categoryId, ProductLine productLine);

  void deleteProductLine(String categoryId, String productLineName);

  List<ProductLine> findAllProductLinesByCategory(String categoryId);

  ProductLine getProductLine(final String categoryId, final String productLineName);

}

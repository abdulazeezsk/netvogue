package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Category;

import java.util.List;

public interface CategoryDao {

  public static String CATEGORY_COLLECTION_NAME = "categories";

  void addCategory(Category category);

  void deleteCategory(String categoryId);

  Category getCategory(String categoryId);

  List<Category> findAllCategories();
}

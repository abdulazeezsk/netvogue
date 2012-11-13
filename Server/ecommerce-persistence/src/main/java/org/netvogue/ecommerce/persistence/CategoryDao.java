package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Category;

import java.util.Set;

public interface CategoryDao {

  void addCategory(Category category);

  void deleteCategory(long categoryId);

  Category getCategory(long categoryId);

  Set<Category> findAllCategories();
}

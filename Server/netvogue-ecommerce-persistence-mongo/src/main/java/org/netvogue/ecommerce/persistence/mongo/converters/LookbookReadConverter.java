package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.ProductLine;
import org.netvogue.ecommerce.domain.model.Season;
import org.netvogue.ecommerce.persistence.CategoryDao;
import org.netvogue.ecommerce.persistence.UserDao;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

import java.util.Date;

public class LookbookReadConverter implements Converter<DBObject, Lookbook> {

  private CategoryDao categoryDao;

  private UserDao userDao;

  public Lookbook convert(final DBObject source) {
    Lookbook lookbook = new Lookbook();
    lookbook.setId(source.get("_id").toString());
    lookbook.setCreatedDate((Date) source.get("createdDate"));
    lookbook.setCreatedBy(userDao.getActiveUser((String) source.get("createdBy")));
    lookbook.setProfileLink((String)source.get("profileLink"));
    lookbook.setPrivacy(Privacy.valueOf((String)source.get("privacy")));

    Category category = categoryDao.getCategory((String) source.get("categoryId"));
    lookbook.setCategory(category);

    lookbook.setSeason(Season.valueOf((String) source.get("season")));
    lookbook.setYear((Integer) source.get("year"));

    String productLineName = (String) source.get("productLineName");

    for (ProductLine prdLine : category.getProductlines()) {
      if (prdLine.getName().equals(productLineName)) {
        lookbook.setProductLine(prdLine);
        break;
      }
    }

    return lookbook;
  }

  public CategoryDao getCategoryDao() {
    return categoryDao;
  }

  public void setCategoryDao(final CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(final UserDao userDao) {
    this.userDao = userDao;
  }

}

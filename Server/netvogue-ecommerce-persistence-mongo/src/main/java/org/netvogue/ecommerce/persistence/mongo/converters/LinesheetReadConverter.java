package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.ProductLine;
import org.netvogue.ecommerce.domain.model.Season;
import org.netvogue.ecommerce.persistence.CategoryDao;
import org.netvogue.ecommerce.persistence.UserDao;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

import java.util.Date;

public class LinesheetReadConverter implements Converter<DBObject, Linesheet> {

  private CategoryDao categoryDao;

  private UserDao userDao;

  public Linesheet convert(final DBObject source) {
    Linesheet linesheet = new Linesheet();
    linesheet.setId(source.get("_id").toString());
    linesheet.setCreatedDate((Date) source.get("createdDate"));
    linesheet.setProfileLink((String)source.get("profileLink"));
    linesheet.setPrivacy(Privacy.valueOf((String)source.get("privacy")));
    linesheet.setCreatedBy(userDao.getActiveUser((String) source.get("createdBy")));

    Category category = categoryDao.getCategory((String) source.get("categoryId"));
    linesheet.setCategory(category);

    linesheet.setSeason(Season.valueOf((String) source.get("season")));
    linesheet.setYear((Integer) source.get("year"));

    String productLineName = (String) source.get("productLineName");

    for (ProductLine prdLine : category.getProductlines()) {
      if (prdLine.getName().equals(productLineName)) {
        linesheet.setProductLine(prdLine);
        break;
      }
    }

    return linesheet;
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

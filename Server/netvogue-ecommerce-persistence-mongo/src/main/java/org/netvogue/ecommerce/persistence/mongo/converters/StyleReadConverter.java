package org.netvogue.ecommerce.persistence.mongo.converters;

import org.bson.types.ObjectId;
import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.ProductLine;
import org.netvogue.ecommerce.domain.model.Style;
import org.netvogue.ecommerce.domain.model.StyleSize;
import org.netvogue.ecommerce.persistence.CategoryDao;
import org.netvogue.ecommerce.persistence.LinesheetDao;
import org.netvogue.ecommerce.persistence.LookbookDao;
import org.netvogue.ecommerce.persistence.UserDao;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import java.util.Date;

public class StyleReadConverter implements Converter<DBObject, Style> {
  private LookbookDao lookbookDao;

  private LinesheetDao linesheetDao;

  private UserDao userDao;

  private CategoryDao categoryDao;

  public Style convert(final DBObject source) {

    String styleName = (String) source.get("styleName");
    String styleNo = (String) source.get("styleNo");
    long price = (Long) source.get("price");

    Lookbook lookbook = lookbookDao.getLookbookById((String) source.get("lookbookId"));
    Linesheet linesheet = linesheetDao.getLinesheetById((String) source.get("linesheetId"));

    Style style = new Style(styleName, styleNo, price);
    style.setId(((ObjectId) source.get("_id")).toString());
    style.setLookbook(lookbook);
    style.setLinesheet(linesheet);
    style.setDescription((String) source.get("description"));
    style.setFabrication((String) source.get("fabrication"));
    style.setBrand(userDao.getActiveUser((String) source.get("brand")));
    style.setCreatedDate((Date) source.get("createdDate"));
    style.setPrivacy(Privacy.valueOf((String)source.get("privacy")));

    Category category = categoryDao.getCategory((String) source.get("categoryId"));
    style.setCategory(category);

    String productLineName = (String) source.get("productLineName");

    for (ProductLine prdLine : category.getProductlines()) {
      if (prdLine.getName().equals(productLineName)) {
        style.setProductLine(prdLine);
        break;
      }
    }

    BasicDBList availableSizes = (BasicDBList) source.get("availableSizes");

    for (Object obj : availableSizes) {
      style.addSize(StyleSize.valueOf((String) obj));
    }

    BasicDBList availableImages = (BasicDBList) source.get("availableImages");

    for (Object obj : availableImages) {
      style.addImage((String) obj);
    }

    BasicDBList availableColors = (BasicDBList) source.get("availableColors");

    for (Object obj : availableColors) {
      style.addColor((String) obj);
    }

    return style;
  }

  public LookbookDao getLookbookDao() {
    return lookbookDao;
  }

  public void setLookbookDao(final LookbookDao lookbookDao) {
    this.lookbookDao = lookbookDao;
  }

  public LinesheetDao getLinesheetDao() {
    return linesheetDao;
  }

  public void setLinesheetDao(final LinesheetDao linesheetDao) {
    this.linesheetDao = linesheetDao;
  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(final UserDao userDao) {
    this.userDao = userDao;
  }

  public CategoryDao getCategoryDao() {
    return categoryDao;
  }

  public void setCategoryDao(final CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

}

package org.netvogue.ecommerce.persistence.mongo;

import static org.netvogue.ecommerce.persistence.util.Util.massageAsObjectId;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.domain.model.Style;
import org.netvogue.ecommerce.persistence.LookbookDao;
import org.netvogue.ecommerce.persistence.StyleDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Set;

public class DefaultStyleDaoImpl implements StyleDao {

  public static String STYLES_COLLECTION_NAME = "styles";

  private MongoTemplate mongoTemplate;

  private LookbookDao lookbookDao;

  private Linesheet linesheetDao;

  public DefaultStyleDaoImpl(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public void addStyleToLookbook(final String lookbookId, final Style style) {
    Lookbook lookbook = lookbookDao.getLookbookById(lookbookId);
    style.setLookbook(lookbook);
    mongoTemplate.insert(style, STYLES_COLLECTION_NAME);
  }

  public void deleteStyleFromLookbook(final String lookbookId, final String styleId) {

    mongoTemplate.remove(new Query(where("_id").is(massageAsObjectId(styleId))), STYLES_COLLECTION_NAME);
  }


  public List<Style> findStylesByBrand(final String userName) {
   List<Style> styles = mongoTemplate.find(new Query(where("brand").is(userName)), Style.class, STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByProductLine(final String productLineNmae) {
    List<Style> styles = mongoTemplate.find(new Query(where("productLine").is(productLineNmae)), Style.class, STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByCategory(final String categoryId) {
    List<Style> styles = mongoTemplate.find(new Query(where("categoryId").is(categoryId)), Style.class, STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByLinesheet(final String linesheetId) {
    List<Style> styles = mongoTemplate.find(new Query(where("linesheetId").is(linesheetId)), Style.class, STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByLookbook(final String lookbookId) {
    List<Style> styles = mongoTemplate.find(new Query(where("lookbookId").is(lookbookId)), Style.class, STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByPriceRange(final long fromPrice, final long toPrice) {
    List<Style> styles = mongoTemplate.find(
                              new Query(where("price").gte(fromPrice).andOperator(where("price").lte(toPrice))), Style.class,
                              STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByPriceRangeAndBrand(final long fromPrice, final long toPrice, final String brandUserName) {
    List<Style> styles = mongoTemplate.find(
                              new Query(where("price").gte(fromPrice).andOperator(
                              where("price").lte(toPrice).andOperator(where("brand").is(brandUserName)))), Style.class,
                              STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByPriceRangeAndBrands(final long fromPrice, final long toPrice, final Set<String> brandsUserNames) {
    List<Style> styles = mongoTemplate.find(
                             new Query(where("price").gte(fromPrice).andOperator(
                             where("price").lte(toPrice).andOperator(where("brand").in(brandsUserNames)))), Style.class,
                             STYLES_COLLECTION_NAME);
    return styles;
  }



  public Style getStyleById(final String styleId) {
    Style style = mongoTemplate.findOne(new Query(where("_id").is(massageAsObjectId(styleId))), Style.class, STYLES_COLLECTION_NAME);
    return style;
  }

  public LookbookDao getLookbookDao() {
    return lookbookDao;
  }

  public void setLookbookDao(final LookbookDao lookbookDao) {
    this.lookbookDao = lookbookDao;
  }

  public MongoTemplate getMongoTemplate() {
    return mongoTemplate;
  }

  public void setMongoTemplate(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public Linesheet getLinesheetDao() {
    return linesheetDao;
  }

  public void setLinesheetDao(final Linesheet linesheetDao) {
    this.linesheetDao = linesheetDao;
  }
}

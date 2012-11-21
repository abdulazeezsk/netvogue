package org.netvogue.ecommerce.persistence.mongo;

import static org.netvogue.ecommerce.persistence.util.Util.massageAsObjectId;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Style;
import org.netvogue.ecommerce.persistence.LinesheetDao;
import org.netvogue.ecommerce.persistence.LookbookDao;
import org.netvogue.ecommerce.persistence.StyleDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Set;

public class DefaultStyleDaoImpl implements StyleDao {

  public static String STYLES_COLLECTION_NAME = "styles";

  private MongoTemplate mongoTemplate;

  private LookbookDao lookbookDao;

  private LinesheetDao linesheetDao;

  public DefaultStyleDaoImpl() {
  }

  public void addStyleToLookbook(final String lookbookId, final Style style) {
    style.setLookbookId(lookbookId);
    mongoTemplate.insert(style, STYLES_COLLECTION_NAME);
  }

  public void unassignLookbook(final String styleId) {
    Style style = mongoTemplate.findOne(new Query(where("_id").is(massageAsObjectId(styleId))), Style.class, STYLES_COLLECTION_NAME);

    if(style.isInLinesheet()) {
      Linesheet lt = linesheetDao.getLinesheetById(style.getLinesheetId());
      throw new RuntimeException("this style is added to a linesheet:" + lt.getLinesheetName() + ". please remove it from linesheet first");
    }
    mongoTemplate.remove(new Query(where("_id").is(massageAsObjectId(styleId))), STYLES_COLLECTION_NAME);
  }

  public void addStyleToLinesheet(final String linesheetId, final String styleId) {
    //Currently style to linesheet is 1-1
     mongoTemplate.findAndModify(new Query(where("_id").is(massageAsObjectId(styleId))), new Update().set("linesheetId", linesheetId), Style.class, STYLES_COLLECTION_NAME);
  }

  public void unassignLinesheet(final String styleId) {
    //Currently style to linesheet is 1-1
    mongoTemplate.findAndModify(new Query(where("_id").is(massageAsObjectId(styleId))), new Update().unset("linesheetId"), Style.class, STYLES_COLLECTION_NAME);

  }
  public List<Style> findStylesByBrand(final String userName) {
   List<Style> styles = mongoTemplate.find(new Query(where("brand").is(userName)), Style.class, STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByProductLine(final String productLineName) {
    List<Style> styles = mongoTemplate.find(new Query(where("productLine").is(productLineName)), Style.class, STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByCategory(final String category) {
    List<Style> styles = mongoTemplate.find(new Query(where("category").is(category)), Style.class, STYLES_COLLECTION_NAME);
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
    List<Style> styles = mongoTemplate.find(new Query(where("price").gte(fromPrice).andOperator(where("price").lte(toPrice))),
                                            Style.class,
                                            STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByPriceRangeAndBrand(final long fromPrice, final long toPrice, final String brandUserName) {
    List<Style> styles = mongoTemplate.find(new Query(where("price").gte(fromPrice).andOperator(where("price").lte(toPrice).andOperator(where("brand").is(brandUserName)))),
                                            Style.class,
                                            STYLES_COLLECTION_NAME);
    return styles;
  }

  public List<Style> findStylesByPriceRangeAndBrands(final long fromPrice, final long toPrice, final Set<String> brandsUserNames) {
    List<Style> styles = mongoTemplate.find(new Query(where("price").gte(fromPrice).andOperator(where("price").lte(toPrice).andOperator(where("brand").in(brandsUserNames)))),
                                            Style.class,
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

  public LinesheetDao getLinesheetDao() {
    return linesheetDao;
  }

  public void setLinesheetDao(final LinesheetDao linesheetDao) {
    this.linesheetDao = linesheetDao;
  }

  public MongoTemplate getMongoTemplate() {
    return mongoTemplate;
  }

  public void setMongoTemplate(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

}

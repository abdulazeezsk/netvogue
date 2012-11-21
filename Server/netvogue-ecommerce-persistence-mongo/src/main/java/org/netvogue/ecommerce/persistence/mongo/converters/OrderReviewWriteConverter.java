package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.OrderLineItem;
import org.netvogue.ecommerce.domain.model.OrderReview;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class OrderReviewWriteConverter implements Converter<OrderReview, DBObject> {

  public DBObject convert(final OrderReview source) {

    BasicDBObject dbReview = new BasicDBObject();
    dbReview.put("reviewedBy", source.getReviewedBy());
    dbReview.put("reviewDate", source.getReviewedDate());

    BasicDBList reviewLineItems = new BasicDBList();

    for (OrderLineItem lineItem : source.getLineItems()) {
      BasicDBObject dbLineItem = new BasicDBObject();
      dbLineItem.put("lineItemId", lineItem.getLineItemId());
      StyleWriteConverter converter = new StyleWriteConverter();
      DBObject dbStyle = converter.convert(lineItem.getStyle());
      dbLineItem.put("style", dbStyle);
      dbLineItem.put("quantity", lineItem.getQuantity());
      dbLineItem.put("styleSize", lineItem.getStyleSize().toString());
      dbLineItem.put("stylePrice", lineItem.getStylePrice());
      dbLineItem.put("lineItemPrice", lineItem.getLineItemPrice());
      reviewLineItems.add(dbLineItem);
    }

    dbReview.put("lineItems", reviewLineItems);
    return dbReview;
  }

}

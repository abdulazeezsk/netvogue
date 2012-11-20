package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Address;
import org.netvogue.ecommerce.domain.model.Order;
import org.netvogue.ecommerce.domain.model.OrderLineItem;
import org.netvogue.ecommerce.domain.model.OrderReview;
import org.netvogue.ecommerce.persistence.util.Util;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class OrderWriteConveter implements Converter<Order, DBObject > {

  public DBObject convert(final Order source) {
    BasicDBObject dbOject = new BasicDBObject();

    if(source.getId() != null) {
        dbOject.put("_id", Util.massageAsObjectId(source.getId()));
    }

    BasicDBList originalLineItemsList = new BasicDBList();

    for(OrderLineItem lineItem : source.getOriginalLineItems()) {
      BasicDBObject dbLineItem = new BasicDBObject();
      dbLineItem.put("lineItemId", lineItem.getLineItemId());
      StyleWriteConverter converter = new StyleWriteConverter();
      DBObject dbStyle = converter.convert(lineItem.getStyle());
      dbLineItem.put("style", dbStyle);
      dbLineItem.put("quantity", lineItem.getQuantity());
      dbLineItem.put("styleSize", lineItem.getStyleSize().toString());
      dbLineItem.put("stylePrice", lineItem.getStylePrice());
      dbLineItem.put("lineItemPrice", lineItem.getLineItemPrice());
      originalLineItemsList.add(dbLineItem);
    }


    dbOject.put("originallLineItems", originalLineItemsList);

    BasicDBList finalLineItemsList = new BasicDBList();

    for(OrderLineItem lineItem : source.getFinalizedLineItemsAfterReview()) {
      BasicDBObject dbLineItem = new BasicDBObject();
      dbLineItem.put("lineItemId", lineItem.getLineItemId());
      StyleWriteConverter converter = new StyleWriteConverter();
      DBObject dbStyle = converter.convert(lineItem.getStyle());
      dbLineItem.put("style", dbStyle);
      dbLineItem.put("quantity", lineItem.getQuantity());
      dbLineItem.put("styleSize", lineItem.getStyleSize().toString());
      dbLineItem.put("stylePrice", lineItem.getStylePrice());
      dbLineItem.put("lineItemPrice", lineItem.getLineItemPrice());
      finalLineItemsList.add(dbLineItem);
    }

    dbOject.put("finalizedLineItemsAfterReview", finalLineItemsList);

    BasicDBObject orderTracking = new BasicDBObject();
    orderTracking.put("company",  source.getOrderTracking().getCompany());
    orderTracking.put("trackingId", source.getOrderTracking().getOrderTrackingId());
    orderTracking.put("status", source.getOrderTracking().getStatus());

    dbOject.put("orderTracking", orderTracking);

    dbOject.put("orderTotal", source.getOrderTotal());
    dbOject.put("orderedCreatedDate", source.getOrdereCreatedDate());
    dbOject.put("orderCompletionDate", source.getOrderCompletionDate());
    dbOject.put("resolutionComments", source.getResolutionComments());
    dbOject.put("paymentMethod", source.getPaymentMethod().toString());


    DBObject shippingAddress = addressToDBObject(source.getShippingAddress());
    dbOject.put("shippingAddress", shippingAddress);

    DBObject billingAddress = addressToDBObject(source.getBillingAddress());
    dbOject.put("billingAddress", billingAddress);

    DBObject brandAddress = addressToDBObject(source.getBrandAddress());
    dbOject.put("brandAddress", brandAddress) ;

    dbOject.put("brand", source.getBrand().getUsername());
    dbOject.put("createdBy", source.getCreatedBy().getUsername());

    BasicDBList dbReviews = new BasicDBList();

    for(OrderReview review : source.getReviews()) {
      BasicDBObject dbReview = new BasicDBObject();
      dbReview.put("reviewedBy", review.getReviewedBy().getUsername());
      dbReview.put("reviewDate", review.getReviewedDate());

      BasicDBList reviewLineItems = new BasicDBList();

      for(OrderLineItem lineItem : review.getLineItems()) {
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
      dbReviews.add(dbReview);
    }

    dbOject.put("reviews", dbReviews);
    dbOject.put("termsAndConditionsLink", source.getTermsAndCondtionsLink());
    dbOject.put("_class", Order.class.getName());

    return dbOject;
  }

  private DBObject addressToDBObject(final Address address) {
    BasicDBObject dbAddress = new BasicDBObject();
    dbAddress.put("firstName", address.getFirstName());
    dbAddress.put("lastName", address.getLastName());
    dbAddress.put("emailId", address.getEmailId());
    dbAddress.put("address", address.getAddress());
    dbAddress.put("city", address.getCity());
    dbAddress.put("state", address.getState());
    dbAddress.put("zipCode", address.getZipCode());
    dbAddress.put("country", address.getCountry());
    dbAddress.put("contactNumber", address.getContactNumber());
    return dbAddress;

  }
}

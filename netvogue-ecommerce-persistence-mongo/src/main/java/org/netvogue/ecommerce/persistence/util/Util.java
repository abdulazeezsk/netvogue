package org.netvogue.ecommerce.persistence.util;

import org.bson.types.ObjectId;

public class Util {

  public static ObjectId massageAsObjectId(final String id) {
    return new ObjectId(id);
  }
}

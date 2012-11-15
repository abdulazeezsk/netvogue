package org.netvogue.ecommerce.persistence.mongo;

import org.netvogue.ecommerce.domain.model.Review;
import org.netvogue.ecommerce.domain.model.User;
import org.netvogue.ecommerce.persistence.ReviewDao;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DefaultReviewDaoImpl implements ReviewDao {

  private MongoTemplate mongoTemplate;

  public DefaultReviewDaoImpl(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public void addReview(final long styleId, final Review review) {
    // TODO Auto-generated method stub

  }

  public void deleteReview(final long reviewId, final long styleId) {
    // TODO Auto-generated method stub

  }

  public void voteReview(final long reviewId, final long styleId, final User votedBy) {
    // TODO Auto-generated method stub

  }

}

package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.Review;
import org.netvogue.ecommerce.domain.model.User;

public interface ReviewDao {

  void addReview(long styleId, Review review);

  void deleteReview(long reviewId, long styleId);

  void voteReview(long reviewId, long styleId, User votedBy);
}

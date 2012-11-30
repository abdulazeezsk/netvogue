package org.netvogue.ecommerce.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * we don't implement reviews for now. If time permits will implement at the end;
 * 
 */
public class Review {

  private String reviewComment;

  private String reviewedBy;

  private Date reviewdDate;

  private Set<Vote> votes = new HashSet<Vote>();

  public String getReviewComment() {
    return reviewComment;
  }

  public void setReviewComment(final String reviewComment) {
    this.reviewComment = reviewComment;
  }

  public String getReviewedBy() {
    return reviewedBy;
  }

  public void setReviewedBy(final String reviewedBy) {
    this.reviewedBy = reviewedBy;
  }

  public Date getReviewdDate() {
    return reviewdDate;
  }

  public void setReviewdDate(final Date reviewdDate) {
    this.reviewdDate = reviewdDate;
  }

  public Set<Vote> getVotes() {
    return votes;
  }

  public void setVotes(final Set<Vote> votes) {
    this.votes = votes;
  }

}

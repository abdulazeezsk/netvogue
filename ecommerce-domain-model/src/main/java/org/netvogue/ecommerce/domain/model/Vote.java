package org.netvogue.ecommerce.domain.model;

import java.util.Date;

/**
 *
 * for now we don't implement reviews and votes. will do this at the end when time permits.
 * 
 */
public class Vote {

  private String votedBy;

  private Date votedDate;

  public String getVotedBy() {
    return votedBy;
  }

  public void setVotedBy(final String votedBy) {
    this.votedBy = votedBy;
  }

  public Date getVotedDate() {
    return votedDate;
  }

  public void setVotedDate(final Date votedDate) {
    this.votedDate = votedDate;
  }

}

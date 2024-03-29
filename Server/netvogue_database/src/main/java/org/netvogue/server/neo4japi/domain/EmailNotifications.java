package org.netvogue.server.neo4japi.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class EmailNotifications {

  @GraphId
  Long nodeId;

  boolean networkRequestFlag = true;

  boolean newsletterFlag = true;

  public EmailNotifications() {

  }

  public boolean isNetworkRequestFlag() {
    return networkRequestFlag;
  }

  public void setNetworkRequestFlag(boolean networkRequestFlag) {
    this.networkRequestFlag = networkRequestFlag;
  }

  public boolean isNewsletterFlag() {
    return newsletterFlag;
  }

  public void setNewsletterFlag(boolean newsletterFlag) {
    this.newsletterFlag = newsletterFlag;
  }

  public Long getNodeId() {
    return nodeId;
  }

  public void setNodeId(Long nodeId) {
    this.nodeId = nodeId;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (nodeId == null) {
      return false;
    }
    if (!(other instanceof User)) {
      return false;
    }
    return nodeId.equals(((User) other).nodeId);
  }

  @Override
  public int hashCode() {
    return nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
  }

}

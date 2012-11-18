package org.netvogue.server.neo4japi.common;

public enum UnsubscribeNotificationsType {

  NETWORK_REQUEST("11+99"), NEWSLETTER_REQUEST("12+99");

  private final String value;

  private UnsubscribeNotificationsType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

}

package org.netvogue.ecommerce.domain.model;

public enum Season {
  SPRING_SUMMER("SPRING SUMMER"), FALL_WINTER("FALL WINTER");

  private String name;

  private Season(final String name) {
    this.name = name;
  }

  public String toName() {
    return name;
  }
}

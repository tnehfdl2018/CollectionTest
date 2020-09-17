package com.soobineey.collection;

public class SortPart implements Comparable<SortPart> {
  private String asksPrice = null;
  private String asksQuantity = null;
  private String bidsPrice = null;
  private String bidsQuantity = null;
  private String target = null;

  public SortPart(String target, String asksPrice, String asksQuantity, String bidsPrice, String bidsQuantity) {
    this.target = target;
    this.asksPrice = asksPrice;
    this.asksQuantity = asksQuantity;
    this.bidsPrice = bidsPrice;
    this.bidsQuantity = bidsQuantity;
  }

  public String getAsksPrice() {
    return asksPrice;
  }

  public String getAsksQuantity() {
    return asksQuantity;
  }

  public String getBidsPrice() {
    return bidsPrice;
  }

  public String getBidsQuantity() {
    return bidsQuantity;
  }


  @Override
  public int compareTo(SortPart current) {
    if (target.equals("asksPrice")) {
      return this.asksPrice.compareTo(current.asksPrice);
    } else if (target.equals("asksQuantity")) {
      return this.asksPrice.compareTo(current.asksPrice);
    }else if (target.equals("bidsPrice")) {
      return this.asksPrice.compareTo(current.asksPrice);
    } else {
      return this.asksPrice.compareTo(current.asksPrice);
    }
  }
}

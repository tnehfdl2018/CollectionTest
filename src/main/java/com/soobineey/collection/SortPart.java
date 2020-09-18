package com.soobineey.collection;

public class SortPart implements Comparable<SortPart> {
  private String asksPrice = null;
  private String asksQuantity = null;
  private String bidsPrice = null;
  private String bidsQuantity = null;
  private String target = null;

  // 한 줄에 보여줄 4개의 데이터를 초기화 시킨다.
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


  // 실제 sort되는 제공 메소드
  @Override
  public int compareTo(SortPart current) {
    // if문으로 sorting의 기준을 판단하고 sort를 진행한다.
    if (target.equals("asksPrice")) {
      return Double.valueOf(this.asksPrice).compareTo(Double.valueOf(current.asksPrice));
    } else if (target.equals("asksQuantity")) {
      return Double.valueOf(this.asksQuantity).compareTo(Double.valueOf(current.asksQuantity));
    }else if (target.equals("bidsPrice")) {
      return Double.valueOf(this.bidsPrice).compareTo(Double.valueOf(current.bidsPrice));
    } else {
      return Double.valueOf(this.bidsQuantity).compareTo(Double.valueOf(current.bidsQuantity));
    }
//    if (target.equals("asksPrice")) {
//      return this.asksPrice.compareTo(current.asksPrice);
//    } else if (target.equals("asksQuantity")) {
//      return this.asksQuantity.compareTo(current.asksQuantity);
//    }else if (target.equals("bidsPrice")) {
//      return this.bidsPrice.compareTo(current.bidsPrice);
//    } else {
//      return this.bidsQuantity.compareTo(current.bidsQuantity);
//    }
  }
}

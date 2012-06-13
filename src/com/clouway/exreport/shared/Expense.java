package com.clouway.exreport.shared;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class Expense implements Serializable {


  private String name;
  private double price;

  public Expense(String name, double price) {

    this.name =   name;
    this.price = price;
  }

  public Expense() {
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public boolean isPriceValid() {
    return price > 0;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}

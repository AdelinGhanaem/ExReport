package com.clouway.exreport.shared.entites;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class Expense implements Serializable, Comparable<Expense> {


  private String name;
  private double price;
  private Date date;

  public Expense(String name, double price, Date date) {

    this.name = name;
    this.price = price;
    this.date = date;
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

  public Date getDate() {
    return date;
  }

  @Override
  public int compareTo(Expense expense) {
    if (name.equals(expense.getName())) {
      if (date.equals(expense.getDate())) {
        return new Double(price).compareTo(expense.getPrice());
      }
      return date.compareTo(expense.getDate());
    }
    return name.compareTo(expense.getName());
  }


}

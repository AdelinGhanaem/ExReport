package com.clouway.exreport.shared;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Adio
 * Date: 6/10/12
 * Time: 2:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Month implements Serializable {

  private int year;
  private int month;

  public Month(int year, int month) {
    this.year = year;
    this.month = month;

  }

  public Month() {

  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }


}

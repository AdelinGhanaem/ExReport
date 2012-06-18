package com.clouway.exreport.shared.entites;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Adio
 * Date: 6/10/12
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class Day implements Serializable {

  private  int day;
  
  private  int month;
  
  private  int year;

  public Day(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public Day() {

  }


  public int getDay() {
    return day;
  }

  public int getMonth() {
    return month;
  }

  public int getYear() {
    return year;
  }
}

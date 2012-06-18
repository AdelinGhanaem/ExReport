package com.clouway.exreport.shared.entites;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class Year implements Serializable {

  private int year;

  public Year() {
  }

  public Year(int year) {

    this.year = year;
  }

  public int getYear() {
    return year;
  }
}

package com.clouway.exreport.shared.entites;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class Token implements Serializable {

  private String user;


  public Token(String user) {
    this.user = user;
  }

  public Token() {
  }


  public String getUser() {
    return user;
  }


}

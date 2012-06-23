package com.clouway.exreport.shared.entites;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class Account implements Serializable {


  private String email;

  private String password;

  public Account(String email) {
    this.email = email;
  }

  public Account() {

  }

  public Account(String email, String password) {

    this.email = email;

    this.password = password;
  }


  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

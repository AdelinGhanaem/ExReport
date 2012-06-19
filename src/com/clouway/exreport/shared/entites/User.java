package com.clouway.exreport.shared.entites;

import sun.security.krb5.PrincipalName;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class User implements Serializable {

  private String username;
  private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }


  public String getPassword() {
    return password;
  }
}

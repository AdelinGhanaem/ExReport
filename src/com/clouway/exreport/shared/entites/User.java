package com.clouway.exreport.shared.entites;


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

  public User() {
  }

  public String getUsername() {
    return username;
  }


  public String getPassword() {
    return password;
 }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

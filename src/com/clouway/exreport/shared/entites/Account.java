package com.clouway.exreport.shared.entites;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class Account implements Serializable ,IsSerializable  {


  private String email;

  private String password;

  private  String id;

  public Account() {

  }

  public Account(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public Account(String id, String username, String password) {
    this.id = id;
    email = username;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

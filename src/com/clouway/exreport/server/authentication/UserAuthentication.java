package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.entites.User;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserAuthentication {

  public Token authenticate(User user);

}

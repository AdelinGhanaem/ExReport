package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.entites.Token;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AuthenticatedUsersRepository {

  boolean isAuthorized(Token token);

  void persist(Token token);
}

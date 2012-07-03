package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.entites.Token;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AuthenticatedUsersRepository {

  String getTokenKey(Token token);

  void addToken(Token token, String accountId);

  void deleteToken(Token token);
}

package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.entites.Token;
import com.google.appengine.api.memcache.MemcacheService;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AuthenticatedUsersRepositoryImpl implements AuthenticatedUsersRepository {


  private final MemcacheService service;

  public AuthenticatedUsersRepositoryImpl(MemcacheService service) {


    this.service = service;
  }

  @Override
  public boolean isAuthorized(Token token) {
    Token returnedToke = (Token) service.get(token.getUser());
    return returnedToke != null;
  }


  @Override
  public void persist(Token token) {
    service.put(token.getUser(), token);
  }
}

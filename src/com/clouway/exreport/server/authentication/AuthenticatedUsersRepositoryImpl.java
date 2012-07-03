package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.entites.Token;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AuthenticatedUsersRepositoryImpl implements AuthenticatedUsersRepository {


  private final MemcacheService service;

  @Inject
  public AuthenticatedUsersRepositoryImpl(MemcacheService service) {
    this.service = service;
  }

  @Override
  public String getTokenKey(Token token) {
    return (String) service.get(token);
  }


  @Override
  public void addToken(Token token, String accountId) {
    service.put(token, accountId);
  }

  @Override
  public void deleteToken(Token token) {
    service.delete(token);
  }


}

package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.shared.entites.Account;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountRepositoryImpl implements AccountRepository {


  private final DatastoreService service;

  private final String accountEntityKid = "Account";

  public AccountRepositoryImpl(DatastoreService service) {


    this.service = service;
  }

  @Override
  public void persis(Account account) {
    Entity entity = new Entity(accountEntityKid);
    entity.setProperty("name", account.getEmail());
    entity.setProperty("password", account.getPassword());
    service.put(entity);
  }

  @Override
  public Account getAccountByEmail(String email) {
    return null;
  }


  @Override
  public Account getAccount(String username, String password) {
    Query query = new Query(accountEntityKid);
    query.addFilter("username", Query.FilterOperator.EQUAL, username);
    query.addFilter("password", Query.FilterOperator.EQUAL, password);
    Entity entity = service.prepare(query).asSingleEntity();
    Account account = null;
    if (entity != null && (entity.getProperty("username").equals(username) && entity.getProperty("password").equals(password))) {
      account = new Account((String) entity.getProperty("username"), (String) entity.getProperty("password"));
    }
    return account;
  }
}

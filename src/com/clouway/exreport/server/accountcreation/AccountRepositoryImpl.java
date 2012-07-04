package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.shared.entites.Account;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountRepositoryImpl implements AccountRepository {


  private final DatastoreService service;

  private final String accountEntityKid = "Account";


  @Inject
  public AccountRepositoryImpl(DatastoreService service) {
    this.service = service;
  }

  @Override
  public Account persis(Account account) {
    Entity entity = new Entity(accountEntityKid);
    entity.setProperty("username", account.getEmail());
    entity.setProperty("password", account.getPassword());
    service.put(entity);
    return new Account(KeyFactory.keyToString(entity.getKey()), account.getEmail(), account.getPassword());
  }

  @Override
  public boolean isPreviouslyRegistered(String username) {
    Query query = new Query(accountEntityKid);
    query.setFilter(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, username));
    Entity entity = service.prepare(query).asSingleEntity();
    return entity != null;
  }


  @Override
  public Account getAccount(String username, String password) {
    Query query = new Query(accountEntityKid);
    query.setFilter(Query.CompositeFilterOperator.and(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, username),
            new Query.FilterPredicate("password", Query.FilterOperator.EQUAL, password)));
    Entity entity = service.prepare(query).asSingleEntity();
    Account account = null;
    if (entity != null && (entity.getProperty("username").equals(username) && entity.getProperty("password").equals(password))) {
      account = new Account(KeyFactory.keyToString(entity.getKey()), (String) entity.getProperty("username"), (String) entity.getProperty("password"));
    }
    return account;
  }
}

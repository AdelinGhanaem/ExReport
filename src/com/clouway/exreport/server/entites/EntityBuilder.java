package com.clouway.exreport.server.entites;

import com.google.appengine.api.datastore.Entity;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface EntityBuilder<T> {


  public Entity create(T t) throws NoSuchMethodException;
}

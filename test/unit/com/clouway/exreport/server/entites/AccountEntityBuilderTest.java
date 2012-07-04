package com.clouway.exreport.server.entites;

import com.clouway.exreport.server.AppEngineTestCase;
import com.clouway.exreport.shared.entites.Account;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountEntityBuilderTest extends AppEngineTestCase {


  class AccountEntityBuilder implements EntityBuilder<Account> {
    Entity entity;

    @Override
    public Entity create(Account account) {
      Entity entity = new Entity(Account.class.getSimpleName());
      try {
        Method[] objectMethods = account.getClass().getMethods();
        for (Method method : objectMethods) {
          Pattern pattern = Pattern.compile("^get");
          if (pattern.matcher(method.getName()).find() && !"getClass".equals(method.getName())) {
            entity.setProperty(method.getName(), method.invoke(account));
          }
        }
      } catch (InvocationTargetException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      } catch (IllegalAccessException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
      return entity;
    }


//    public Query buildQuery(Account account) {
//      Query query = new Query();
//      Query.FilterPredicate
//      query.setFilter(Query.CompositeFilterOperator.and());
//      try {
//        Method[] objectMethods = account.getClass().getMethods();
//        for (Method method : objectMethods) {
//          Pattern pattern = Pattern.compile("^get");
//          if (pattern.matcher(method.getName()).find() && !"getClass".equals(method.getName())) {
//
//          }
//        }
//      } catch (InvocationTargetException e) {
//        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//      } catch (IllegalAccessException e) {
//        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//      }
//      return new Query();
//    }
  }

  public AccountEntityBuilderTest() {
    super(new LocalDatastoreServiceTestConfig());
  }


  AccountEntityBuilder builder = new AccountEntityBuilder();


  @Test
  public void returnsAccountEntityWithSpecifiedEmail() {
    Entity entity = builder.create(new Account());
    assertThat(entity, is(notNullValue()));
    assertThat(entity.getKind(), is(Account.class.getSimpleName()));
  }

  @Test
  public void returnsAccountEntityWithEmailPropertySet() throws NoSuchMethodException {

    String email = "bla bla ...";
    Account account = new Account();
    account.setEmail(email);
    Entity entity = builder.create(account);
    assertThat(entity, is(notNullValue()));
    assertThat((String) entity.getProperty(Account.class.getMethod("getEmail").getName()), is(equalTo(email)));

  }

  @Test
  public void returnsAccountEntityWithPasswordPropertySet() throws NoSuchMethodException {
    String password = "password";
    Account account = new Account();
    account.setPassword(password);
    Entity entity = builder.create(account);
    assertThat(entity, is(notNullValue()));
    assertThat((String) entity.getProperty(Account.class.getMethod("getPassword").getName()), is(equalTo(password)));
  }

  @Test
  public void doesNotSetPropertyWhenPropertyIsMissing() {
    Account account = new Account();
    Entity entity = builder.create(account);
    assertThat(entity.getProperty("email"), is(nullValue()));
  }



//
//  @Test
//  public void shouldReturnQueryThatFetchAccount() {
//
//    Account account = new Account();
//
//    Query query = builder.buildQuery(account);
//
//    assertThat(query, is(notNullValue()));
//
//
//  }
//
//  @Test
//  public void queryContainsAllAccountFieldsToReturn() {
//
//    String id = "id";
//
//    String email = "email";
//
//    String password = "password";
//
//    Account account = new Account(email, password);
//
//    Account anotherAccount = new Account();
//
//    Entity entity = builder.create(account);
//
//    Entity anotherEntity = builder.create(anotherAccount);
//
////    Query query = builder.buildQuery(account);
//
//    service.put(entity);
//    service.put(anotherEntity);
//
//
//    List<Entity> returnedEntity = Lists.newArrayList(service.prepare(query).asIterable());
//
//    assertThat(returnedEntity, is(notNullValue()));
//
//    assertThat(returnedEntity.size(), is(equalTo(1)));
//
//
//  }

  @Test
  public void returnsOnlyGetMethodsWithoutGetClassMethod() {
    Account account = new Account();
    Method[] methods = account.getClass().getMethods();
    List<Method> getMethods = new ArrayList<Method>();
    for (Method method : methods) {
      Pattern pattern = Pattern.compile("^get");
      if (pattern.matcher(method.getName()).find() && method.getTypeParameters().length == 0) {
        getMethods.add(method);
      }
    }
    assertThat(getMethods.size(), is(equalTo(4)));
  }


}

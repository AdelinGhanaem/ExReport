package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Year;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.clouway.exreport.server.expensesreporting.ExpensesRepositoryImplTest.CalendarUtil.january;
import static com.clouway.exreport.server.expensesreporting.ExpensesRepositoryImplTest.CalendarUtil.newDate;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesRepositoryImplTest {


  private LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  private DatastoreService service;

  private Entity account;

  private Key key;

  private ExpensesRepositoryImpl repository;

  private static final String EXPENSE_ENTITY_KIND = "Expense";


  @Before
  public void setUp() throws Exception {

    helper.setUp();

    service = DatastoreServiceFactory.getDatastoreService();

    account = new Entity("Account");

    service.put(account);

    key = account.getKey();

    repository = new ExpensesRepositoryImpl(service, key);

  }

  @After
  public void tearDown() throws Exception {
    helper.tearDown();
  }

  @Test
  public void expensesAreReturnedByDate() {

    Date date = newDate(2012, 2, 3);

    Entity entity = createExpenseEntity("food", 12d, date);

    service.put(entity);

    List<Expense> returnedList = repository.getExpenseByDate(date);

    assertThat(returnedList, is(notNullValue()));

    assertThat(returnedList.size(), is(equalTo(1)));

    assertThat(returnedList.get(0), is(notNullValue()));

    assertThat(returnedList.get(0).getName(), is(equalTo("food")));

    assertThat(returnedList.get(0).getPrice(), is(equalTo(12d)));

    assertThat(returnedList.get(0).getDate(), is(equalTo(date)));

  }


  @Test
  public void tryReturnAnotherExpense() {

    Date date = new Date();

    Entity entity = new Entity("Expense", key);

    entity.setProperty("name", "Dress");


    entity.setProperty("price", 20d);

    entity.setProperty("date", date);

    service.put(entity);

    List<Expense> expenseList = new ArrayList<Expense>();

    expenseList.add(new Expense("food", 20d, date));

    List<Expense> returnedList = repository.getExpenseByDate(date);

    assertThat(returnedList, is(notNullValue()));
    assertThat(returnedList.get(0), is(notNullValue()));
    assertThat(returnedList.get(0).getName(), is(equalTo("Dress")));
    assertThat(returnedList.get(0).getPrice(), is(equalTo(20d)));
    assertThat(returnedList.get(0).getDate(), is(equalTo(date)));
  }


  @Test
  public void returnsEmptyListWhenNoExpenseWithTheProvidedDate() {

    Date date = new Date();

    List<Expense> expenseList = new ArrayList<Expense>();

    expenseList.add(new Expense("food", 20d, date));

    List<Expense> returnedList = repository.getExpenseByDate(date);

    assertThat(returnedList, is(notNullValue()));
    assertThat(returnedList.size(), is(equalTo(0)));
  }


  @Test
  public void returnsExpenseByName() {

    Date date = new Date();

    Entity entity = new Entity("Expense", key);

    entity.setProperty("name", "Pens");
    entity.setProperty("price", 12d);
    entity.setProperty("date", date);

    service.put(entity);

    List<Expense> expenseList = new ArrayList<Expense>();

    expenseList.add(new Expense("Pens", 20d, date));

    List<Expense> returnedList = repository.getExpenseByName("Pens");

    assertThat(returnedList, is(notNullValue()));

    assertThat(returnedList.size(), is(equalTo(1)));

    assertThat(returnedList.get(0), is(notNullValue()));

    assertThat(returnedList.get(0).getName(), is(equalTo("Pens")));

  }


  @Test
  public void tryReturnAnotherExpenseByName() {
    //TODO:don't forget to implement it  ... !
  }


  @Test
  public void returnsExpensesBetweenTwoDate() throws Exception {

    addExpenseOn(january(2012, 2),key);

    addExpenseOn(january(2012, 4),key);

    addExpenseOn(january(2012, 6),key);

    List<Expense> expenses = repository.getExpensesBetween(january(2012, 2), january(2012, 5));

    assertThat(expenses, is(notNullValue()));

    assertThat(expenses.size(), is(equalTo(2)));
  }


  @Test
  public void returnsEmptyListWhenNoExpensesDeclaredForCurrentUserKey() {

    Entity entity = new Entity(EntityKind.ACCOUNT);

    service.put(entity);

    Key key1 = entity.getKey();

    addExpenseOn(january(2012,1),key1);

    List<Expense> expenses = repository.getExpensesBetween(january(2001, 1), january(2012, 3));

    assertThat(expenses.size(), is(0));

  }


  @Test
  public void addsExpensesInDatastore() {

    Expense expense = new Expense("computer", 1000d, newDate(2012, 3, 12));

    repository.saveExpense(expense);

    Query query = new Query(EXPENSE_ENTITY_KIND);

    query.addFilter("name", Query.FilterOperator.EQUAL, "computer");

    List<Entity> returnedEntities = Lists.newArrayList(service.prepare(query).asIterable());

    assertThat(returnedEntities, is(notNullValue()));

    assertThat(returnedEntities.size(), is(equalTo(1)));

    assertThat((String) returnedEntities.get(0).getProperty("name"), is(equalTo("computer")));

  }


  @Test
  public void savesTheYearOfExpenseWhenExpensesIsSaved() {
//
//    int year = 2012;
//
//    Expense expense = new Expense("keyboard", 3000d, newDate(year, 3, 12));
//
//    repository.saveExpense(expense);
//
//    Query query = new Query("Year");
//
//    query.setAncestor(key);
//
//    query.addFilter("year", Query.FilterOperator.EQUAL, "2012");
//
//    Entity returnedYear = service.prepare(query).asSingleEntity();
//
//    assertThat(returnedYear, is(notNullValue()));
//
//    Integer integer = Integer.valueOf((String) returnedYear.getProperty("year"));
//
//    assertThat(integer, is(equalTo(year)));

  }


  private void addExpenseOn(Date firstDate,Key key) {

    Entity entity = new Entity(EXPENSE_ENTITY_KIND, key);
    entity.setProperty("name", "fuel");
    entity.setProperty("price", 30d);
    entity.setProperty("date", firstDate);
    service.put(entity);

  }

  private Entity createExpenseEntity(String name, Double price, Date date) {
    Entity entity = new Entity(EXPENSE_ENTITY_KIND, key);
    entity.setProperty("name", name);
    entity.setProperty("price", price);
    entity.setProperty("date", date);
    return entity;
  }


  public static final class CalendarUtil {

    @SuppressWarnings("unused")
    CalendarUtil() {
    }

    public static Date january(int year, int day) {
      return newDate(year, 1, day);
    }

    public static Date february(int year, int day) {
      return newDate(year, 2, day);
    }


    public static Date newDate(int year, int month, int day) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.YEAR, year);
      calendar.set(Calendar.MONTH, month - 1);
      calendar.set(Calendar.DAY_OF_MONTH, day);
      calendar.set(Calendar.HOUR, 1);
      calendar.set(Calendar.MINUTE, 1);
      calendar.set(Calendar.SECOND, 1);
      clearNonDateFields(calendar);
      return calendar.getTime();

    }

    private static Calendar clearNonDateFields(Calendar c) {
      c.clear(Calendar.HOUR);
      c.clear(Calendar.MINUTE);
      c.clear(Calendar.SECOND);
      c.clear(Calendar.MILLISECOND);
      return c;
    }
  }

  @Test
  public void yearsTest() throws Exception {
    Calendar calendar = Calendar.getInstance();
    Date date = new Date();
    calendar.setTime(date);
    int year = calendar.get(Calendar.YEAR);
    assertThat(year, is(equalTo(2012)));
  }


  @Test
  public void returnsAllYearsOfDeclaredExpenses() {
    for (int i = 0; i < 10; i++) {
      Entity entity = new Entity(EntityKind.YEAR, key);
      entity.setProperty("year", 2000 + i);
      System.out.println(2000 + i);
      service.put(entity);
    }
    List<Year> returnedYears = repository.getYears();
    assertThat(returnedYears, is(notNullValue()));
    assertThat(returnedYears.size(), is(equalTo(10)));
  }
  //TODO:what happens when any of the properties of the expense entity does does not exists (NullPointerException).
}

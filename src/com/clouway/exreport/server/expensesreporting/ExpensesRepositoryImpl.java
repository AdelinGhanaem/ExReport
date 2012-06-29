package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Year;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesRepositoryImpl implements ExpensesRepository {


  private final DatastoreService service;

  private final Key key;

  private final String expenseEntityKind = "Expense";
  private final String yearEntityKind = "Year";

  public ExpensesRepositoryImpl(DatastoreService service, Key key) {
    this.service = service;
    this.key = key;
  }

  @Override
  public void saveExpense(Expense expense) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(expense.getDate());
    int yearInteger = calendar.get(Calendar.YEAR);

    Query query = new Query(EntityKind.YEAR);

    Entity entity = service.prepare(query.setFilter(new Query.FilterPredicate("year", Query.FilterOperator.EQUAL, yearInteger))).asSingleEntity();

    if (entity == null) {
      Entity year = new Entity(yearEntityKind, key);
      year.setProperty("year", yearInteger);
      service.put(year);
    }


    Entity entityEntity = new Entity(expenseEntityKind, key);

    entityEntity.setProperty("name", expense.getName());

    entityEntity.setProperty("price", expense.getPrice());

    entityEntity.setProperty("date", expense.getDate());

    service.put(entityEntity);
  }

  @Override
  public List<Expense> getExpenseByDate(final Date date) {

    Query query = new Query(expenseEntityKind, key);

    query.addFilter("date", Query.FilterOperator.EQUAL, date);

    return getReturnedExpenses(service.prepare(query).asIterable());

  }


  @Override
  public List<Expense> getExpenseByName(final String expenseName) {

    Query query = new Query("Expense", key);

    query.addFilter("name", Query.FilterOperator.EQUAL, expenseName);

    Iterable<Entity> entities = service.prepare(query).asIterable();

    return getReturnedExpenses(entities);

  }

  @Override
  public List<Expense> getExpensesBetween(Date firstDate, Date secondDate) {

    Query query = new Query(expenseEntityKind);

    query.setFilter(Query.CompositeFilterOperator.and(new Query.FilterPredicate("date",
            Query.FilterOperator.GREATER_THAN_OR_EQUAL, firstDate), new Query.FilterPredicate("date",
            Query.FilterOperator.LESS_THAN_OR_EQUAL, secondDate)));

    Iterable<Entity> entities = service.prepare(query).asIterable();

    return getReturnedExpenses(entities);

  }

  @Override
  public List<Year> getYears() {
    Query query = new Query("Year");
    query.addSort("year");
    Iterable<Entity> entities = service.prepare(query).asIterable();
    List<Year> years = new ArrayList<Year>();
    for (Entity entity : entities) {
      years.add(new Year(Integer.valueOf(String.valueOf(entity.getProperty("year")))));
    }
    return years;
  }


  private List<Expense> getReturnedExpenses(Iterable<Entity> entities) {
    List<Expense> expenses = new ArrayList<Expense>();
    for (Entity entity : entities) {
      expenses.add(new Expense((String) entity.getProperty("name"),
              (Double) entity.getProperty("price"), (Date) entity.getProperty("date")));
    }
    return expenses;
  }

}

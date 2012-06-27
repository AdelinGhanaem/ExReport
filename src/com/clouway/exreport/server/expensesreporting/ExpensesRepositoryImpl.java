package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.entites.Expense;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesRepositoryImpl implements ExpensesRepository {


  private final DatastoreService service;

  private final Key key;

  private final String expenseEntityKind = "Expense";

  public ExpensesRepositoryImpl(DatastoreService service, Key key) {
    this.service = service;
    this.key = key;
  }

  @Override
  public void saveExpense(Expense expense) {
    Entity entity = new Entity(expenseEntityKind, key);
    entity.setProperty("name", expense.getName());
    entity.setProperty("price", expense.getPrice());
    entity.setProperty("date", expense.getDate());
    service.put(entity);
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

    query.addFilter("date", Query.FilterOperator.GREATER_THAN_OR_EQUAL, firstDate);

    query.addFilter("date", Query.FilterOperator.LESS_THAN_OR_EQUAL, secondDate);

    Iterable<Entity> entities = service.prepare(query).asIterable();

    return getReturnedExpenses(entities);

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

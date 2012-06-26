package com.clouway.exreport.server.expensesreporting;

import com.clouway.exreport.shared.entites.Expense;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesRepositoryImplTest {

 
  private ExpensesRepository repository = new ExpensesRepositoryImpl();

  private LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

//  private Entity Account = new Entity("Account",);

  private DatastoreService service;


  @Before
  public void setUp() throws Exception {

    helper.setUp();
    service = DatastoreServiceFactory.getDatastoreService();

  }

  @After
  public void tearDown() throws Exception {
    helper.tearDown();
  }

  @Test
  public void expensesAreReturnedByDate() {

    List<Expense> expenseList = new ArrayList<Expense>();

    Date date = new Date();

    for (int i = 0; i < 10; i++) {
      expenseList.add(new Expense("name:" + 1, (double) i, date));
    }
    
//    List<Expense> returnedExpenses =


  }
}

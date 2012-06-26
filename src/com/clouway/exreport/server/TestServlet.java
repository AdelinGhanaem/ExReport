package com.clouway.exreport.server;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@Singleton
public class TestServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    DatastoreService dss = DatastoreServiceFactory.getDatastoreService();

    Entity expense = new Entity("Expense");

    expense.setUnindexedProperty("name", "pens");

    expense.setUnindexedProperty("amount", 20d);

    expense.setUnindexedProperty("owner", "mgenov@gmail.com");

    dss.put(expense);

  }
}

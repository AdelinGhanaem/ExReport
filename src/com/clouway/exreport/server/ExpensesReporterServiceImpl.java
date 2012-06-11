package com.clouway.exreport.server;

import com.clouway.exreport.client.expensesdashboard.ExpenseReporterService;
import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Adio
 * Date: 6/10/12
 * Time: 6:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExpensesReporterServiceImpl extends RemoteServiceServlet implements ExpenseReporterService {
  @Override
  public ArrayList<Expense> getExpensesFor(Date date) {
    ArrayList<Expense> expenses = new ArrayList<Expense>();
    expenses.add(new Expense("Food", 12));
    expenses.add(new Expense("Diskoteka", 12));
    expenses.add(new Expense("Fitness", 12d));
    return expenses;
  }

  @Override
  public ArrayList<Expense> getExpensesBetween(Date firstDate, Date secondDate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public ArrayList<Year> getYearsOfExpenses() {
    ArrayList<Year> years = new ArrayList<Year>();
    years.add(new Year(2009));
    years.add(new Year(2008));
    years.add(new Year(2010));
    years.add(new Year(2032));
    years.add(new Year(2022));
    return years;
  }

  @Override
  public ArrayList<Month> getMonthOf(int year) {
    ArrayList<Month> months = new ArrayList<Month>();
    months.add(new Month(year, 9));
    months.add(new Month(year, 2));
    months.add(new Month(year, 12));
    months.add(new Month(year, 11));
    months.add(new Month(year, 3));
    return months;
  }

  @Override
  public ArrayList<Day> getDaysOf(int year, int month) {
    ArrayList<Day> days = new ArrayList<Day>();
    days.add(new Day(2, month, year));
    days.add(new Day(23, month, year));
    days.add(new Day(4, month, year));
    return days;
  }
}

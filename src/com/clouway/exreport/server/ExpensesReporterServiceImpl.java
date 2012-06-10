package com.clouway.exreport.server;

import com.clouway.exreport.client.expensesdashboard.ExpenseReporterService;
import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Adio
 * Date: 6/10/12
 * Time: 6:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExpensesReporterServiceImpl extends RemoteServiceServlet implements ExpenseReporterService {
    @Override
    public List<Expense> getExpensesFor(Date date) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Expense> getExpensesBetween(Date firstDate, Date secondDate) {
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ArrayList<Day> getDaysOf(int year, int month) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

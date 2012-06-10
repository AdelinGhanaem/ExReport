package com.clouway.exreport.client.expensesdashboard;

import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Year;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

import java.util.ArrayList;

import static org.mockito.Mockito.doAnswer;

/**
 * Created with IntelliJ IDEA.
 * User: Adio
 * Date: 6/10/12
 * Time: 2:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestingAsyncCallbacksHelper {




    static <T> Stubber doOnSuccess(final ArrayList<T> returnedExpenses) {
        return doAnswer(new Answer<ArrayList<Expense>>() {
            @Override
            public ArrayList<Expense> answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();

                AsyncCallback<ArrayList<T>> callback = (AsyncCallback<ArrayList<T>>) args[args.length - 1];

                callback.onSuccess(returnedExpenses);

                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    static <T>Stubber doOnFailure(final Throwable throwable) {
        return doAnswer(new Answer<Throwable>() {
            @Override
            public Throwable answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();

                AsyncCallback<ArrayList<T>> callback = (AsyncCallback<ArrayList<T>>) args[args.length - 1];

                callback.onFailure(throwable);

                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }




}

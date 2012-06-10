package com.clouway.exreport.shared;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Adio
 * Date: 6/10/12
 * Time: 2:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Month  implements Serializable{
    private int monthNumber;

    public Month() {
    }

    public Month(int monthNumber) {

        this.monthNumber = monthNumber;
    }


    public int getMonthNumber() {
        return monthNumber;
    }
}

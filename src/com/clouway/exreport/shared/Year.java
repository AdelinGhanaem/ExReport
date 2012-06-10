package com.clouway.exreport.shared;

import java.io.Serializable;

/**
* Created with IntelliJ IDEA.
* User: Adio
* Date: 6/10/12
* Time: 1:51 AM
* To change this template use File | Settings | File Templates.
*/
public  class Year implements Serializable {
    private int year;

    public Year() {
    }

    public Year(int year) {

        this.year = year;
    }

    public int getYear() {
        return year;
    }
}

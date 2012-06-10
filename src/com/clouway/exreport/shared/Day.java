package com.clouway.exreport.shared;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Adio
 * Date: 6/10/12
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class Day implements Serializable{
    private String day;

    public Day() {
    }

    public Day(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}

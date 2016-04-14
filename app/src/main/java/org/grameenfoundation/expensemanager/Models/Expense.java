package org.grameenfoundation.expensemanager.Models;

/**
 * Created by henry on 3/18/16.
 */
public class Expense {

    private String label;
    private Integer cost;
    private String date;

    public static final String LABEL = "Item";
    public static final String COST = "Cost";
    public static final String DATE = "Date";

    public Expense() {

    }

    public Expense(String label, Integer cost, String date) {
        this.label = label;
        this.cost = cost;
        this.date = date;
    }

    public Expense(String label, Integer cost) {
        this.label = label;
        this.cost = cost;
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {

    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

    }

}

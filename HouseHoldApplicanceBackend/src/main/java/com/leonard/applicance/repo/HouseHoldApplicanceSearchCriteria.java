/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leonard.applicance.repo;

public class HouseHoldApplicanceSearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    public HouseHoldApplicanceSearchCriteria() {
    }

    public HouseHoldApplicanceSearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    // getters and setters, equals(), toString(), ... (omitted for brevity)

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SearchOperation getOperation() {
        return operation;
    }

    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", operation=" + operation +
                '}';
    }
}

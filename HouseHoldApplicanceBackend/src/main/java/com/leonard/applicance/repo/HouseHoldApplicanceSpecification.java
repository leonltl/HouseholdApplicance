/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leonard.applicance.repo;

import com.leonard.applicance.domain.HouseHoldApplicanceEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class HouseHoldApplicanceSpecification implements Specification<HouseHoldApplicanceEntity> {
    
    private List<HouseHoldApplicanceSearchCriteria> list;
    
    public HouseHoldApplicanceSpecification() {
        this.list = new ArrayList<>();
    }
    
    public void add(HouseHoldApplicanceSearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<HouseHoldApplicanceEntity> root, CriteriaQuery<?> cq, CriteriaBuilder builder) {
        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (HouseHoldApplicanceSearchCriteria criteria : list) {
            
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                if (criteria.getValue() instanceof Date) {
                    Path<Date> datePath = root.get(criteria.getKey());
                    predicates.add(builder.greaterThan(datePath, (Date)criteria.getValue()));
                }
                else if (criteria.getValue() instanceof Long) {
                    Path<Long> longPath = root.get(criteria.getKey());
                    predicates.add(builder.greaterThan(longPath, (Long)criteria.getValue()));
                }
                else if (criteria.getValue() instanceof Integer) {
                    Path<Integer> intPath = root.get(criteria.getKey());
                    predicates.add(builder.greaterThan(intPath, (Integer)criteria.getValue()));
                }
            } 
            else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                if (criteria.getValue() instanceof Date) {
                    Path<Date> datePath = root.get(criteria.getKey());
                    predicates.add(builder.lessThan(datePath, (Date)criteria.getValue()));
                }
                else if (criteria.getValue() instanceof Long) {
                    Path<Long> longPath = root.get(criteria.getKey());
                    predicates.add(builder.lessThan(longPath, (Long)criteria.getValue()));
                }
                else if (criteria.getValue() instanceof Integer) {
                    Path<Integer> intPath = root.get(criteria.getKey());
                    predicates.add(builder.lessThan(intPath, (Integer)criteria.getValue()));
                }
            } 
            else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                if (criteria.getValue() instanceof Date) {
                    Path<Date> datePath = root.get(criteria.getKey());
                    predicates.add(builder.greaterThanOrEqualTo(datePath, (Date)criteria.getValue()));
                }
                else if (criteria.getValue() instanceof Long) {
                    Path<Long> longPath = root.get(criteria.getKey());
                    predicates.add(builder.greaterThanOrEqualTo(longPath, (Long)criteria.getValue()));
                }
                else if (criteria.getValue() instanceof Integer) {
                    Path<Integer> intPath = root.get(criteria.getKey());
                    predicates.add(builder.greaterThanOrEqualTo(intPath, (Integer)criteria.getValue()));
                }
            } 
            else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                if (criteria.getValue() instanceof Date) {
                    Path<Date> datePath = root.get(criteria.getKey());
                    predicates.add(builder.lessThanOrEqualTo(datePath, (Date)criteria.getValue()));
                }
                else if (criteria.getValue() instanceof Long) {
                    Path<Long> longPath = root.get(criteria.getKey());
                    predicates.add(builder.lessThanOrEqualTo(longPath, (Long)criteria.getValue()));
                }
                else if (criteria.getValue() instanceof Integer) {
                    Path<Integer> intPath = root.get(criteria.getKey());
                    predicates.add(builder.lessThanOrEqualTo(intPath, (Integer)criteria.getValue()));
                }
            } 
            else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
            } 
            else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            } 
            else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } 
            else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } 
            else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } 
            else if (criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
            } 
            else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    
   
}
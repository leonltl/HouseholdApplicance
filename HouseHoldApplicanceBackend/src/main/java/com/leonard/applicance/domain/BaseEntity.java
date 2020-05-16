/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leonard.applicance.domain;

import java.util.Calendar;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Setter;

/*
    hibernate super base entity class 
*/
@Setter
@MappedSuperclass
public abstract class BaseEntity  {
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updatedAt;
           
    public BaseEntity() {
        createdAt = Calendar.getInstance();
        updatedAt = Calendar.getInstance();
    }
    
    public Calendar getCreatedAt() {
        if (createdAt == null) {
            createdAt = Calendar.getInstance();
        }
        
        return updatedAt;
    }
    
    public Calendar getUpdatedAt() {
        if (updatedAt == null) {
            updatedAt = Calendar.getInstance();
        }
        
        return updatedAt;
    }
    
}


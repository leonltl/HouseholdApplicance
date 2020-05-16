/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leonard.applicance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author DELL
 */

@Getter
@Setter
@Entity
@Table(name = "Household_Applicance")
public class HouseHoldApplicanceEntity extends BaseEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNum; 
    private String brand;
    private String model;
    private String status;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = JsonFormat.DEFAULT_LOCALE)
    private Date dateBought;
    
    public HouseHoldApplicanceEntity() {
        super();
    }
    
    public HouseHoldApplicanceEntity(String serialNum, String brand, String model, Date dateBought, String status) {        
        super();
        
        this.serialNum = serialNum;
        this.brand = brand;
        this.model = model;
        this.dateBought = dateBought;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HouseHoldApplicanceEntity entity = (HouseHoldApplicanceEntity) o;

        if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
        if (brand != null ? !brand.equals(entity.brand) : entity.brand != null) return false;
        return model != null ? model.equals(entity.model) : entity.model == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }
    
}

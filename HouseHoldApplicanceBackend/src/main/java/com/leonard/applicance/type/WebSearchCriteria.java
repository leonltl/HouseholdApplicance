/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leonard.applicance.type;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class WebSearchCriteria {
    
    String serialNum;
    String brand;
    String model;
    String status;
    String dateBought;

    public WebSearchCriteria() {   
        this.serialNum = "";
        this.brand = "";
        this.model = "";
        this.status = "";
        this.dateBought = "";
    }
    
    public WebSearchCriteria(String serialNum, String brand, String model, String status, String dateBought) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.dateBought = dateBought;
    }
}

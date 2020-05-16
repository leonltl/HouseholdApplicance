/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leonard.applicance.type;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.leonard.applicance.domain.HouseHoldApplicanceEntity;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class WebHouseHoldApplicance {
    
    private Long id;
    private String serialNum; 
    private String brand;
    private String model;
    private String dateBought;
    private String status;
  
    
    public WebHouseHoldApplicance() {
        this.id = 0L;
        this.serialNum = "";
        this.brand = "";
        this.model = "";
        this.status = "";
        
    }
   
    public WebHouseHoldApplicance(Long id, String serialNum, String brand, String model, String dateBought, String status) {
        this.id = id;
        this.serialNum = serialNum;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.dateBought = dateBought;
    }
    
    public WebHouseHoldApplicance(HouseHoldApplicanceEntity houseHoldApplicance) {
        this.id = houseHoldApplicance.getId();
        this.serialNum = houseHoldApplicance.getSerialNum();
        this.brand = houseHoldApplicance.getBrand();
        this.model = houseHoldApplicance.getModel();
        this.status = houseHoldApplicance.getStatus();
        
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(houseHoldApplicance.getDateBought());
        this.dateBought = date;
    }
}

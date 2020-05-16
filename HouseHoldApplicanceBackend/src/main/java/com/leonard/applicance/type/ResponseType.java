/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leonard.applicance.type;

public class ResponseType {
    private String isSuccess;
 
    public ResponseType(Boolean isSuccess){
        
        if (isSuccess) {
            this.isSuccess = "true";
        }
        else {
            this.isSuccess = "false";
        }
    }
 
    public String getIsSuccess() {
        return isSuccess;
    }
}

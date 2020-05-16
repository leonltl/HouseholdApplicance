
package com.leonard.applicance.controller;

import com.leonard.applicance.domain.HouseHoldApplicanceEntity;
import com.leonard.applicance.repo.HouseHoldApplicanceRepository;
import com.leonard.applicance.repo.HouseHoldApplicanceSearchCriteria;
import com.leonard.applicance.repo.HouseHoldApplicanceSpecification;
import com.leonard.applicance.repo.SearchOperation;
import com.leonard.applicance.type.ErrorType;
import com.leonard.applicance.type.ResponseType;
import com.leonard.applicance.type.WebHouseHoldApplicance;
import com.leonard.applicance.type.WebSearchCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@Api(value="Household aplicance", description="Operations pertaining to house hold applicance entry", produces ="application/json")
public class HouseHoldApplicanceController {
 
    @Autowired
    private HouseHoldApplicanceRepository repository;
    
    @Autowired
    public void setBlogEntryService(HouseHoldApplicanceRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/applicances", method = RequestMethod.GET)
    @ApiOperation(value = "Get a list of all household applicances entries", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
    })
    public ResponseEntity<List<HouseHoldApplicanceEntity>> getAllEntries() {
        List<HouseHoldApplicanceEntity> entries = this.repository.findAll();
        return new ResponseEntity(entries, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/applicance/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get a household applicance entry based on the id", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved an entry"),
        @ApiResponse(code = 404, message = "An invalid household applicance entry")
    })
    public ResponseEntity<HouseHoldApplicanceEntity> getEntry(@PathVariable("id") long id) {

        Optional<HouseHoldApplicanceEntity> record = this.repository.findById(id);
        
        if (!record.isPresent()) {
            return new ResponseEntity(new ErrorType("Entry with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        
        HouseHoldApplicanceEntity entry = record.get();
        return new ResponseEntity(entry, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/addApplicance", method = RequestMethod.POST)
    @ApiOperation(value = "Add a household applicance entry", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully added the entry"),
        @ApiResponse(code = 404, message = "A duplicated entry found with a existing serial number, brand and model")
    })
    public ResponseEntity<?> addEntry(@RequestBody WebHouseHoldApplicance webEntry, UriComponentsBuilder ucBuilder) { 
        
        Optional<HouseHoldApplicanceEntity> record = this.repository.findBySerialNumAndBrandAndModel(webEntry.getSerialNum(), webEntry.getBrand(), webEntry.getModel());
        
        if (record.isPresent()) {
            return new ResponseEntity(new ErrorType("Unable to add new applicance. Entry with " + webEntry.getSerialNum() + "," + webEntry.getBrand() + "," + webEntry.getModel() + " is found."), HttpStatus.NOT_FOUND);
        }
        
        HouseHoldApplicanceEntity entry = new HouseHoldApplicanceEntity();
        entry.setBrand(webEntry.getBrand());
        entry.setModel(webEntry.getModel());
        entry.setSerialNum(webEntry.getSerialNum());
        entry.setStatus(webEntry.getStatus());
          
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(webEntry.getDateBought());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY, 12);
            entry.setDateBought(calendar.getTime());
            
        } 
        catch (ParseException ex) {
            Logger.getLogger(HouseHoldApplicanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.repository.save(entry);
        return new ResponseEntity(new ResponseType(true), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/updateApplicance/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update a household applicance entry based on the id", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully update the entry"),
    })
    public ResponseEntity<?> updateEntry(@PathVariable("id") long id, @RequestBody WebHouseHoldApplicance webEntry) {
        
        Optional<HouseHoldApplicanceEntity> record = this.repository.findById(id);
        if (!record.isPresent()) {
            return new ResponseEntity(new ErrorType("Unable to update. Entry with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
 
        HouseHoldApplicanceEntity currentEntry = record.get();
 
        currentEntry.setBrand(webEntry.getBrand());
        currentEntry.setModel(webEntry.getModel());
        currentEntry.setSerialNum(webEntry.getSerialNum());
        currentEntry.setStatus(webEntry.getStatus());
        
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(webEntry.getDateBought());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY, 12);
            currentEntry.setDateBought(calendar.getTime());
        } 
        catch (ParseException ex) {
            Logger.getLogger(HouseHoldApplicanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        this.repository.save(currentEntry);
        return new ResponseEntity(new ResponseType(true), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deleteApplicance/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a household applicance entry based on the id", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully delete the entry"),
    })
    public ResponseEntity<?> deleteEntry(@PathVariable("id") long id) {
   
        Optional<HouseHoldApplicanceEntity> record = this.repository.findById(id);
        if (!record.isPresent()) {
            return new ResponseEntity(new ErrorType("Unable to delete. Entry with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        
        HouseHoldApplicanceEntity currentEntry = record.get();
        
        this.repository.delete(currentEntry);
        return new ResponseEntity(new ResponseType(true), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/searchApplicance", method = RequestMethod.POST)
    @ApiOperation(value = "Search a household applicance entry based on the criteria", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of applicances"),
    })
    public ResponseEntity<List<HouseHoldApplicanceEntity>> searchForApplicances(@RequestBody WebSearchCriteria criteria) {
       
        List<HouseHoldApplicanceEntity> entries = new ArrayList();
        HouseHoldApplicanceSpecification searchSpecs = new HouseHoldApplicanceSpecification();
        if (!criteria.getSerialNum().isEmpty()) {
            HouseHoldApplicanceSearchCriteria search = new HouseHoldApplicanceSearchCriteria("serialNum", criteria.getSerialNum(), SearchOperation.EQUAL);
            searchSpecs.add(search);
        }
        
        if (!criteria.getBrand().isEmpty()) {
            HouseHoldApplicanceSearchCriteria search = new HouseHoldApplicanceSearchCriteria("brand", criteria.getBrand(), SearchOperation.EQUAL);
            searchSpecs.add(search);
        }
        
        if (!criteria.getModel().isEmpty()) {
            HouseHoldApplicanceSearchCriteria search = new HouseHoldApplicanceSearchCriteria("model", criteria.getModel(), SearchOperation.EQUAL);
            searchSpecs.add(search);
        }
        
        if (!criteria.getStatus().isEmpty()) {
            HouseHoldApplicanceSearchCriteria search = new HouseHoldApplicanceSearchCriteria("status", criteria.getStatus(), SearchOperation.EQUAL);
            searchSpecs.add(search);
        }
        
        if (!criteria.getDateBought().isEmpty()) {
            try {
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(criteria.getDateBought());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.HOUR_OF_DAY, 12);
                
                HouseHoldApplicanceSearchCriteria search = new HouseHoldApplicanceSearchCriteria("dateBought", calendar.getTime(), SearchOperation.EQUAL);
                searchSpecs.add(search);
            } 
            catch (ParseException ex) {
                Logger.getLogger(HouseHoldApplicanceController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
        entries = this.repository.findAll(searchSpecs);
        return new ResponseEntity(entries, HttpStatus.OK);
    }
}


package com.leonard.applicance.repo;

import com.leonard.applicance.repo.HouseHoldApplicanceRepository;
import com.leonard.applicance.config.RepositoryConfiguration;
import com.leonard.applicance.controller.HouseHoldApplicanceController;
import com.leonard.applicance.domain.HouseHoldApplicanceEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class HouseHoldApplicanceRepositoryTest {
    
    private HouseHoldApplicanceRepository repository;
    
    @Autowired
    public void setPostRepository(HouseHoldApplicanceRepository repository) {
        this.repository = repository;
    }
    
    @Test
    public void testSaveEntries() {
        {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, 12);
            HouseHoldApplicanceEntity entry = new HouseHoldApplicanceEntity("1234567654", "Samsung", "S10", calendar.getTime(), "In used");
            
            entry = repository.save(entry);
            assertNotNull(entry.getId()); 
        }
        
        {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, 12);
            HouseHoldApplicanceEntity entry = new HouseHoldApplicanceEntity("24323SF5DW", "Huawei", "P20", calendar.getTime(), "Sold");
            entry = repository.save(entry);
            assertNotNull(entry.getId()); 
        }
        
    }
    
    @Test
    public void testSearchCriteria() {
        {
            HouseHoldApplicanceSpecification searchSpecs = new HouseHoldApplicanceSpecification();
            searchSpecs.add(new HouseHoldApplicanceSearchCriteria("serialNum", "1234567654", SearchOperation.EQUAL));
            searchSpecs.add(new HouseHoldApplicanceSearchCriteria("brand", "Samsung", SearchOperation.EQUAL));
            searchSpecs.add(new HouseHoldApplicanceSearchCriteria("model", "S10", SearchOperation.EQUAL));
            searchSpecs.add(new HouseHoldApplicanceSearchCriteria("status", "In used", SearchOperation.EQUAL));
            
            List<HouseHoldApplicanceEntity> entries = this.repository.findAll(searchSpecs);
            assertTrue(!entries.isEmpty());
        }   
    }
    
    @Test
    public void testDeleteEntry() {
        {
            List<HouseHoldApplicanceEntity> entries = this.repository.findBySerialNum("1234567654");

            if (entries.size() > 0) {
                for (HouseHoldApplicanceEntity entry : entries) {
                    repository.delete(entry);
                }
                assertTrue(true);
            }
        }
        
        {
            List<HouseHoldApplicanceEntity> entries = this.repository.findBySerialNum("24323SF5DW");
            if (entries.size() > 0) {
                for (HouseHoldApplicanceEntity entry : entries) {
                    repository.deleteById(entry.getId());
                }
                assertTrue(true);
            }
        }
    }
    
    
}

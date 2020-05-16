/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leonard.applicance.repo;

import com.leonard.applicance.domain.HouseHoldApplicanceEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseHoldApplicanceRepository extends JpaRepository<HouseHoldApplicanceEntity, Long>, JpaSpecificationExecutor<HouseHoldApplicanceEntity> {

    Optional<HouseHoldApplicanceEntity> findById(String id);
    List<HouseHoldApplicanceEntity> findBySerialNum(String serialNum);
    Optional<HouseHoldApplicanceEntity> findBySerialNumAndBrandAndModel(String serialNum, String brand, String model);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.CompPoiCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mekko
 */
public interface CompPoiCategoryRepository extends JpaRepository<CompPoiCategory, Integer>{
    
}

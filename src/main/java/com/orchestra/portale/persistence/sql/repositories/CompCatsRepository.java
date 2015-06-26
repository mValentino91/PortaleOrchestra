/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.CompCats;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Alex
 */
public interface CompCatsRepository extends JpaRepository<CompCats, Integer>{ 
    @Query("select c.component, c.description from CompCats c where c.category = ?1")
    ArrayList<Object[]> getComponentsByCategory(String category);
}

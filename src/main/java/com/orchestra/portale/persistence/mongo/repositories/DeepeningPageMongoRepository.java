/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.repositories;

import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import com.orchestra.portale.persistence.mongo.documents.Home;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Alex
 */
public interface DeepeningPageMongoRepository extends PagingAndSortingRepository<DeepeningPage, String> {
    
}

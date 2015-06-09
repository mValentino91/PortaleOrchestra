/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.repositories;

import com.orchestra.portale.persistence.mongo.documents.Pages_En;


import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Alex
 */
public interface PagesMongoRepository_En extends PagingAndSortingRepository<Pages_En, String> {

    
}

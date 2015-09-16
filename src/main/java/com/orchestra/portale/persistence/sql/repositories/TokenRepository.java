/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.Token;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author antonio
 */
public interface TokenRepository extends JpaRepository<Token, Integer>{

    public Token findByToken(String token);

    public Token findByIdUser(Integer id_user);

    @Modifying
    @Transactional(readOnly=false)
    @Query("update Token t set t.token=?2, t.validity=?3 where t.id=?1")    
    public void updateToken(Integer id, String token, Date validity);
    
}

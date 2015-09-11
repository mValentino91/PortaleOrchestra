/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author andrea
 */
public interface DealerOfferRepository extends JpaRepository<DealerOffer, Integer>{
    List<DealerOffer> findOfferByIdPoi(String idPoi);
    DealerOffer findOfferByIdOffer(int idOffer);
    List<DealerOffer> findOfferByIdDealer(String idDealer);

    @Query("SELECT count(*) FROM DealerOffer d WHERE d.idDealer =?1")
    int countOfferByIdPoi(String idPoi);

    DealerOffer findByidOffer(Integer idOffer);

}

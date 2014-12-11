/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import java.util.ArrayList;
import org.springframework.data.annotation.TypeAlias;

/**
 *
 * @author Alex
 */
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.PricesComponent")
public class PricesComponent extends AbstractPoiComponent{
    private ArrayList<TicketPrice> prices;
    

    public void setPrices(ArrayList<TicketPrice> prices) {
        this.prices = prices;
    }


    public ArrayList<TicketPrice> getPrices() {
        return prices;
    }

   
}

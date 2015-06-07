/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import org.springframework.data.annotation.TypeAlias;

/**
 *
 * @author Alex
 */
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.TopTenComponent")
public class TopTenComponent extends AbstractPoiComponent {

    private String table;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

}

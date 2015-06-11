/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orchestra.portale.external.services.manager;

import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marco Valentino
 */
public interface ExternalServiceManager {
    
    public String load(HttpSession session);
    public String getResponse(Map<String,String[]> mapParams);
    
}

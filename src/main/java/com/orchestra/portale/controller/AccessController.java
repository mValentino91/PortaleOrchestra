package com.orchestra.portale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AccessController {

	@RequestMapping("/login")
	public String login() {
		return "access/loginForm";
	}        
           
    
	@RequestMapping("/loginForm")
	public String loginForm(Model model) {
                return "access/loginForm";
	}        

	@RequestMapping(value="/loginForm", params="error")
	public String loginForm(Model model, @RequestParam(value="error") String error) {
                model.addAttribute("error", "Username o password errati");
		return "access/loginForm";
	}                
             
        
	@RequestMapping("/denied")
 	public String denied() {
		return "access/denied";
	}
	
	@RequestMapping("/loginArea")
 	public String loginArea() {
		return "access/loginArea";
	}
}
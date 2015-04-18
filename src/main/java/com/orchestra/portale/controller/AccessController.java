package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.User;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class AccessController {

    @Autowired
    PersistenceManager pm;

    @RequestMapping("/login")
    public String login() {
        return "access/loginForm";
    }

    @RequestMapping("/loginForm")
    public String loginForm(Model model) {
        return "access/loginForm";
    }

    @RequestMapping(value = "/loginForm", params = "error")
    public String loginForm(Model model, @RequestParam(value = "error") String error) {
        model.addAttribute("error", "Username o password errati");
        return "access/loginForm";
    }

    @RequestMapping("/denied")
    public String denied() {
        return "access/denied";
    }

    @RequestMapping("/loginArea")
    public ModelAndView loginArea(HttpServletRequest request) {
        
        HttpSession session = request.getSession(true);
        ModelAndView model = new ModelAndView("access/loginArea");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        if (user != null) {
            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra" + File.separator + "dist" + File.separator + "user" + File.separator + "img" + File.separator + user.getId() + File.separator + "avatar.jpg");
            if (dir.exists()) {
                session.setAttribute("avatar", "./dist/user/img/" + user.getId() + "/avatar.jpg");
            } else {
                session.setAttribute("avatar", "./dist/img/default_avatar.png");
            }
            
            session.setAttribute("uid", user.getId());

        }
        return model;
    }

    @RequestMapping("/test")
    @Secured("ROLE_USER")
    public String test() {
        return "access/test";
    }
}

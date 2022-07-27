package com.edutecno.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DefaultController {
	
	//encargado de despachar a los administradores a una parte y los usuarios tambien
	@RequestMapping("/default")
	public RedirectView defaultAfterLogin(HttpServletRequest request) {
		
		if (request.isUserInRole("ADMIN")) {//se verifica que el usuario que viene en el request tenga el Role de ADMIN
			return new RedirectView("/admin/"); //return "redirect:/admin/"
		}
		return new RedirectView("/user/"); //return "redirect:/user/"
	}
}

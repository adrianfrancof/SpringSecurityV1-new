package com.edutecno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	//encargado de recibir la peticion desde el DefaultController y despachar los admin a la vista en admin/home
	@GetMapping("/admin")
	public ModelAndView home() {
		
		return new ModelAndView("admin/home");
	}
}

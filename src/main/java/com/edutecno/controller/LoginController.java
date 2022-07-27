package com.edutecno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	//encargado de despachar recibiendo la peticion /login hacia la pagina de login.html
	@GetMapping("/login")
	public ModelAndView login() {
		
		return new ModelAndView("login");//retornando a la vista login.html
	}
}

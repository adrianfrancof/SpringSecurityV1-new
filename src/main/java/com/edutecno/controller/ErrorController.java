package com.edutecno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	
	//enncargado de despachar a la pagina de eror 403 recurso prohibido
	@GetMapping("/recurso-prohibido") //configurado en WebSecurityConfig .accessDeniedPage("/recurso-prohibido");
	public ModelAndView recursoProhibido() {
		return new ModelAndView("error/403");
	}
}

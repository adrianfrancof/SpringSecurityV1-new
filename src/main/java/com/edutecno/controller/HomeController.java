package com.edutecno.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.edutecno.dto.DetalleDTO;

@Controller
public class HomeController {
	
	//encargado de despachar a los usuarios a user/home enviando los valores de nuevo recibidos en la sesion
	@GetMapping("/user")
	public ModelAndView home(HttpSession httpSession) {
		
		ModelAndView modelAndView = new ModelAndView("user/home");
		modelAndView.addObject("detalle", new DetalleDTO());
		modelAndView.addObject("valores", httpSession.getAttribute("valores"));
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/users")
	public RedirectView home(HttpSession httpSession,@ModelAttribute DetalleDTO detalles) {
		
		List<DetalleDTO> valores = new ArrayList<DetalleDTO>();//lista para ser despachada con la sesion
		
		if (httpSession.getAttribute("valores") != null) {//si vienen valores dentro de la sesion se rescatan y luego se agregan a un arreglo
			valores.addAll((List<DetalleDTO>) httpSession.getAttribute("valores"));
		}
		
		valores.add(detalles);//se agregan los detalles o valores que vienen de la vista
		httpSession.setAttribute("valores", valores);
		return new RedirectView("/user");
	}
}

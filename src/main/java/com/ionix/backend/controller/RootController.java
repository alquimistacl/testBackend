package com.ionix.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Helper controller to get the token and expose the swagger documentation
 * 
 * @author Luis San Martin
 *
 */
@Controller
@RequestMapping
public class RootController {

	@GetMapping("/swagger")
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}

}

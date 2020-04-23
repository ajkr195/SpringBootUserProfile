package spring.boot.rocks.controllers;

//import lombok.extern.slf4j.Slf4j;
import spring.boot.rocks.services.AppUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Slf4j
@Controller
public class WebController {

	private final AppUserService appUserService;

	public WebController(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	@RequestMapping({ "", "/", "/userlist" })
	public String getIndexPage(Model model) {
		// log.debug("Getting Home page");
		//System.out.println("Getting Home page");
		model.addAttribute("appUsers", appUserService.getAppUsers());

		return "userlist";
	}

	@GetMapping("/contact")
	public String contactPage() {
		return "contact";
	}

	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}
}

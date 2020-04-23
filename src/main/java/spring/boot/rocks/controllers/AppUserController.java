package spring.boot.rocks.controllers;

import lombok.extern.slf4j.Slf4j;
import spring.boot.rocks.commands.AppUserCommand;
import spring.boot.rocks.services.AppUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("appUser/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("appUser", appUserService.findById(Long.valueOf(id)));
        return "userdetails";
    }

    @GetMapping("appUser/new")
    public String newAppUser(Model model){
        model.addAttribute("appUser", new AppUserCommand());

        return "userform";
    }

    @GetMapping("appUser/{id}/update")
    public String updateAppUser(@PathVariable String id, Model model){
        model.addAttribute("appUser", appUserService.findCommandById(Long.valueOf(id)));
        return  "userform";
    }

    @PostMapping("appUser")
    public String saveOrUpdate(@ModelAttribute AppUserCommand command){
        AppUserCommand savedCommand = appUserService.saveAppUserCommand(command);

        return "redirect:/appUser/" + savedCommand.getId() + "/show";
    }

    @GetMapping("appUser/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        appUserService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}

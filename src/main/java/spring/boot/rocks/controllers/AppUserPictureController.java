package spring.boot.rocks.controllers;

import spring.boot.rocks.commands.AppUserCommand;
import spring.boot.rocks.services.AppUserPictureService;
import spring.boot.rocks.services.AppUserService;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class AppUserPictureController {

    private final AppUserPictureService appUserPictureService;
    private final AppUserService appUserService;

    public AppUserPictureController(AppUserPictureService appUserPictureService, AppUserService appUserService) {
        this.appUserPictureService = appUserPictureService;
        this.appUserService = appUserService;
    }

    @GetMapping("appUser/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("appUser", appUserService.findCommandById(Long.valueOf(id)));

        return "imageupload";
    }

    @PostMapping("appUser/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("userPicturefile") MultipartFile file){

        appUserPictureService.saveUserPictureFile(Long.valueOf(id), file);

        return "redirect:/appUser/" + id + "/show";
    }

    @GetMapping("appUser/{id}/appUserimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        AppUserCommand appUserCommand = appUserService.findCommandById(Long.valueOf(id));

        if (appUserCommand.getUserpicture() != null) {
            byte[] byteArray = new byte[appUserCommand.getUserpicture().length];
            int i = 0;

            for (Byte wrappedByte : appUserCommand.getUserpicture()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}

package spring.boot.rocks.services;

import org.springframework.web.multipart.MultipartFile;

public interface AppUserPictureService {

    void saveUserPictureFile(Long recipeId, MultipartFile file);
}

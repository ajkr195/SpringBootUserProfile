package spring.boot.rocks.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

//import lombok.extern.slf4j.Slf4j;
import spring.boot.rocks.domain.AppUser;
import spring.boot.rocks.repositories.AppUserRepository;

//@Slf4j
@Service
public class AppUserPictureServiceImpl implements AppUserPictureService {

	private final AppUserRepository appUserRepository;

	public AppUserPictureServiceImpl(AppUserRepository appUserService) {
		this.appUserRepository = appUserService;
	}

	@Override
	@Transactional
	public void saveUserPictureFile(Long appUserId, MultipartFile file) {

		try {
			AppUser appUser = appUserRepository.findById(appUserId).get();
			Byte[] byteObjects = new Byte[file.getBytes().length];
			int i = 0;
			for (byte b : file.getBytes()) {
				byteObjects[i++] = b;
			}

			appUser.setUserpicture(byteObjects);
			System.out.println("Loaded image :: "+byteObjects.toString());
			appUserRepository.save(appUser);

		} catch (IOException e) {
			System.out.println("Error occurred");
//			log.error("Error occurred", e);
			e.printStackTrace();
		}
	}
}

package spring.boot.rocks.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.rocks.domain.Expertise;
import spring.boot.rocks.domain.AppUser;
import spring.boot.rocks.repositories.AppUserRepository;

@Component
public class AppUserBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final AppUserRepository appUserRepository;

	public AppUserBootstrap(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		appUserRepository.saveAll(getAppUsers());
		System.out.println("Loading initial Data");
	}

	private List<AppUser> getAppUsers() {

		List<AppUser> appUsers = new ArrayList<>(2);

		// User1
		AppUser appUser = new AppUser();
		appUser.setUsername("UserName1");
		appUser.setUserage(30);
		appUser.setUserssn(123456789);
		appUser.setUsertin(987654321);
		appUser.setUserdesignation("Sr. Architect");
		appUser.setUserblog("https://github.com/ajkr195?tab=repositories");
		appUser.setUseraboutme("Totally Messed-up. But I love Java and SpringBoot");
		appUser.setUserexpertise(Expertise.SPRINGBOOT);

		appUsers.add(appUser);

		// User2
		AppUser appUser2 = new AppUser();
		appUser2.setUsername("UserName2");
		appUser2.setUserage(40);
		appUser2.setUserssn(123456789);
		appUser2.setUsertin(987654321);
		appUser2.setUserdesignation("Sr.TechManager");
		appUser2.setUserblog("https://github.com/ajkr195?tab=repositories");
		appUser2.setUseraboutme("Totally Messed-up. But I love Linux and OPENSOURCE_TECHNOLOGIES");
		appUser2.setUserexpertise(Expertise.OPENSOURCE_TECHNOLOGIES);

		appUsers.add(appUser2);

		return appUsers;

	}
}

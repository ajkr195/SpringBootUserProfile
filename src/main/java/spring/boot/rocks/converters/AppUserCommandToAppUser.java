package spring.boot.rocks.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import spring.boot.rocks.commands.AppUserCommand;
import spring.boot.rocks.domain.AppUser;

@Component
public class AppUserCommandToAppUser implements Converter<AppUserCommand, AppUser> {

	@Synchronized
	@Nullable
	@Override
	public AppUser convert(AppUserCommand source) {
		if (source == null) {
			return null;
		}
		final AppUser appUser = new AppUser();
		appUser.setUsername(source.getUsername());
		appUser.setUserage(source.getUserage());
		appUser.setUserssn(source.getUserssn());
		appUser.setUsertin(source.getUsertin());
		appUser.setUserdesignation(source.getUserdesignation());
		appUser.setUserblog(source.getUserblog());
		appUser.setUseraboutme(source.getUseraboutme());
		appUser.setUserexpertise(source.getUserexpertise());

		return appUser;
	}
}

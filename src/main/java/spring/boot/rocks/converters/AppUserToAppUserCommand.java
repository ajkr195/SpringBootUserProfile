package spring.boot.rocks.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import spring.boot.rocks.commands.AppUserCommand;
import spring.boot.rocks.domain.AppUser;

@Component
public class AppUserToAppUserCommand implements Converter<AppUser, AppUserCommand> {

	@Synchronized
	@Nullable
	@Override
	public AppUserCommand convert(AppUser source) {
		if (source == null) {
			return null;
		}
		final AppUserCommand command = new AppUserCommand();
		command.setId(source.getId());
		command.setUsername(source.getUsername());
		command.setUserage(source.getUserage());
		command.setUserssn(source.getUserssn());
		command.setUsertin(source.getUsertin());
		command.setUserdesignation(source.getUserdesignation());
		command.setUserblog(source.getUserblog());
		command.setUseraboutme(source.getUseraboutme());
		command.setUserexpertise(source.getUserexpertise());
		command.setUserpicture(source.getUserpicture());

		return command;
	}
}

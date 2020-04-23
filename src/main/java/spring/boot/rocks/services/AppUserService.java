package spring.boot.rocks.services;

import spring.boot.rocks.commands.AppUserCommand;
import spring.boot.rocks.domain.AppUser;

import java.util.Set;

public interface AppUserService {

    Set<AppUser> getAppUsers();

    AppUser findById(Long l);

    AppUserCommand findCommandById(Long l);

    AppUserCommand saveAppUserCommand(AppUserCommand command);

    void deleteById(Long idToDelete);
}

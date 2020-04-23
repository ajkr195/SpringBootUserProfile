package spring.boot.rocks.services;

import lombok.extern.slf4j.Slf4j;
import spring.boot.rocks.commands.AppUserCommand;
import spring.boot.rocks.converters.AppUserCommandToAppUser;
import spring.boot.rocks.converters.AppUserToAppUserCommand;
import spring.boot.rocks.domain.AppUser;
import spring.boot.rocks.repositories.AppUserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository recipeRepository;
    private final AppUserCommandToAppUser recipeCommandToAppUser;
    private final AppUserToAppUserCommand recipeToAppUserCommand;

    public AppUserServiceImpl(AppUserRepository recipeRepository, AppUserCommandToAppUser recipeCommandToAppUser, AppUserToAppUserCommand recipeToAppUserCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToAppUser = recipeCommandToAppUser;
        this.recipeToAppUserCommand = recipeToAppUserCommand;
    }

    @Override
    public Set<AppUser> getAppUsers() {
        log.debug("I'm in the service");

        Set<AppUser> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public AppUser findById(Long l) {

        Optional<AppUser> recipeOptional = recipeRepository.findById(l);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("AppUser Not Found!");
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public AppUserCommand findCommandById(Long l) {
        return recipeToAppUserCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public AppUserCommand saveAppUserCommand(AppUserCommand command) {
        AppUser detachedAppUser = recipeCommandToAppUser.convert(command);

        AppUser savedAppUser = recipeRepository.save(detachedAppUser);
        log.debug("Saved AppUserId:" + savedAppUser.getId());
        return recipeToAppUserCommand.convert(savedAppUser);
    }

    @Override
    public void deleteById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }
}

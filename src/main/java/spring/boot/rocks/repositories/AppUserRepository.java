package spring.boot.rocks.repositories;

import org.springframework.data.repository.CrudRepository;

import spring.boot.rocks.domain.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
}

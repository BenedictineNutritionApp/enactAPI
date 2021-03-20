package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Administrators
 */
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    Optional<Administrator> findAdministratorByEmail(String email);

}

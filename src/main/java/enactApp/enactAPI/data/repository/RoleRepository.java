package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.ERole;
import enactApp.enactAPI.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}

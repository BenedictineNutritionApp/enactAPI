package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.UserHasFrequentGiIssues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for UserHasFrequentGiIssues
 */
public interface UserHasFrequentGiIssuesRepository extends JpaRepository<UserHasFrequentGiIssues, Long> {

    Optional<UserHasFrequentGiIssues> findUserHasFrequentGiIssuesByUserId(Long id);

}

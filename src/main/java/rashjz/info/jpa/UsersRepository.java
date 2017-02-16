package rashjz.info.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rashjz.info.domain.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    Users findByUsernameAndPassword(String username, String password);
}

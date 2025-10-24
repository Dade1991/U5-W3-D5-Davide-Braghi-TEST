package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.repositories;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.repositories;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByTitle(String title);

    Page<Event> findAll(Pageable pageable);
}
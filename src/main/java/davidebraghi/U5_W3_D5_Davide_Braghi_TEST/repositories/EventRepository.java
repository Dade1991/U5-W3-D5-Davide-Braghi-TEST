package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.repositories;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findAllByEndDateTimeAfterOrEndDateTimeIsNull(LocalDateTime now, Pageable pageable);

    Page<Event> findAll(Pageable pageable);
}
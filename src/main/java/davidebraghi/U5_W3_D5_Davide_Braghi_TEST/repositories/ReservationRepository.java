package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.repositories;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities.Reservation;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByStatusAndCreatedAt(ReservationStatus status, LocalDateTime createdAt);

    Optional<Reservation> findByCreationDateAndStatus(LocalDateTime createdAt, ReservationStatus status);
}
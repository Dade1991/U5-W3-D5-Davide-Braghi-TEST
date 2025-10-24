package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.repositories;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities.Reservation;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByEventIdAndStatus(Long eventId, ReservationStatus status);

    List<Reservation> findByUserIdAndStatus(Long userId, ReservationStatus status);
}
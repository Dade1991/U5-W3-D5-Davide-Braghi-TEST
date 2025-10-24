package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Enumerated
    @Column(nullable = false)
    private ReservationStatus status = ReservationStatus.PENDING;
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
}
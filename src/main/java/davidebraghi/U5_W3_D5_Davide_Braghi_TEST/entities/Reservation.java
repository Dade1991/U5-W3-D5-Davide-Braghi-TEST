package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@ToString
@NoArgsConstructor
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
    private ReservationStatus status;
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    public Reservation(ReservationStatus status,
                       LocalDateTime createdAt
    ) {
        this.status = status;
        this.createdAt = createdAt;
    }
}

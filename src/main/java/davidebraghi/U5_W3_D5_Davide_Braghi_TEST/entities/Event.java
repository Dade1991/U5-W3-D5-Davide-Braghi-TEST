package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;
    @Column(nullable = false)
    private LocalDateTime startDateTime;
    @Column(nullable = false)
    private Integer totalSeats;
    @Column(nullable = false)
    private Integer remainingSeats;
    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer; // perchè SOLO l'organizer potrà creare un evento, non uno user che dovrà passare per la prenotazione.
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    public Event(String title,
                 String description,
                 LocalDateTime startDateTime,
                 Integer totalSeats
    ) {
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.totalSeats = totalSeats;
    }
}

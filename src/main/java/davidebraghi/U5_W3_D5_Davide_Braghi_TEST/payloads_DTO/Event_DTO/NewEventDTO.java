package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.payloads_DTO.Event_DTO;

import java.time.LocalDateTime;

public record NewEventDTO(String title,
                          String description,
                          LocalDateTime startDateTime,
                          LocalDateTime endDateTime,
                          Integer totalSeats) {
}
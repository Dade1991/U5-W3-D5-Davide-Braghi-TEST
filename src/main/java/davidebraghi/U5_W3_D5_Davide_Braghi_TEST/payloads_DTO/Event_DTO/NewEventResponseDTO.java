package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.payloads_DTO.Event_DTO;

import java.time.LocalDateTime;

public record NewEventResponseDTO(Long id,
                                  String title,
                                  String description,
                                  LocalDateTime startDateTime,
                                  Integer totalSeats,
                                  Integer remainingSeats,
                                  String organizerUsername) {
}
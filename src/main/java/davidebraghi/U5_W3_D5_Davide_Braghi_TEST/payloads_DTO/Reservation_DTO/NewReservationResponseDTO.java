package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.payloads_DTO.Reservation_DTO;

import java.time.LocalDateTime;

public record NewReservationResponseDTO(Long id, Long eventId, Long userId, String status, LocalDateTime createdAt) {
}
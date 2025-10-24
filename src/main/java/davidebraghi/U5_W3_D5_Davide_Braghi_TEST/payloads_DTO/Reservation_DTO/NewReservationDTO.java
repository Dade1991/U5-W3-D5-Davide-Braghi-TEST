package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.payloads_DTO.Reservation_DTO;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.enums.ReservationStatus;

import java.time.LocalDateTime;

public record NewReservationDTO(ReservationStatus status, LocalDateTime createdAt) {
}
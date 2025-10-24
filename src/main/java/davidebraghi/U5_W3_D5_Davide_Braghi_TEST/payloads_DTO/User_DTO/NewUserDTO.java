package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.payloads_DTO.User_DTO;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotBlank(message = "Mandatory field.")
        String username,
        @NotBlank(message = "Mandatory field.")
        String email,
        @NotBlank(message = "Mandatory field.")
        @Size(min = 2, max = 30, message = "Name length shall be min 2 characters and max 30 characters")
        String name,
        @NotBlank(message = "Mandatory field.")
        @Size(min = 2, max = 30, message = "Surname length shall be min 2 characters and max 30 characters")
        String surname,
        @NotBlank(message = "Mandatory field.")
        @Size(min = 6, max = 30, message = "Password length shall be min 6 characters and max 30 characters")
        String password,
        Role role) {
}
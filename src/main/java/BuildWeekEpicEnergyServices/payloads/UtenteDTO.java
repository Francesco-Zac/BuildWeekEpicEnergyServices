package BuildWeekEpicEnergyServices.payloads;

import jakarta.validation.constraints.*;

import java.util.Set;

public record UtenteDTO(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String nome,
        @NotBlank String cognome,
        String avatar,
        Set<Long> ruoliIds
) {}

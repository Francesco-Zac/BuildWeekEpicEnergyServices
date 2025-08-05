package BuildWeekEpicEnergyServices.payloads;

import jakarta.validation.constraints.*;

import java.util.Set;

public record UtenteDTO(
        @NotBlank(message = "L'username è obbligatorio") String username,
        @NotBlank(message = "L'email è obbligatoria") @Email String email,
        @NotBlank(message = "La password è obbligatoria") String password,
        @NotBlank(message = "Il nome è obbligatorio") String nome,
        @NotBlank(message = "Il cognome è obbligatorio") String cognome,
        String avatar,
        Set<Long> ruoliIds
) {}

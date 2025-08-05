package BuildWeekEpicEnergyServices.payloads;

import BuildWeekEpicEnergyServices.enums.TipoSede;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IndirizzoDTO(
        @NotBlank(message = "l'Id è obbligatorio!")
        Long id,
        @NotBlank(message = "La via è obbligatoria!")
        String via,
        @NotNull( message = "Il civico è obbligatorio!")
        int civico,
        @NotNull(message = "Il cap è obbligatorio!")
        int cap,
        @NotBlank(message = "Il tipo di sede è obbligatorio!")
        TipoSede tipoSede,
        @NotBlank(message = "Il comune è obbligatorio!")
        Long comuneId
) {
}

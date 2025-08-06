package BuildWeekEpicEnergyServices.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateFatturaDTO(
        @NotNull
        LocalDate data,
        @NotNull
        BigDecimal importo,
        @NotBlank
        String numero,
        @NotNull
        Long clienteId,
        @NotNull
        UUID statoId) {
}

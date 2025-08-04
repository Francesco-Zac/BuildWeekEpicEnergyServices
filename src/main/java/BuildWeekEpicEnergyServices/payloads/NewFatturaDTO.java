package BuildWeekEpicEnergyServices.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record NewFatturaDTO(
        @NotNull
        LocalDate data,
        @NotNull
        BigDecimal importo,
        @NotBlank
        String numero,
        @NotNull
        UUID clienteId,
        @NotNull
        UUID statoId) {
}

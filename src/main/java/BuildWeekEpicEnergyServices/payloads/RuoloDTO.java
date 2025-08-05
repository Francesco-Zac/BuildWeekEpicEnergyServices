package BuildWeekEpicEnergyServices.payloads;

import jakarta.validation.constraints.NotBlank;

public record RuoloDTO (
        @NotBlank String name
){}
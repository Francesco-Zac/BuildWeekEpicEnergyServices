package BuildWeekEpicEnergyServices.payloads;

import jakarta.validation.constraints.NotBlank;

public record RuoloDTO (
        @NotBlank (message = "Il nome è obbligatorio") String name
){}
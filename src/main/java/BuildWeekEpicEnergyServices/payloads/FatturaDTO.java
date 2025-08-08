package BuildWeekEpicEnergyServices.payloads;


import BuildWeekEpicEnergyServices.entities.Cliente;
import BuildWeekEpicEnergyServices.entities.Fattura;
import BuildWeekEpicEnergyServices.entities.StatoFattura;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record FatturaDTO(
@NotBlank(message = "Il cliente è obbligatorio")
        Cliente cliente,
        @NotBlank(message = "Lo stato della fattura è obbligatorio!")
        StatoFattura statoFattura,
        @NotNull(message = "La data della fattura è obbligatoria")
        LocalDate data,
        @Size(min = 0,message = "La fattura deve avere un importo superiore a 0!")
        BigDecimal importo
) {

}
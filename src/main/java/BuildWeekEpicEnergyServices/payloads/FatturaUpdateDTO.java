package BuildWeekEpicEnergyServices.payloads;

import BuildWeekEpicEnergyServices.entities.Cliente;
import BuildWeekEpicEnergyServices.entities.StatoFattura;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FatturaUpdateDTO(
        Cliente cliente,

        StatoFattura statoFattura,

        LocalDate data,

        BigDecimal importo)
{
}

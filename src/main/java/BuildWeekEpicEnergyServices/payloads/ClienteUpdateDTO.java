package BuildWeekEpicEnergyServices.payloads;

import BuildWeekEpicEnergyServices.enums.TipoAzienda;

import java.time.LocalDate;

public record ClienteUpdateDTO(String ragioneSociale,
                               Long partitaIva,
                               String email,
                               LocalDate inseritoIl,
                               LocalDate ultimoContattoIl,
                               Integer fatturatoAnnuo,
                               String pec,
                               String numeroTelefono,
                               String emailContatto,
                               String nomeContatto,
                               String cognomeContatto,
                               String telefonoContatto,
                               String logoAzienda,
                               TipoAzienda tipo,
                               IndirizzoDTO sedeOperativa,
                               IndirizzoDTO sedeLegale) {
}

package BuildWeekEpicEnergyServices.payloads;

import BuildWeekEpicEnergyServices.enums.TipoAzienda;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record NuovoClienteDTO(

        @NotBlank(message = "La ragione sociale è obbligatoria")
        String ragioneSociale,

        @NotNull(message = "La partita IVA è obbligatoria")
        @Digits(integer = 11, fraction = 0, message = "La partita IVA deve contenere solo cifre (max 11)")
        Long partitaIva,

        @Email(message = "Email non valida")
        String email,

        @NotNull(message = "La data dell'ultimo contatto è obbligatoria")
        @PastOrPresent(message = "La data dell'ultimo contatto non può essere futura")
        LocalDate ultimoContattoIl,

        @PositiveOrZero(message = "Il fatturato deve essere positivo o zero")
        int fatturatoAnnuo,

        @NotBlank(message = "La PEC è obbligatoria")
        @Email(message = "PEC non valida")
        String pec,

        @NotBlank(message = "Il numero telefonico è obbligatorio")
        @Pattern(regexp = "^[0-9\\s+]+$", message = "Il numero telefonico può contenere solo numeri e spazi")
        String numeroTelefono,

        @Email(message = "Email del contatto non valida")
        String emailContatto,

        @NotBlank(message = "Nome contatto obbligatorio!")
        String nomeContatto,

        String cognomeContatto,

        @Pattern(regexp = "^[0-9\\s+]*$", message = "Il telefono del contatto può contenere solo numeri e spazi")
        String telefonoContatto,

        String logoAzienda,

//        @NotBlank(message = "Il tipo di azienda è obbligatorio")
        TipoAzienda tipoAzienda,

        @NotNull(message = "L'indirizzo della sede operativa è obbligatorio")
        IndirizzoDTO sedeOperativa,

        @NotNull(message = "L'indirizzo della sede legale è obbligatorio")
        IndirizzoDTO sedeLegale
) {}


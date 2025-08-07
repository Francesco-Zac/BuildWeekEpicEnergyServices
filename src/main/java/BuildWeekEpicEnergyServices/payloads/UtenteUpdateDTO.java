package BuildWeekEpicEnergyServices.payloads;

import java.util.List;

public record UtenteUpdateDTO(
        String email,
        String username,
        String password,
        String nome,
        String cognome,
        List<Long> ruoliId
) {}

package BuildWeekEpicEnergyServices.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "province")
@Getter
@Setter
public class Provincia {
    @Id
    private String sigla;
    private String provincia;
    private String regione;
}

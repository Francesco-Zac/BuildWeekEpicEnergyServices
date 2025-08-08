package BuildWeekEpicEnergyServices.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stati_fattura")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StatoFattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column( nullable = false)
    private String stato;

    @OneToMany(mappedBy = "statoFattura")
    private List<Fattura> fatture;
}

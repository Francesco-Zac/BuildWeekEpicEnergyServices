package BuildWeekEpicEnergyServices.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ruoli")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Ruolo(String name) {
        this.name = name;
    }
}
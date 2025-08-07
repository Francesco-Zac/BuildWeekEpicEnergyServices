package BuildWeekEpicEnergyServices.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ruoli")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"id"})
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
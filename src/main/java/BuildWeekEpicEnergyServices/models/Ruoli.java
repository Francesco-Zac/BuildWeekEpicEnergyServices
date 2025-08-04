package BuildWeekEpicEnergyServices.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ruoli")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ruoli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
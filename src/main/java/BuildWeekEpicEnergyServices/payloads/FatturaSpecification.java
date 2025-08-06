package BuildWeekEpicEnergyServices.payloads;


import BuildWeekEpicEnergyServices.entities.Fattura;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FatturaSpecification {

    public static Specification<Fattura> byFilters(
            Long clienteId,
            UUID statoId,
            LocalDate data,
            Integer anno,
            BigDecimal importoMin,
            BigDecimal importoMax
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (clienteId != null)
                predicates.add(cb.equal(root.get("cliente").get("id"), clienteId));

            if (statoId != null)
                predicates.add(cb.equal(root.get("stato").get("id"), statoId));

            if (data != null)
                predicates.add(cb.equal(root.get("data"), data));

            if (anno != null)
                predicates.add(cb.equal(cb.function("YEAR", Integer.class, root.get("data")), anno));

            if (importoMin != null)
                predicates.add(cb.greaterThanOrEqualTo(root.get("importo"), importoMin));

            if (importoMax != null)
                predicates.add(cb.lessThanOrEqualTo(root.get("importo"), importoMax));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
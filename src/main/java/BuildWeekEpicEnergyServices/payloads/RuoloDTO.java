package BuildWeekEpicEnergyServices.payloads;

import jakarta.validation.constraints.NotBlank;

public class RuoloDTO {
    @NotBlank
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

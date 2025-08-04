package BuildWeekEpicEnergyServices.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("L'id * " + id + " * non è stato trovato! Riprova");
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}

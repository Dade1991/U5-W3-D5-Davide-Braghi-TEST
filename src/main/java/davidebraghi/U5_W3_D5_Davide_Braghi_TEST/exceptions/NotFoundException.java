package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Record with ID: " + id + " was not found. Try again.");
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}

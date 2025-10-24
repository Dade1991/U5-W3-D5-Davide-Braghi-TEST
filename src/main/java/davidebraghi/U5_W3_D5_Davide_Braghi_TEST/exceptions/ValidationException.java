package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> errorsMessages;

    public ValidationException(List<String> errorsMessages) {
        super("Errors during 'validation' process. Try again.");
        this.errorsMessages = errorsMessages;
    }
}

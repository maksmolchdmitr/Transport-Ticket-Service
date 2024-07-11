package maks.molch.dmitr.core.controller.exception;

public record ErrorMessage(
        String message,
        String exceptionName,
        String exceptionMessage
) {
    public static ErrorMessage fromThrowable(Throwable e, String message) {
        return new ErrorMessage(
                message,
                e.getClass().getName(),
                e.getLocalizedMessage()
        );
    }
}

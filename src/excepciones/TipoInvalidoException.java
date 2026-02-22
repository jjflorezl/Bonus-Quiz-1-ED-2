package excepciones;

public class TipoInvalidoException extends RuntimeException {
    public TipoInvalidoException(String message) {
        super(message);
    }
}

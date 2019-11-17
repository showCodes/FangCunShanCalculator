import java.util.function.Supplier;

public class RPCException extends Exception {

    public RPCException() {
        super();
    }

    public RPCException(String msg) {
        super(msg);
    }
}

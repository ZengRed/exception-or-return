package zeng.test.exceptionorreturn.exceptions;

public class AppException extends RuntimeException {


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public AppException(String msg) {
        super(msg);
    }

    public AppException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

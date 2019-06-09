package zeng.test.exceptionorreturn.exceptions;

public class ServiceException extends AppException {

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

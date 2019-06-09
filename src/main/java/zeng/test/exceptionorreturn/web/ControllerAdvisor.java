package zeng.test.exceptionorreturn.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zeng.test.exceptionorreturn.dto.JsonResult;
import zeng.test.exceptionorreturn.exceptions.AppException;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) {
        if (e instanceof AppException) {
            return JsonResult.build(50001, e.getMessage());
        } else {
            return JsonResult.build(50002, "系统异常");
        }

    }

}

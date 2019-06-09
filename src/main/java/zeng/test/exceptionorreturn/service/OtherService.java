package zeng.test.exceptionorreturn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zeng.test.exceptionorreturn.dto.JsonResult;
import zeng.test.exceptionorreturn.dto.User;

@Service
public class OtherService {
    @Autowired
    private UserService userService;

    public User createAndDoSomething(User user) {
        User po = userService.createOrElseThrow(user);

        doSth(po);
        return po;

    }

    public JsonResult createAndDoSomething2(User user) {
        JsonResult result = userService.createOrElseReturn(user);
        if (result.getCode() == 200) {
            User po = (User) result.getData();
            doSth(po);
        }

        return JsonResult.OK;
    }

    private void doSth(User user) {
        // do nothing

    }
}

package zeng.test.exceptionorreturn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zeng.test.exceptionorreturn.dto.JsonResult;
import zeng.test.exceptionorreturn.dto.User;
import zeng.test.exceptionorreturn.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * exception中断
     *
     * @param user user
     * @return dto
     */
    @RequestMapping("/createOrElseThrow")
    public JsonResult createOrElseThrow(User user) {
        User po = userService.createOrElseThrow(user);
        return JsonResult.ok(po);
    }

    /**
     * return中断
     *
     * @param user user
     * @return dto
     */
    @RequestMapping("/createOrElseReturn")
    public JsonResult createOrElseReturn(User user) {
        return userService.createOrElseReturn(user);

    }
}

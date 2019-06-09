package zeng.test.exceptionorreturn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zeng.test.exceptionorreturn.dao.UserDao;
import zeng.test.exceptionorreturn.dto.JsonResult;
import zeng.test.exceptionorreturn.dto.User;
import zeng.test.exceptionorreturn.exceptions.ServiceException;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User createOrElseThrow(User user) {
        if ("foo".equals(user.getName())) {
            throw new ServiceException("用户已存在");
        }

        User po = userDao.create(user);

        return po;
    }

    public JsonResult createOrElseReturn(User user) {
        if ("foo".equals(user.getName())) {
            return JsonResult.build(50001, "用户已存在");
        }

        User po = userDao.create(user);
        return JsonResult.ok(po);
    }

}

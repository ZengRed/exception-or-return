package zeng.test.exceptionorreturn.dao;

import org.springframework.stereotype.Repository;
import zeng.test.exceptionorreturn.dto.User;

@Repository
public class UserDao {

    public User create(User user) {
        // do nothing
        return user;
    }
}

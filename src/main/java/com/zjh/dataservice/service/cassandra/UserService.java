package com.zjh.dataservice.service.cassandra;

import com.zjh.dataservice.entity.cassandra.User;
import com.zjh.dataservice.exception.DataNotFoundException;

public interface UserService {

    User saveUser(User user);

    User findByUserId(String uuid) throws DataNotFoundException;
}

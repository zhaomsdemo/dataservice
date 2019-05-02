package com.zjh.dataservice.service.cassandra;

import com.zjh.dataservice.entity.cassandra.User;

public interface UserService {

    User saveUser(User user);
}

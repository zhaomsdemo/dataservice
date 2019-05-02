package com.zjh.dataservice.service.cassandra;

import com.datastax.driver.core.utils.UUIDs;
import com.zjh.dataservice.entity.cassandra.User;
import com.zjh.dataservice.repository.cassandra.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String uuid = UUIDs.timeBased().toString();
        user.setUserId(uuid);
        userRepository.save(user);
        return user;
    }
}

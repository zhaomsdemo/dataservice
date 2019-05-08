package com.zjh.dataservice.repository.cassandra;

import com.zjh.dataservice.entity.cassandra.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends CassandraRepository<User,String> {
}

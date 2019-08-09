package com.zjh.dataservice;

import com.btmatthews.springboot.memcached.EnableMemcached;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMemcached
public class DataserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataserviceApplication.class, args);
	}

}

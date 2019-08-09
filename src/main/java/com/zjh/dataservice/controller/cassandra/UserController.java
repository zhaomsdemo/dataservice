package com.zjh.dataservice.controller.cassandra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjh.dataservice.configuration.Constants;
import com.zjh.dataservice.entity.cassandra.User;
import com.zjh.dataservice.exception.DataNotFoundException;
import com.zjh.dataservice.service.cassandra.UserService;
import net.spy.memcached.MemcachedClient;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<User> postUser(@RequestBody User user) throws IOException, SolrServerException {
        User response = userService.saveUser(user);
        ResponseEntity<User> responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);

//        saveToSolr(user);
        saveToMemcached(user);
        return responseEntity;
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<User> getUser(@Pattern(regexp = Constants.REGEX_UUID) @PathVariable String uuid) throws DataNotFoundException {
        User user = getUserFromMemcached(uuid);
        if(user == null ){
            user = userService.findByUserId(uuid);
            saveToMemcached(user);
        }
        ResponseEntity<User> responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
        return responseEntity;
    }

    private void saveToSolr(User user) throws IOException, SolrServerException {
        SolrInputDocument document = new SolrInputDocument();
        document.setField("userId", user.getUserId());
        document.setField("userName", user.getUserName());
        document.setField("age", user.getAge());

        solrClient.add("demo", document);
        solrClient.commit("demo");
    }

    private void saveToMemcached(User user){
        memcachedClient.add(user.getUserId(), 60000, user);
    }

    private User getUserFromMemcached(String key){
        Object object = memcachedClient.get(key);
        if(object == null){
            return null;
        }
        return convertObjectToUser(object);
    }

    private User convertObjectToUser(Object object){
        User user = objectMapper.convertValue(object, User.class);
        return user;
    }
}

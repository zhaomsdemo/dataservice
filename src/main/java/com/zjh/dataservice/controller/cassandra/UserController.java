package com.zjh.dataservice.controller.cassandra;

import com.zjh.dataservice.entity.cassandra.User;
import com.zjh.dataservice.service.cassandra.UserService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SolrClient solrClient;

    @PostMapping("/create")
    public ResponseEntity<User> postUser(@RequestBody User user) throws IOException, SolrServerException {
        User response = userService.saveUser(user);
        ResponseEntity<User> responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);

        saveToSolr(user);
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
}

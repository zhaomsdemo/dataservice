package com.zjh.dataservice.controller.cassandra;

import com.zjh.dataservice.configuration.Constants;
import com.zjh.dataservice.entity.cassandra.User;
import com.zjh.dataservice.exception.DataNotFoundException;
import com.zjh.dataservice.service.cassandra.UserService;
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

    @PostMapping("/create")
    public ResponseEntity<User> postUser(@RequestBody User user) throws IOException, SolrServerException {
        User response = userService.saveUser(user);
        ResponseEntity<User> responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);

        saveToSolr(user);
        return responseEntity;
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<User> getUser(@Pattern(regexp = Constants.REGEX_UUID) @PathVariable String uuid) throws DataNotFoundException {
        User user = userService.findByUserId(uuid);
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
}

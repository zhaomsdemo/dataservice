package com.zjh.dataservice.common.steps;

import com.zjh.dataservice.component.common.CommonSteps;
import com.zjh.dataservice.entity.cassandra.User;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserSteps extends CommonSteps {

    @Given("^Create a request for new user with userName (.*) and age (.*)$")
    public void prepared_user_request(String userName,int age){
        User user = User.builder().userName(userName).age(age).build();
        setAttribute("user", user);
    }

    @When("^Call the service for post user$")
    public void call_user_post_service(){

    }

    @Then("^Service should respond status of (.*)$")
    public void valid_response_state(int status){

    }

    @And("^Service should respond user Id$")
    public void valid_response_has_user_id(){

    }
}

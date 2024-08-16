package com.project_two_tests.steps;

import java.io.IOException;

import com.project_two_tests.utils.HttpUtil;

import io.cucumber.java.Before;

public class BaseSteps {

    @Before
    public void resetDatabaseAfterTest() {
        try {
            HttpUtil.sendPostRequest("http://localhost:8080/api/testing/reset");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

package com.project_two_functional_tests.stepdefs;

import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.BeforeAll;

public class BaseSteps {
    @BeforeAll
    public static void resetDatabaseBeforeAll() {
        ResetDatabase.run();
    }

}

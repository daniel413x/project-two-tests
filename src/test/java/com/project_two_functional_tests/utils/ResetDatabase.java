package com.project_two_functional_tests.utils;

import java.io.IOException;

public class ResetDatabase {
    public static void run() {
        try {
            HttpUtil.sendPostRequest(
                    "http://localhost:5000/api/testing/reset");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}

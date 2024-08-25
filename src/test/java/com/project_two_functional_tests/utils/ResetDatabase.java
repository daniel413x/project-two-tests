package com.project_two_functional_tests.utils;

import java.io.IOException;

public class ResetDatabase {
    public static void run() {
        try {
            HttpUtil.sendPostRequest(
                    "http://crag-supply-co-env-4.eba-ndsnmqsj.us-east-1.elasticbeanstalk.com/api/testing/reset");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}

package com.efes.quizApp.util;

public final class ApiPaths {


    private static final String BASE_PATH = "/api"; // buraya /v1 diyip api versiyonlaması yapılabilir.

    public static  final  class QuestionCtrl{
        public static  final String CTRL = BASE_PATH + "/question";
    }
    public static  final  class UserCtrl{
        public static  final String CTRL = BASE_PATH + "/user";
    }
    public static  final  class TrialCtrl{
        public static  final String CTRL = BASE_PATH + "/trial";
    }
}

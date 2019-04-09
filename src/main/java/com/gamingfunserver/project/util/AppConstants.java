package com.gamingfunserver.project.util;

public interface AppConstants {
    String DEFAULT_PAGE_NUMBER = "0";
    String DEFAULT_PAGE_SIZE = "30";

    int MAX_PAGE_SIZE = 50;

    int MIN_NUMBER_FOR_ID_FIELD = 1;

    String [] URL_OPEN_ENDPOINTS = new String[]{
            "/api/user/checkUsernameAvailability",
            "/api/user/checkEmailAvailability",
            "/test",
            "/roles"

    };
}
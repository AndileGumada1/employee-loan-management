package com.atlas.loan.application.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {
    public static final String ACTIVATION_EMAIL = "http://localhost:8084/api/auth/accountVerification";
    public static final String USER_NOT_FOUND = "user with email %s not found";
}

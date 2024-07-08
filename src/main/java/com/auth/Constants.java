package com.auth;

public class Constants {
    /* KeyWord */
    public static final String REFRESH_TOKEN = "refreshToken";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String VERIFY_CODE_KEY_NAME = "vnm";
    public static final String TOKEN_TYPE="typ";

    /* Server Message */
    public static final String SECRET_KEY_MUST_BE_AT_LEAST_60_CHARACTERS_LONG = "Secret key must be at least 60 characters long";
    public static final String REFRESH_TOKEN_EXPIRATION_MINUTE_MUST_BE_AT_LEAST_10_MINUTES_LONG = "Refresh token expiration minute must be at least 10 minutes long";
    public static final String ACCESS_TOKEN_EXPIRATION_MINUTE_MUST_BE_AT_LEAST_10_MINUTES_LONG = "Access token expiration minute must be at least 10 minutes long";
    public static final String AUTH_TOKEN_EXPIRATION_MINUTE_MUST_BE_AT_LEAST_1_MINUTES_LONG = "Auth token expiration minute must be at least 1 minute long";
    public static final String REGISTER_TOKEN_EXPIRATION_MINUTE_MUST_BE_AT_LEAST_5_MINUTES_LONG = "Register token expiration minute must be at least 5 minutes long";

    /* Email Message */
    public static final String EMAIL_SUBJECT = "ChatGPT Template Service 이메일 인증";
    public static final String EMAIL_MESSAGE = "<p>인증번호 : <span style=\"color:red\">%s<span></p>";

    /* Client Message */
    public static final String EMAIL_REQUIRED = "이메일은 필수 값입니다.";
    public static final String EMAIL_INVALID = "유효한 이메일 주소를 입력해주세요.";
    public static final String EMAIL_SIZE = "5이상, 64이하 이메일을 입력해주세요.";
    public static final String PASSWORD_REQUIRED = "비밀번호는 필수 값입니다.";
    public static final String EMAIL_TOKEN_REQUIRED = "이메일 인증 토큰은 필수 값입니다.";
    public static final String INVALID_JWT_TOKEN_FORMAT = "잘못된 토큰 요청입니다.";
    public static final String VERIFY_CODE_REQUIRED = "인증코드는 필수 값입니다.";
    public static final String VERIFY_CODE_MUST_BE_NUMBER = "숫자만 입력해야 합니다.";
    public static final String VERIFY_CODE_DOSE_NOT_MACH = "인증번호 일치하지 않음";
    public static final String UNAUTHENTICATED_EMAIL = "인증되지 않은 이메일";

    public static final String SIGNUP_EMAIL_DIFFERENT_ERROR = "입력된 이메일과 인증된 이메일이 다름";
    public static final String SIGNUP_PHONE_NUMBER_DIFFERENT_ERROR = "입력된 전화번호와 인증된 전화번호 다름";
    public static final String RECORD_NOT_EXIST = "레코드가 존재하지 않음";
    public static final String SIGNUP_EMAIL_EXIST = "이메일이 존재함";
    public static final String BAD_REQUEST = "잘못된 요청";
    public static final String VALID_ERROR = "잘못된 형식 요청";
}

package by.itacademy.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {


    /**
     * String names
     */
    public static final String ID = "id";
    public static final String USER_ID = "userId";
    public static final String TARE_ID = "tareId";
    public static final String COUPON_ID = "couponId";
    public static final String COMPANY_ID = "companyId";
    public static final String NAME = "name";
    public static final String LOGIN_FIELD_NAME = "login";
    public static final String COMPANY_FIELD_NAME = "company";
    public static final String BONUSES_FIELD_NAME = "bonuses";
    public static final String ROLE_FIELD_NAME = "role";
    public static final String TARES_FIELD_NAME = "tares";

    public static final String ZERO_EXPRESSION_DEFAULT = "java(0)";
    public static final String CONSUMER_ROLE_DEFAULT = "java(Role.CONSUMER)";


    /**
     * URL parts
     */
    public static final String START_PART_URL = "/api/version1";
    public static final String COUPON_PROCESSING_START_URL = "/api/version1/coupon_processing";
    public static final String GET_ADDRESSES_URL = "/address/{name}";

    public static final String SAVE_COMPANY_URL = "/company";
    public static final String GET_COMPANY_URL = "/company/{id}";
    public static final String GET_COMPANIES_URL = "/companies";
    public static final String UPDATE_COMPANY_URL = "/company/{id}";
    public static final String DELETE_COMPANY_URL = "/company/{id}";

    public static final String SAVE_COUPON_URL = "/coupon";
    public static final String GET_COUPON_URL = "/coupon/{id}";
    public static final String GET_COUPONS_URL = "/coupons";
    public static final String GET_USER_COUPONS_URL = "/coupons/user/{id}";
    public static final String GET_COMPANY_COUPONS_URL = "/coupons/company/{id}";
    public static final String GET_AVAILABLE_USER_COUPONS_URL = "/available_coupons/user/{id}";
    public static final String UPDATE_COUPON_URL = "/coupon/{id}";
    public static final String DELETE_COUPON_URL = "/coupon/{id}";

    public static final String SAVE_TARE_URL = "/tare";
    public static final String GET_TARE_URL = "/tare/{id}";
    public static final String GET_TARES_URL = "/tares";
    public static final String GET_USER_TARES_URL = "/tares/user/{id}";
    public static final String UPDATE_TARE_URL = "/tare/{id}";
    public static final String DELETE_TARE_URL = "/tare/{id}";

    public static final String GET_USER_BY_ID_URL = "/user/{id}";
    public static final String GET_USER_BY_LOGIN_URL = "/user/login/{login}";
    public static final String SAVE_USER_URL = "/user";
    public static final String UPDATE_USER_URL = "/user/{id}";
    public static final String DELETE_USER_URL = "/user/{id}";
    public static final String DEPOSIT_TARE_BY_USER_URL = "/user/{userId}/deposit_tare/{tareId}";
    public static final String BUY_COUPON_BY_USER_URL = "/user/{userId}/coupon/{couponId}";
    public static final String USE_COUPON_BY_USER_URL = "/user/{userId}/coupon/{couponId}/without_bonuses_back";
    public static final String RETURN_COUPON_BY_USER_URL = "/user/{userId}/return_coupon/{couponId}/with_bonuses_back";

    /**
     * Regex
     */
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REGEX = "^.{7,10}$";

    /**
     * Aspects
     */
    public static final String CONTROLLERS_POINTCUT = "execution(* by.itacademy.controllers..*(..))";
    public static final String POINTCUT = "pointCut()";
    public static final String RESPONSE = "response";
    public static final String EXCEPTION = "exception";
    public static final String LOG_REQUEST_PATTERN = "Method {} : {} was started with URI {}";
    public static final String LOG_RESPONSE_PATTERN = "Method {} : {} returned value: {}";
    public static final String LOG_EXCEPTION_PATTERN = "Exception while do method {} : {}, exception means: {}";

    /**
     * Internationalization
     */
    public static final String INTERNATIONALIZATION_RESOURCE = "classpath:messages";
    public static final String ENCODING = "UTF-8";
    public static final String MISSING_COMPANY_MESSAGE = "missing.company.message";
    public static final String MISSING_COUPON_MESSAGE = "missing.coupon.message";
    public static final String MISSING_TARE_MESSAGE = "missing.tare.message";
    public static final String MISSING_USER_MESSAGE = "missing.user.message";
    public static final String INSUFFICIENT_BONUSES_MESSAGE = "insufficient.bonuses.message";
    public static final String DISABLED_COUPON_MESSAGE = "disabled.coupon.message";
    public static final String WRONG_LOGIN_MESSAGE = "wrong.login.message";

    public static final String DEFAULT_COMPANY_EXCEPTION_MESSAGE = "Wrong company id";
    public static final String DEFAULT_COUPON_EXCEPTION_MESSAGE = "Wrong coupon id";
    public static final String DEFAULT_TARE_EXCEPTION_MESSAGE = "Wrong tare id";
    public static final String DEFAULT_USER_EXCEPTION_MESSAGE = "Wrong user id";
    public static final String DEFAULT_BONUSES_EXCEPTION_MESSAGE = "Not enough bonuses to buy coupon";
    public static final String DEFAULT_USER_COUPON_EXCEPTION_MESSAGE = "Unavailable coupon for user";
    public static final String DEFAULT_LOGIN_EXCEPTION_MESSAGE = "Entered existing login";

    /**
     * Table names
     */
    public static final String COMPANY = "COMPANY";
    public static final String COUPON = "COUPON";
    public static final String TARE = "TARE";
    public static final String PERSON = "PERSON";
    public static final String PERSON_COUPON = "PERSON_COUPON";
    public static final String PERSON_TARE = "PERSON_TARE";

    /**
     * Column names
     */
    public static final String ID_COLUMN = "ID";
    public static final String COMPANY_NAME = "COMPANY_NAME";
    public static final String DETAILS = "DETAILS";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String COST = "COST";
    public static final String TARE_CATEGORY = "TARE_CATEGORY";
    public static final String LITRES_VOLUME = "LITRES_VOLUME";
    public static final String BONUSES_TO_ACCOUNTING = "BONUSES_TO_ACCOUNTING";
    public static final String LOGIN = "LOGIN";
    public static final String PASSWORD = "PASSWORD";
    public static final String NAME_COLUMN = "NAME";
    public static final String BONUSES = "BONUSES";
    public static final String ROLE = "ROLE";
    public static final String COMPANY_REFERENCE = "company";
    public static final String COUPONS_REFERENCE = "coupons";

    /**
     * Added columns
     */
    public static final String FK_COMPANY_ID_COLUMN = "COMPANY_ID";
    public static final String FK_PERSON_ID_COLUMN = "PERSON_ID";
    public static final String FK_COUPON_ID_COLUMN = "COUPON_ID";
    public static final String FK_TARE_ID_COLUMN = "TARE_ID";

    /**
     * Feign clients
     */
    public static final String ADDRESSES_URL_PROPERTIES = "${services.external.addresses.url}";
    public static final String FEIGN_CLIENT_NAME = "addresses-client";

    /**
     * Queries
     */
    public static final String SELECT_USER_BONUSES_JPQL_QUERY = "SELECT user.bonuses FROM User AS user WHERE user.id = :id";
}

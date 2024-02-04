package by.itacademy.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {


    /**
     * stringNames
     */
    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASS = "password";
    public static final String ROLE = "role";
    public static final String COUPON_DESCRIPTION = "description";
    public static final String COUPON_COST = "cost";
    public static final String TARE_CATEGORY = "tareCategory";
    public static final String TARE_VOLUME = "litresVolume";
    public static final String BONUSES_TO_ACCOUNTING = "bonusesToAccounting";
    public static final String USER_NAME = "name";

    /**
     * URL parts
     */
    public static final String TARES_CREATE_URL = "/tares/create";
    public static final String TARES_READ_URL = "/tares/read";
    public static final String TARES_UPDATE_URL = "/tares/updateTare";
    public static final String TARES_DELETE_URL = "/tares/deleteUser";
    public static final String USERS_CREATE_URL = "/users/create";
    public static final String USERS_READ_URL = "/users/read";
    public static final String USERS_UPDATE_URL = "/users/updateTare";
    public static final String USERS_DELETE_URL = "/users/deleteUser";
    public static final String USERS_USE_COUPON_URL = "/users/useCoupon";
    public static final String USERS_BUY_COUPON_URL = "/users/buyCoupon";
    public static final String USERS_DEPOSIT_TARE_URL = "/users/depositTare";
    public static final String COMPANIES_READ_URL = "/companies/read";
    public static final String COMPANIES_CREATE_URL = "/companies/create";
    public static final String REGISTRATION_URL = "/registration";
    public static final String AUTHENTICATION_URL = "/authentication";
    public static final String CHOICE_URL = "/choice";
    public static final String EXIT_URL = "/exit";

    /**
     * Count
     */
    public static final String ZERO = "0";

    /**
     * Regex
     */
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
}

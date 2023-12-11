package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    /**
     * Pages
     */
    public static final String PASS_ERROR_PAGE = "/pages/error/password_error.jsp";
    public static final String LOGIN_ERROR_PAGE = "/pages/error/login_error.jsp";
    public static final String AUTHORIZATION_ERROR_PAGE = "/pages/error/authorization_error.jsp";
    public static final String ID_ERROR_PAGE = "/pages/error/id_error.jsp";
    public static final String AUTHENTICATION_PAGE = "/pages/user/enter/user_authentication.jsp";
    public static final String TARES_READ_PAGE = "/pages/tare/tares_read.jsp";
    public static final String ADMIN_MENU_PAGE = "/pages/user/menu/admin_menu.jsp";
    public static final String USER_REGISTRATION_PAGE = "/pages/user/enter/user_registration.jsp";
    public static final String USERS_READ_PAGE = "/pages/user/users_read.jsp";
    public static final String START_PAGE = "/index.jsp";
    public static final String TARE_DELETE_PAGE = "/pages/tare/tare_deleted.jsp";
    public static final String TARE_CREATE_PAGE = "/pages/tare/tare_create.jsp";
    public static final String TARE_UPDATE_PAGE = "/pages/tare/tare_update.jsp";
    public static final String USER_CREATE_PAGE = "/pages/user/user_create.jsp";
    public static final String USER_UPDATE_PAGE = "/pages/user/user_update.jsp";
    public static final String COMPANIES_READ_PAGE = "/pages/company/read_companies.jsp";
    public static final String COMPANY_CREATE_PAGE = "/pages/company/create_company.jsp";

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
    public static final String TARES_UPDATE_URL = "/tares/update";
    public static final String TARES_DELETE_URL = "/tares/delete";
    public static final String USERS_CREATE_URL = "/users/create";
    public static final String USERS_READ_URL = "/users/read";
    public static final String USERS_UPDATE_URL = "/users/update";
    public static final String USERS_DELETE_URL = "/users/delete";
    public static final String COMPANIES_READ_URL = "/companies/read";
    public static final String COMPANIES_CREATE_URL = "/companies/create";
    public static final String REGISTRATION_URL = "/registration";
    public static final String AUTHENTICATION_URL = "/authentication";
    public static final String EXIT_URL = "/exit";

    /**
     * Attributes
     */
    public static final String TARES_ATTRIBUTE = "tares";
    public static final String TARE_ATTRIBUTE = "tare";
    public static final String USERS_ATTRIBUTE = "users";
    public static final String USER_ATTRIBUTE = "user";
    public static final String COMPANY_ATTRIBUTE = "companies";

    /**
     * Parameters
     */
    public static final String CHOICE_PARAMETER = "choice";
    public static final String COMPANY_NAME_PARAMETER = "companyName";
    public static final String COMPANY_DETAILS_PARAMETER = "details";

    /**
     * Buttons
     */
    public static final String LOGIN_BUTTON = "Log In";
    public static final String REGISTRATE_BUTTON = "Registrate";
    public static final String SEE_TARES_BUTTON = "See tares";
    public static final String DELETE_TARE_BUTTON = "Delete tare";
    public static final String SEE_USERS_BUTTON = "See users";
    public static final String CREATE_TARE_BUTTON = "Create tare";
    public static final String UPDATE_TARE_BUTTON = "Update tare";
    public static final String CREATE_USER_BUTTON = "Create user";
    public static final String UPDATE_USER_BUTTON = "Update user";
    public static final String SEE_COMPANIES_BUTTON = "See companies";
    public static final String CREATE_COMPANY_BUTTON = "Create company";

    /**
     * Count
     */
    public static final Integer ZERO = 0;
}
